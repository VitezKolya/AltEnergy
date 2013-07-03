package altenergy.common;

import net.minecraft.world.World;
import altenergy.core.util.MultiblockStructureUtil;

public class StructurePowerCore extends MultiblockStructureUtil {

	public StructurePowerCore(int masterX, int masterY, int masterZ, int rotation, boolean isValid, World world) {
		super(masterX, masterY, masterZ, rotation, isValid, world);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int[][][] getFinalStructure() {

		int A = 0, // Air id
		P = 1, // Bottom Base Blocks
		S = 1, // Base model
		E = 1, // Top Model
		T = 1, // Top Blocks
		C = 1; // Core Block

		int pattern[][][] = {
				{
					{
							T, T, T, T, T
					}, {
							T, T, T, T, T
					}, {
							A, A, A, A, A
					}, {
							A, A, A, A, A
					}, {
							A, A, A, A, A
					}, {
							P, P, P, P, P
					}, {
							P, P, P, P, P
					}
			},

			{
					{
							T, T, T, T, T
					}, {
							T, S, S, S, T
					}, {
							A, A, A, A, A
					}, {
							A, A, A, A, A
					}, {
							A, A, A, A, A
					}, {
							P, S, S, S, P
					}, {
							P, P, P, P, P
					}
			},

			{
					{
							T, T, T, T, T
					}, {
							T, S, E, S, T
					}, {
							A, A, A, A, A
					}, {
							A, A, A, A, A
					}, {
							A, A, A, A, A
					}, {
							P, S, C, S, P
					}, {
							P, P, P, P, P
					}
			},

			{
					{
							T, T, T, T, T
					}, {
							T, S, S, S, T
					}, {
							A, A, A, A, A
					}, {
							A, A, A, A, A
					}, {
							A, A, A, A, A
					}, {
							P, S, S, S, P
					}, {
							P, P, P, P, P
					}
			},

			{
					{
							T, T, T, T, T
					}, {
							T, T, T, T, T
					}, {
							A, A, A, A, A
					}, {
							A, A, A, A, A
					}, {
							A, A, A, A, A
					}, {
							P, P, P, P, P
					}, {
							P, P, P, P, P
					}
			}
	};

		return pattern;
	}

	@Override
	public boolean BlockWildCard(int arrNum, int blockID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int[][][] getInitializingStructure() {
		int A = 0, P = 1, // Main platform/base
		C = 1, // Main cap
		R = 1, // Power Regulator
		S = 1, // Core supports
		T = 1, // Core Base/platform
		E = 1, // Core Cap
		V = 1; // Emergency vent

		int pattern[][][] = {
				{
						{
								T, T, T, T, T
						}, {
								T, T, T, T, T
						}, {
								A, A, A, A, A
						}, {
								A, A, A, A, A
						}, {
								A, A, A, A, A
						}, {
								P, P, P, P, P
						}, {
								P, P, P, P, P
						}
				},

				{
						{
								T, T, T, T, T
						}, {
								T, S, S, S, T
						}, {
								A, A, A, A, A
						}, {
								A, A, A, A, A
						}, {
								A, A, A, A, A
						}, {
								P, S, S, S, P
						}, {
								P, P, P, P, P
						}
				},

				{
						{
								T, T, V, T, T
						}, {
								T, S, E, S, T
						}, {
								A, A, A, A, A
						}, {
								A, A, A, A, A
						}, {
								A, A, A, A, A
						}, {
								P, S, C, S, P
						}, {
								P, P, R, P, P
						}
				},

				{
						{
								T, T, T, T, T
						}, {
								T, S, S, S, T
						}, {
								A, A, A, A, A
						}, {
								A, A, A, A, A
						}, {
								A, A, A, A, A
						}, {
								P, S, S, S, P
						}, {
								P, P, P, P, P
						}
				},

				{
						{
								T, T, T, T, T
						}, {
								T, T, T, T, T
						}, {
								A, A, A, A, A
						}, {
								A, A, A, A, A
						}, {
								A, A, A, A, A
						}, {
								P, P, P, P, P
						}, {
								P, P, P, P, P
						}
				}
		};
		return pattern;
	}

	@Override
	public int[] getMasterBlockLocalCoords() {
		return new int[]{2,1,2};
	}

	@Override
	public void sendStructureToTile(int x, int y, int z, int blockID) {
		//TileEntityAlterDummy te = new TileEntityAlterDummy(); 
	}

}
