
package altenergy.content.conductors;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import universalelectricity.core.block.IConnector;
import universalelectricity.core.block.INetworkProvider;
import universalelectricity.prefab.tile.TileEntityConductor;
import altenergy.lib.Reference;

public class TileEnergyBusway extends TileEntityConductor {

	public TileEnergyBusway() {

		channel = Reference.CHANNEL_NAME;
	}

	@Override
	public double getResistance() {

		return 0;
	}

	@Override
	public double getCurrentCapcity() {

		return 5000000;
	}

	public void printConnections() {

		System.out.println("Bottom: " + visuallyConnected[0]);
		System.out.println("Top: " + visuallyConnected[1]);
		System.out.println("Front: " + visuallyConnected[2]);
		System.out.println("Back: " + visuallyConnected[3]);
		System.out.println("Left: " + visuallyConnected[4]);
		System.out.println("Right: " + visuallyConnected[5]);
	}

	@Override
	public void updateConnection(TileEntity tileEntity, ForgeDirection side) {

		if (!worldObj.isRemote) {
			if (tileEntity instanceof TileEnergyBusway) {
				if (tileEntity instanceof IConnector) {
					if (((IConnector) tileEntity).canConnect(side.getOpposite())) {
						connectedBlocks[side.ordinal()] = tileEntity;
						visuallyConnected[side.ordinal()] = true;

						if (tileEntity.getClass() == this.getClass() && tileEntity instanceof INetworkProvider) {
							getNetwork().mergeConnection(((INetworkProvider) tileEntity).getNetwork());
						}

						return;
					}
				}

				if (connectedBlocks[side.ordinal()] != null) {
					getNetwork().stopProducing(connectedBlocks[side.ordinal()]);
					getNetwork().stopRequesting(connectedBlocks[side.ordinal()]);
				}

				connectedBlocks[side.ordinal()] = null;
				visuallyConnected[side.ordinal()] = false;
			}
		}
	}

	public int getNumConnections() {

		int count = 0;

		for (int side = 0; side < visuallyConnected.length; side++) {
			if (visuallyConnected[side]) {
				count++;
			}
		}
		return count;
	}
}
