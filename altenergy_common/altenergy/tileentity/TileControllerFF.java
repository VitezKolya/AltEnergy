
package altenergy.tileentity;

import net.minecraft.tileentity.TileEntity;
import altenergy.configuration.ConfigurationSettings;

public class TileControllerFF extends TileEntity {

	public int maxHight;
	public int maxSpace;
	public int rotation;
	public boolean isVaild;

	public TileControllerFF() {

		maxHight = ConfigurationSettings.FORCEFIELD_DOOR_MAXHEIGHT;
		maxSpace = ConfigurationSettings.FORCEFIELD_DOOR_MAXWIDTH;
	}

	/**
	 * @return
	 */
	public boolean isValidStructure() {

		worldObj.getBlockId(xCoord, yCoord, zCoord + 1);
		worldObj.getBlockId(xCoord, yCoord, zCoord - 1);
		worldObj.getBlockId(xCoord - 1, yCoord, zCoord);
		worldObj.getBlockId(xCoord + 1, yCoord, zCoord);
		worldObj.getBlockId(xCoord, yCoord + 1, zCoord + 1);
		worldObj.getBlockId(xCoord, yCoord - 1, zCoord + 1);

		// if(northID == BlockIds.)
		return false;
	}

	public void removeStructureTiles() {

	}

	public void setStructureTiles() {

	}
}
