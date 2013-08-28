
package altenergy.content.controllers;

import net.minecraft.tileentity.TileEntity;

public class TileControllerBase extends TileEntity {

	public int linkRangeX;
	public int linkRangeY;
	public int linkRangeZ;

	/**
	 * Linked Coords to controlled
	 */
	public int linkedX, linkedY, linkedZ;
	public boolean isLinked;
	public TileEntity controlled;

	public void markBlockForUpdate() {

		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}

	/**
	 * @return boolean
	 */
	public boolean isLinked() {

		return isLinked;
	}

	/**
	 * Returns controlled if linked
	 * 
	 * @return TileEntity
	 */
	public TileEntity getLinked() {

		if (isLinked) {
			return controlled;
		}
		return null;
	}

	/**
	 * Find controlled and set it to this.controlled
	 * 
	 * @return boolean
	 */
	public boolean findControlled() {

		return false;
	}

	/**
	 * Links controller to controlled
	 * 
	 * @return boolean
	 */
	public boolean linkToControlled() {

		if (!isLinked) {
			// System.out.printf("TileControllerBase: Linking controller at (%d, %d, %d) with target at (%d, %d, %d)\n",
			// xCoord, yCoord, zCoord, tile.xCoord, tile.yCoord, tile.zCoord);
		}
		return false;
	}

	/**
	 * Clears link to controlled
	 */
	public void clearLink() {

		System.out.printf("Controller: Unlinking controller at (%d, %d, %d) from target\n", xCoord, yCoord, zCoord);
		controlled = null;
		isLinked = false;
		markBlockForUpdate();
	}
}
