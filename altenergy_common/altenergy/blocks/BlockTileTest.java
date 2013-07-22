
package altenergy.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import altenergy.common.BlockContainerAE;
import altenergy.tileentity.TileTest;

public class BlockTileTest extends BlockContainerAE {

	public BlockTileTest(int id) {

		super(id, Material.iron);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {

		return new TileTest();
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, int id, int meta) {

		if (world.getBlockTileEntity(x, y, z) instanceof TileTest) {
			world.markBlockForUpdate(x, y, z);
			world.updateAllLightTypes(x, y, z);
		}

		super.breakBlock(world, x, y, z, id, meta);
	}
}
