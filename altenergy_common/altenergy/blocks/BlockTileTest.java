
package altenergy.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import altenergy.AltEnergy;
import altenergy.lib.Reference;
import altenergy.lib.Strings;
import altenergy.tileentity.TileTest;

public class BlockTileTest extends BlockContainerAE {
	
	public BlockTileTest(int id) {

		super(id, Material.iron);
		this.setCreativeTab(AltEnergy.tabsAE);
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
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {

		iconRegister.registerIcon(Reference.MOD_ID.toLowerCase() + ":" + Strings.BLOCK_GRANITE_NAME);
	}
}
