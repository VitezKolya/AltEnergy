package altenergy.tileentity;

import net.minecraft.block.Block;
import altenergy.lib.Reference;
import universalelectricity.prefab.tile.TileEntityConductor;

public class TileEnergyBusway extends TileEntityConductor{

	public static float RESISTANCE = 0.0001f;
	public static float MAX_AMPS = 5000000f;
	
	public TileEnergyBusway() {
		this.channel = Reference.CHANNEL_NAME;
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
		
		if(this.getNetwork() != null && this.ticks % Reference.SECOND_IN_TICKS == 0) {
			if(this.getNetwork().getProduced().amperes > this.getCurrentCapcity()) {
				if(!this.worldObj.isRemote) {
					this.worldObj.setBlock(this.xCoord, this.yCoord, this.zCoord, Block.fire.blockID);
				}
			}
		}
	}
}
