
package altenergy.blocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import altenergy.common.BlockContainerAE;
import altenergy.tileentity.TETestEntity;

public class BlockBaseTileEntity extends BlockContainerAE {

	public BlockBaseTileEntity(int id) {

		super(id, Material.iron);
		// TODO Auto-generated constructor stub
	}

	@Override
	public TileEntity createNewTileEntity(World world) {

		// TODO Auto-generated method stub
		return new TETestEntity();
	}

	public int idDroped(int meta, Random rand, int i) {

		return 0;
	}

	@Override
	public int quantityDropped(Random rand) {

		return 0;
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, int i, int j) {

		world.removeBlockTileEntity(x, y, z);
	}
}
