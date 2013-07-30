
package altenergy.power;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import mekanism.api.IStrictEnergyAcceptor;
import mekanism.api.Object3D;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.world.ChunkEvent;
import altenergy.core.util.EnergyUtil;
import altenergy.lib.Reference;

public class EnergyBuswayNetwork {

	public HashSet<IBusway> busways = new HashSet<IBusway>();

	public Set<TileEntity> possibleAcceptors = new HashSet<TileEntity>();
	public Map<TileEntity, ForgeDirection> acceptorDirections = new HashMap<TileEntity, ForgeDirection>();

	private double joulesTransmitted = 0;
	private double joulesLastTick = 0;
	private int ticksSinceCreate = 0;
	private boolean fixed = false;

	public EnergyBuswayNetwork(IBusway... varBusways) {

		busways.addAll(Arrays.asList(varBusways));
		EnergyBuswayNetworkRegistry.getInstance().registerNetwork(this);

	}

	public EnergyBuswayNetwork(Set<EnergyBuswayNetwork> networks) {

		for (EnergyBuswayNetwork net : networks) {
			if (net != null) {
				addAllBusways(net.busways);
				net.deregister();
			}
		}

		refresh();
		EnergyBuswayNetworkRegistry.getInstance().registerNetwork(this);
	}

	public double getEnergyNeeded(ArrayList<TileEntity> ignored) {

		return 0;
	}

	public double emit(double energyToSend, ArrayList<TileEntity> ignored) {

		double energyAvailable = energyToSend;
		double sent;
		List availableAcceptors = Arrays.asList(getEnergyAcceptors().toArray());

		Collections.shuffle(availableAcceptors);

		if (!availableAcceptors.isEmpty()) {

			int divider = availableAcceptors.size();
			double remaining = energyToSend % divider;
			double sending = (energyToSend - remaining) / divider;

			for (Object obj : availableAcceptors) {
				if (obj instanceof TileEntity && !ignored.contains(obj)) {
					TileEntity acceptor = (TileEntity) obj;
					double currentSending = sending + remaining;

					remaining = 0;

					/**
					 * This is where you tell the busway how to emit and where
					 * to emit to.
					 */
					if (acceptor instanceof IStrictEnergyAcceptor) {
						energyToSend -= currentSending
								- ((IStrictEnergyAcceptor) acceptor).transferEnergyToAcceptor(currentSending);
					}
				}
			}

			sent = energyAvailable - energyToSend;
			joulesTransmitted += sent;
		}

		return energyToSend;
	}

	public Set<TileEntity> getEnergyAcceptors() {

		return null;
	}

