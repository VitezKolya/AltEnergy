package altenergy.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import altenergy.tileentity.TE_PCController;

public class BlockPCController extends BlockMachine {

	static boolean debugMerge = false;

	static final int mergedBit = 0x8;

	static int southSide[] = {
			3, 5, 2, 4
	};
	static int unitX[] = {
			1, 0, -1, 0
	};
	static int unitZ[] = {
			0, -1, 0, 1
	};

	/*
	 * - Generator platform - 9 : 40 
	 * - Generator Cap - 8 : 40
	 * - Generator Support Frame - 7 : 12
	 * - Core Supports - 6 : 28
	 * - Core Platform - 5 : 1
	 * - Core Cap - 4 : 1
	 * - Regulator - 3 : 1 - Can be placed anywhere
	 * - Controller - ComputerCraft Enabled - 1 : 1
	 * - Emergency Vent - 2 : 1
	 */

	static int pattern[][][] = {
			{
					{
							9, 9, 9, 9, 9
					}, {
							9, 9, 9, 9, 9
					}, {
							9, 9, 9, 9, 9
					}, {
							9, 9, 9, 9, 9
					}, {
							9, 9, 9, 9, 9
					}
			},

			{
					{
							9, 9, 9, 9, 9
					}, {
							9, 6, 6, 6, 9
					}, {
							9, 6, 5, 6, 9
					}, {
							9, 6, 6, 6, 9
					}, {
							9, 9, 1, 9, 9
					}
			},

			{
					{
							7, 0, 0, 0, 7
					}, {
							0, 6, 0, 6, 0
					}, {
							0, 0, 0, 0, 0
					}, {
							0, 6, 0, 6, 0
					}, {
							7, 0, 0, 0, 7
					}
			},

			{
					{
							7, 0, 0, 0, 7
					}, {
							0, 6, 0, 6, 0
					}, {
							0, 0, 0, 0, 0
					}, {
							0, 6, 0, 6, 0
					}, {
							7, 0, 0, 0, 7
					}
			},

			{
					{
							7, 0, 0, 0, 7
					}, {
							0, 6, 0, 6, 0
					}, {
							0, 0, 0, 0, 0
					}, {
							0, 6, 0, 6, 0
					}, {
							7, 0, 0, 0, 7
					}
			},

			{
					{
							8, 8, 8, 8, 8
					}, {
							8, 6, 6, 6, 8
					}, {
							8, 6, 4, 6, 8
					}, {
							8, 6, 6, 6, 8
					}, {
							8, 8, 8, 8, 8
					}
			},

			{
					{
							8, 8, 8, 8, 8
					}, {
							8, 8, 8, 8, 8
					}, {
							8, 8, 2, 8, 8
					}, {
							8, 8, 8, 8, 8
					}, {
							8, 8, 8, 8, 8
					}
			},
	};

	protected BlockPCController(int id) {
		super(id, Material.iron);
		// TODO Auto-generated constructor stub
	}

	public boolean isMerged(IBlockAccess world, int x, int y, int z) {
		// TODO Auto-generated method stub
		return false;
	}

	public void checkForMerge(World world, int x, int y, int z) {
		// expecting the first level to below the controller
		for (int localY = -1; localY < 3; localY++) {
			for (int localX = -2; localX < 2; localX++) {
				for (int localZ = -2; localZ < 2; localZ++) {

					// If coords is the same as the controller don't do anything
					if (!(localY == 0 && localX == 0 && localZ == 0)) {
						int modX = localX + x;
						int modY = localY + y;
						int modZ = localZ + z;

						int type = getBlockType(world, modX, modY, modZ);

						int pat = pattern[1 + localY][2 + localZ][1 + localX];

						// if pat result isn't air and pat isn't in type exit
						// function
						if (pat != 0 && type != pat) {
							return;
						}
					}
				}
			}
		}

		TE_PCController tileEntity = (TE_PCController) this.getTileEntity(world, x, y, z);
		tileEntity.isMerged = true;
		world.markBlockForUpdate(x, y, z);
		for (int localY = -1; localY < 3; localY++) {
			for (int localX = -2; localX < 2; localX++) {
				for (int localZ = -2; localZ < 2; localZ++) {
					int modX = localX + x;
					int modY = localY + y;
					int modZ = localZ + z;
					int id = world.getBlockId(modX, modY, modZ);

				}
			}
		}
	}

	private int getBlockType(World world, int modX, int modY, int modZ) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void unmerge(World world, int x, int y, int z) {
		// TODO Auto-generated method stub

	}

}
