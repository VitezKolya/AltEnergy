
package altenergy.content.controllers;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import altenergy.core.misc.PowerCoreState;
import altenergy.core.misc.TilePowerCoreBase;

public class TileControllerPC extends TileControllerBase {

	public int outputLevel;

	public TileControllerPC() {

		System.out.println();

		linkRangeX = 9;
		linkRangeY = 4;
		linkRangeZ = 10;

		if (isLinked) {
			findControlled();
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {

		super.readFromNBT(nbt);
		isLinked = nbt.getBoolean("isLinkedToPowerCore");
		linkedX = nbt.getInteger("linkedX");
		linkedY = nbt.getInteger("linkedY");
		linkedZ = nbt.getInteger("linkedZ");
		outputLevel = nbt.getInteger("outputLevel");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {

		super.writeToNBT(nbt);
		nbt.setBoolean("isLinkedToPowerCore", isLinked);
		nbt.setInteger("linkedX", linkedX);
		nbt.setInteger("linkedY", linkedY);
		nbt.setInteger("linkedZ", linkedZ);
		nbt.setInteger("outputLevel", outputLevel);
	}

	public PowerCoreState getStatus() {

		if (isLinked) {
			if (controlled instanceof TilePowerCoreBase) {
				return ((TilePowerCoreBase) controlled).getStatus();
			}
		}

		return null;
	}

	/**
	 * Set the output level of PowerCore
	 * 
	 * @param level
	 */
	public void changeOutputLevel(int level) {

		if (isLinked) {
			if (controlled instanceof TilePowerCoreBase) {
				((TilePowerCoreBase) controlled).setOutputLevel(level);
			}
		}
	}

	@Override
	public boolean findControlled() {

		if (isLinked) {
			TileEntity tileEntity = worldObj.getBlockTileEntity(linkedX, linkedY, linkedZ);
			if (tileEntity instanceof TilePowerCoreBase) {
				controlled = tileEntity;
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean linkToControlled() {

		TileEntity tileEntity;
		System.out.println("Searching for powercores");
		for (int x = -linkRangeX; x < linkRangeX; x++) {
			for (int y = -linkRangeY; y < linkRangeY; y++) {
				for (int z = -linkRangeZ; z < linkRangeZ; z++) {

					tileEntity = worldObj.getBlockTileEntity(xCoord +x, yCoord + y, zCoord + z);
					if(tileEntity != null) {
						System.out.println(tileEntity);
						System.out.println("x: " + (tileEntity.xCoord + x) + " y: " + (tileEntity.yCoord + y) + " z: " + (tileEntity.zCoord + z));
					}
					if (tileEntity instanceof TilePowerCoreBase) {
						System.out.println("Found a powercore does it have a link already?");
						if (!((TilePowerCoreBase) tileEntity).isLinked()) {
							System.out.println("Powercore doesn't havea a link");
							System.out.printf("TileControllerBase: Linking controller at (%d, %d, %d) with powercore at (%d, %d, %d)\n",
											xCoord, yCoord, zCoord, tileEntity.xCoord, tileEntity.yCoord,
											tileEntity.zCoord);
							controlled = tileEntity;
							((TilePowerCoreBase) controlled).linkTo(this);
							linkedX = tileEntity.xCoord;
							linkedY = tileEntity.yCoord;
							linkedZ = tileEntity.zCoord;
							isLinked = true;
							return true;
						}
					}
				}
			}
		}

		System.out.println("No powercores found");
		return false;
	}
	
	@Override
	public void clearLink() {

		System.out.printf("Controller: Unlinking controller at (%d, %d, %d) from target\n", xCoord, yCoord, zCoord);
		((TilePowerCoreBase)controlled).clearLink();
		controlled = null;
		isLinked = false;
		markBlockForUpdate();
	}
}
