
package altenergy.core.util;

import net.minecraft.world.World;

/*
 * Inspired/copied from Yagoki's github
 * https://github.com/Yagoki
 */
public abstract class MultiblockStructureUtil {

	public final int masterX, masterY, masterZ;
	private boolean isValid;
	private int rotation;
	protected final World world;

	/**
	 * The structure should be made in the master block, then inherited by the
	 * other blocks in the structure.
	 * 
	 * @param masterX
	 *            The world x coordz of the master block
	 * @param masterY
	 *            The world y coordz of the master block
	 * @param masterZ
	 *            The world z coordz of the master block
	 * @param rotation
	 *            Rotation of the structure not sure if will be using this.
	 * @param isValid
	 *            If the multiblock is valid
	 * @param world
	 *            the world object
	 */
	public MultiblockStructureUtil(int masterX, int masterY, int masterZ, int rotation, boolean isValid, World world) {

		this.masterX = masterX;
		this.masterY = masterY;
		this.masterZ = masterZ;
		this.rotation = rotation;
		this.isValid = isValid;
		this.world = world;

		this.rotation = 0;
	}

	public abstract int[][][] getFinalStructure();

	public abstract boolean BlockWildCard(int arrNum, int blockID);

	public abstract int[][][] getInitializingStructure();

	public abstract int[] getMasterBlockLocalCoords();

	public int[] getMasterBlockLocalCoords(int rot) {

		rot %= 4;

		int x = this.getMasterBlockWorldCoords()[0];
		int y = this.getMasterBlockWorldCoords()[1];
		int z = this.getMasterBlockWorldCoords()[2];

		int lenX, lenZ;

		if (rotation % 2 == 0) {
			lenX = this.getInitializingStructure().length;
			lenZ = this.getInitializingStructure()[0][0].length;
		} else {
			lenX = this.getInitializingStructure()[0][0].length;
			lenZ = this.getInitializingStructure().length;
		}

		switch (rot) {
			case 1:
				return new int[] {
						lenZ - 1 - z, y, x
				};
			case 2:
				return new int[] {
						lenX - 1 - x, y, z
				};
			case 3:
				return new int[] {
						z, y, lenX - 1 - x
				};
			default:
				return this.getMasterBlockWorldCoords();
		}
	}

	public final int[][][] getStructure(int rot, int[][][] structure) {

		rotation %= 4;

		if (rotation == 0) {
			return structure;
		}

		return null;
	}

	public int[] getMasterBlockWorldCoords() {

		return new int[] {
				masterX, masterY, masterZ
		};
	}

	public boolean isValidStructure() {

		int[][][] structure;
		// int[][][] metadata; // Currently unused
		rot: for (int rotate = 0; rotate < 4; rotate++) {
			structure = this.getStructure(rotate, isValid ? this.getFinalStructure() : this.getInitializingStructure());

			for (int x = 0; x < structure.length; x++) {
				int xCoord = masterX - (this.getMasterBlockWorldCoords()[0] - x);

				for (int y = 0; y < structure[x].length; y++) {
					int yCoord = masterY - (this.getMasterBlockWorldCoords()[1] - y);

					for (int z = 0; z < structure[x][y].length; z++) {
						int zCoord = masterZ - (this.getMasterBlockWorldCoords()[2] - z);

						int id = world.getBlockId(xCoord, yCoord, zCoord);

						if (structure[x][y][z] == Integer.MIN_VALUE || this.BlockWildCard(structure[x][y][z], id)) {

							continue;
						} else {
							continue rot;
						}
					}
				}
			}
			isValid = true;
			rotation = rotate;
			return true;
		}

		isValid = false;
		rotation = 0;
		return false;
	}

	public void removeStructureTiles() {

		int[][][] initStructure = this.getStructure(rotation, getInitializingStructure());
		int[][][] finalStructure = this.getStructure(rotation, getFinalStructure());

		for (int x = 0; x < initStructure.length; x++) {
			int xCoord = masterX - (this.getMasterBlockLocalCoords(rotation)[0] - x);

			for (int y = 0; y < initStructure[x].length; y++) {
				int yCoord = masterY - (this.getMasterBlockLocalCoords(rotation)[1] - y);

				for (int z = 0; z < initStructure[x][y].length; z++) {
					int zCoord = masterZ - (this.getMasterBlockLocalCoords(rotation)[2] - z);

					if (xCoord == masterX && yCoord == masterY && zCoord == masterZ) {
						continue;
					}

					if (world.getBlockId(xCoord, yCoord, zCoord) != finalStructure[x][y][z]
							|| finalStructure[x][y][z] == Integer.MIN_VALUE) {
						continue;
					}

					world.setBlock(xCoord, yCoord, zCoord, initStructure[x][y][z]);
				}
			}
		}
	}

	public void setStructureTiles() {

		int[][][] finalStructure = this.getStructure(rotation, getFinalStructure());
		// 3d meta array

		for (int x = 0; x < finalStructure.length; x++) {
			int xCoord = masterX - (this.getMasterBlockLocalCoords(rotation)[0] - x);

			for (int y = 0; y < finalStructure[x].length; y++) {
				int yCoord = masterY - (this.getMasterBlockLocalCoords(rotation)[1] - y);

				for (int z = 0; z < finalStructure[x][y].length; z++) {
					int zCoord = masterZ - (this.getMasterBlockLocalCoords(rotation)[2] - z);

					world.setBlock(xCoord, yCoord, zCoord, finalStructure[x][y][z]);
					// meta set here
					this.sendStructureToTile(xCoord, yCoord, zCoord, finalStructure[x][y][z]);
				}
			}
		}
	}

	public abstract void sendStructureToTile(int x, int y, int z, int blockID);
}
