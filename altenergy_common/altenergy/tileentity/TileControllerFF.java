package altenergy.tileentity;

import altenergy.configuration.ConfigurationSettings;
import altenergy.lib.BlockIds;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;

public class TileControllerFF extends TileEntity{

	public int maxHight;
	public int maxSpace;
	public int rotation;
	public boolean isVaild;
	private int[] powerRegulatorLoc = {0,0,0};
	
	public TileControllerFF() {
		
		this.maxHight = ConfigurationSettings.FORCEFIELD_DOOR_MAXHEIGHT;
		this.maxSpace = ConfigurationSettings.FORCEFIELD_DOOR_MAXWIDTH;
	}
	
	/**
	 * @return
	 */
	public boolean isValidStructure() {
		
		int northID = worldObj.getBlockId(this.xCoord, this.yCoord, this.zCoord + 1);
		int southID = worldObj.getBlockId(this.xCoord, this.yCoord, this.zCoord - 1);
		int westID = worldObj.getBlockId(this.xCoord - 1, this.yCoord, this.zCoord);
		int eastID = worldObj.getBlockId(this.xCoord + 1, this.yCoord, this.zCoord);
		int topID = worldObj.getBlockId(this.xCoord, this.yCoord + 1, this.zCoord + 1);
		int bottomID = worldObj.getBlockId(this.xCoord, this.yCoord - 1, this.zCoord + 1);
		
		//if(northID == BlockIds.)
		return false;
	}
	
	public void removeStructureTiles() {
		
	}
	
	public void setStructureTiles() {
		
	}
}
