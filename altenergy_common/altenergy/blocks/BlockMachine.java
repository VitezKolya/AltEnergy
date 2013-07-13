
package altenergy.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockMachine extends BlockContainer {

	protected BlockMachine(int id, Material mat) {

		super(id, mat);
		// TODO Auto-generated constructor stub
	}

	@Override
	public TileEntity createNewTileEntity(World world) {

		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("deprecation")
	public TileEntity getTileEntity(IBlockAccess world, int x, int y, int z) {

		if (hasTileEntity()) {
			return world.getBlockTileEntity(x, y, z);
		} else {
			return null;
		}
	}

}
