package altenergy.core.misc;

import altenergy.content.controllers.TileControllerBase;
import universalelectricity.prefab.tile.TileEntityElectrical;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;


public class TilePowerCoreBase extends TileEntityElectrical{
	
	public static int MAX_GENERATE_WATTS;

	public static int MIN_GENERATE_WATTS;

	public static int MAX_OVERLOAD;

	public static float MAX_OUTPUT_SETTING;

	public static int VOLTAGE;
	
	public TileControllerBase controller;
	
	public boolean isLinked;
	
	/**
	 * Linked Coords to controller
	 */
	public int linkedX, linkedY, linkedZ;
	public PowerCoreState state;
	public float outputLevel = 0.0F;
	
	public void markBlockForUpdate() {
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		//state = PowerCoreState.Idle;
	}

	@Override
	public boolean canConnect(ForgeDirection direction) {
		
		return false;
	}
	
	/**
	 * Gets the current status of powercore
	 * @return PowerCoreState
	 */
	public PowerCoreState getStatus() {
		return state;
	}
	
	/**
	 * Gets what the output level is set to
	 * @return float outputLevel
	 */
	public float getOutptuLevel() {
		return outputLevel;
	}
	
	/**
	 * Sets the output level with regards to the maximum output level
	 * Also sets the state of the powercore based on the output level.
	 * @param level
	 */
	public void setOutputLevel(float level) {
		if (level <= MAX_OUTPUT_SETTING) {
			if (level >= 1) {

				state = PowerCoreState.Generate;
			} else if (level == 0.0F) {

				state = PowerCoreState.Idle;
			} else if (level > 1.0F) {

				state = PowerCoreState.Overload;
			}

			outputLevel = level;
		} else if (level < 0.0F) {

			level = 0.0F;
		} else {

			outputLevel = MAX_OUTPUT_SETTING;
		}
	}
	
	/**
	 * Gets the linked controller
	 * @return TileControllerBase
	 */
	public TileControllerBase getLinked() {
		return this.controller;
	}
	
	/**
	 * Tests if things are linked
	 * @return
	 */
	public boolean isLinked() {

		if (controller == null && !isLinked) {
			return false;
		}

		return true;
	}
	
	/**
	 * Unlinks Controller from powercore and clears powercore link too.
	 */
	public void unlinkController() {
		if(this.isLinked) {
			TileControllerBase controller = getLinked();
			controller.clearLink();
			this.clearLink();
		}
	}
	
	/**
	 * Finds controller and sets it to this.controller
	 * @return true if found false if not.
	 */
	public boolean findController() {
		if(isLinked) {
			TileEntity tileEntity = this.worldObj.getBlockTileEntity(linkedX, linkedY, linkedZ);
			if(tileEntity instanceof TileControllerBase) {
				this.controller = (TileControllerBase) tileEntity;
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Links to controller
	 * @param controller
	 */
	public void linkTo(TileControllerBase controller) {
		System.out.printf("PowerCore: linking powercore at (%d, %d, %d) to controller at (%d, %d, %d)\n",
				xCoord, yCoord, zCoord, controller.xCoord, controller.yCoord, controller.zCoord);
		this.controller = controller;
		this.linkedX = controller.xCoord;
		this.linkedY = controller.yCoord;
		this.linkedZ = controller.zCoord;
		this.isLinked = true;
	}
	
	/**
	 * Clears link to controller
	 */
	public void clearLink() {
		System.out.printf("PowerCore: Unlinking powercore at (%d, %d, %d) from controller\n",
				xCoord, yCoord, zCoord);
		controller = null;
		isLinked = false;
		this.markBlockForUpdate();
	}
}
