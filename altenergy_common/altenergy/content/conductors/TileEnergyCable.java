
package altenergy.content.conductors;

import altenergy.lib.Reference;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import universalelectricity.core.block.IConnector;
import universalelectricity.core.block.INetworkProvider;
import universalelectricity.prefab.tile.TileEntityConductor;

public class TileEnergyCable extends TileEntityConductor {

	public TileEnergyCable() {

		channel = Reference.CHANNEL_NAME;
	}
	
	@Override
	public double getResistance() {

		return 0.0001;
	}

	@Override
	public double getCurrentCapcity() {

		return 250;
	}

	@Override
	public void updateConnection(TileEntity tileEntity, ForgeDirection side) {

		if (!worldObj.isRemote) {
			if(tileEntity instanceof TileEnergyCable) {
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
}