	public void refresh() {

		Set<IBusway> iterCables = (Set<IBusway>) busways.clone();
		Iterator<IBusway> it = iterCables.iterator();

		possibleAcceptors.clear();
		acceptorDirections.clear();

		while (it.hasNext()) {
			IBusway conductor = it.next();

			if (conductor == null || ((TileEntity) conductor).isInvalid()) {
				it.remove();
				busways.remove(this);
			} else {
				conductor.setNetwork(this);
			}
		}

		for (IBusway busway : iterCables) {
			TileEntity[] acceptors = EnergyUtil.getConnectedEnergyRecivers((TileEntity) busway);

			for (TileEntity acceptor : acceptors) {
				if (acceptor != null && !(acceptor instanceof IBusway)) {
					possibleAcceptors.add(acceptor);
					acceptorDirections.put(acceptor,
							ForgeDirection.getOrientation(Arrays.asList(acceptors).indexOf(acceptor)));
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	public void merge(EnergyBuswayNetwork network) {

		if (network != null && network != this) {
			@SuppressWarnings("rawtypes")
			Set<EnergyBuswayNetwork> networks = new HashSet();
			networks.add(this);
			networks.add(network);
			EnergyBuswayNetwork newNetwork = new EnergyBuswayNetwork(networks);
			newNetwork.refresh();
		}
	}

	public void addAllBusways(Set<IBusway> newBusways) {

		busways.addAll(newBusways);
	}

	public void split(IBusway splitPoint) {

		if (splitPoint instanceof TileEntity) {
			removeBusway(splitPoint);

			TileEntity[] connectedBlocks = new TileEntity[6];
			boolean[] dealtWith = {
					false, false, false, false, false, false
			};

			for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
				TileEntity sideTile = Object3D.get((TileEntity) splitPoint).getFromSide(direction)
						.getTileEntity(((TileEntity) splitPoint).worldObj);

				if (sideTile != null) {
					connectedBlocks[Arrays.asList(ForgeDirection.values()).indexOf(direction)] = sideTile;

				}

			}

			for (int countOne = 0; countOne < connectedBlocks.length; countOne++) {
				TileEntity connectedBlockA = connectedBlocks[countOne];

				if (connectedBlockA instanceof IBusway && !dealtWith[countOne]) {
					NetworkFinder finder = new NetworkFinder(((TileEntity) splitPoint).worldObj,
							Object3D.get(connectedBlockA), Object3D.get((TileEntity) splitPoint));
					List<Object3D> partNetwork = finder.exploreNetwork();

					for (int countTwo = countOne + 1; countTwo < connectedBlocks.length; countTwo++) {
						TileEntity connectedBlockB = connectedBlocks[countTwo];

						if (connectedBlockB instanceof IBusway && !dealtWith[countTwo]) {
							if (partNetwork.contains(Object3D.get(connectedBlockB))) {
								dealtWith[countTwo] = true;
							}
						}
					}

					EnergyBuswayNetwork newNetwork = new EnergyBuswayNetwork();

					for (Object3D node : finder.iterated) {
						TileEntity nodeTile = node.getTileEntity(((TileEntity) splitPoint).worldObj);

						if (nodeTile instanceof IBusway) {
							if (nodeTile != splitPoint) {
								newNetwork.busways.add((IBusway) nodeTile);
							}
						}
					}

					newNetwork.refresh();

				}
			}

			deregister();
		}
	}

	public void fixMessedUpNetwork(IBusway busway) {

		System.out.println("Fixing Network");

		if (busway instanceof TileEntity) {
			NetworkFinder finder = new NetworkFinder(((TileEntity) busway).getWorldObj(),
					Object3D.get((TileEntity) busway), null);
			List<Object3D> partNetwork = finder.exploreNetwork();
			Set<IBusway> newBusways = new HashSet<IBusway>();
			for (Object3D node : partNetwork) {
				TileEntity nodeTile = node.getTileEntity(((TileEntity) busway).worldObj);

				if (nodeTile instanceof IBusway) {
					((IBusway) nodeTile).removeFromNetwork();
					newBusways.add((IBusway) nodeTile);
				}
			}

			EnergyBuswayNetwork newNetwork = new EnergyBuswayNetwork(newBusways.toArray(new IBusway[0]));
			newNetwork.refresh();
			newNetwork.fixed = true;
			deregister();
		}
	}

	public void removeBusway(IBusway busway) {

		busways.remove(busway);
		if (busways.size() == 0) {
			deregister();
		}
	}

	public void deregister() {

		busways.clear();
		EnergyBuswayNetworkRegistry.getInstance().registerNetwork(this);
	}

	public static class NetworkFinder {
		public World worldObj;
		public Object3D start;

		public List<Object3D> iterated = new ArrayList<Object3D>();
		public List<Object3D> toIgnore = new ArrayList<Object3D>();

		public NetworkFinder(World world, Object3D location, Object3D... ignore) {

			worldObj = world;
			start = location;

			if (ignore != null) {
				toIgnore = Arrays.asList(ignore);
			}
		}

		public void loopAll(Object3D location) {

			if (location.getTileEntity(worldObj) instanceof IBusway) {
				iterated.add(location);
			}

			for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
				Object3D obj = location.getFromSide(direction);

				if (!iterated.contains(obj) && !toIgnore.contains(obj)) {
					TileEntity tileEntity = obj.getTileEntity(worldObj);

					if (tileEntity instanceof IBusway) {
						loopAll(obj);
					}
				}
			}
		}

		public List<Object3D> exploreNetwork() {

			loopAll(start);

			return iterated;
		}
	}

	public static class NetworldLoader {

		@ForgeSubscribe
		public void onChunkLoad(ChunkEvent.Load event) {

			if (event.getChunk() != null) {
				for (Object obj : event.getChunk().chunkTileEntityMap.values()) {
					if (obj instanceof TileEntity) {

						TileEntity tileEntity = (TileEntity) obj;

						if (tileEntity instanceof IBusway) {
							((IBusway) tileEntity).refreshNetwork();
						}
					}
				}
			}
		}
	}

	@Override
	public String toString() {

		return "[EnergyBuswayNetwork] " + busways.size() + " busways, " + possibleAcceptors.size() + " acceptors.";
	}

	public void tick() {

		clearJoulesTransmitted();
		// Fix weird behaviour periodiclly.

		if (!fixed) {
			++ticksSinceCreate;
			if (ticksSinceCreate > 1200) {
				ticksSinceCreate = 0;
				fixMessedUpNetwork(busways.toArray(new IBusway[0])[0]);
			}
		}
	}

	public void clearJoulesTransmitted() {

		joulesLastTick = joulesTransmitted;
		joulesTransmitted = 0;
	}

	public double getPower() {

		return joulesLastTick * Reference.SECOND_IN_TICKS;
	}
}
