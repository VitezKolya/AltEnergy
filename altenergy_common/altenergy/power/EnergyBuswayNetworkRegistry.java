
package altenergy.power;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class EnergyBuswayNetworkRegistry implements ITickHandler {
	private static EnergyBuswayNetworkRegistry INSTANCE = new EnergyBuswayNetworkRegistry();

	private HashSet<EnergyBuswayNetwork> networks = new HashSet<EnergyBuswayNetwork>();

	public EnergyBuswayNetworkRegistry() {

		TickRegistry.registerTickHandler(this, Side.SERVER);
	}

	public static EnergyBuswayNetworkRegistry getInstance() {

		return INSTANCE;
	}

	public void registerNetwork(EnergyBuswayNetwork network) {

		networks.add(network);
	}

	public void removeNetwork(EnergyBuswayNetwork network) {

		if (networks.contains(network)) {
			networks.remove(network);
		}
	}

	public void pruneEmptyNetworks() {

		for (EnergyBuswayNetwork e : networks) {
			if (e.busways.size() == 0) {
				removeNetwork(e);
			}
		}
	}

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {

		return;

	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {

		Set<EnergyBuswayNetwork> iterNetworks = (Set<EnergyBuswayNetwork>) networks.clone();
		for (EnergyBuswayNetwork net : iterNetworks) {
			if (networks.contains(net)) {
				net.tick();
			}
		}

	}

	@Override
	public EnumSet<TickType> ticks() {

		return EnumSet.of(TickType.SERVER);
	}

	@Override
	public String getLabel() {

		return "AltEnergy Energy Networks";
	}

	@Override
	public String toString() {

		return networks.toString();
	}
}
