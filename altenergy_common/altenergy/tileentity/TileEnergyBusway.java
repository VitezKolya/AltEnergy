
package altenergy.tileentity;

import net.minecraft.block.Block;
import universalelectricity.prefab.tile.TileEntityConductor;
import altenergy.lib.Reference;

public class TileEnergyBusway extends TileEntityConductor {

	public static float RESISTANCE = 0.0001f;
	public static float MAX_AMPS = 5000000f;

	public TileEnergyBusway() {

		channel = Reference.CHANNEL_NAME;
	}

	@Override
	public double getResistance() {

		return RESISTANCE;
	}

	@Override
	public double getCurrentCapcity() {

		return MAX_AMPS;
	}

	@Override
	public void updateEntity() {

		super.updateEntity();

		if (this.getNetwork() != null && ticks % Reference.SECOND_IN_TICKS == 0) {
			if (this.getNetwork().getProduced().amperes > this.getCurrentCapcity()) {
				if (!worldObj.isRemote) {
					worldObj.setBlock(xCoord, yCoord, zCoord, Block.fire.blockID);
				}
			}
		}
	}
}
