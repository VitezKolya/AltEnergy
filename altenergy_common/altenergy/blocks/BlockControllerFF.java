
package altenergy.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import altenergy.AltEnergy;
import altenergy.lib.Reference;
import altenergy.lib.Strings;
import altenergy.tileentity.TileControllerFF;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockControllerFF extends BlockContainerAE {

	protected BlockControllerFF(int id) {

		super(id, Material.iron);
		this.setCreativeTab(AltEnergy.tabsAE);
		this.setUnlocalizedName(Strings.BLOCK_CONTROLLER_FF);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {

		// TODO Auto-generated method stub
		return new TileControllerFF();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {

		iconRegister.registerIcon(Reference.MOD_ID.toLowerCase() + ":" + Strings.BLOCK_CONTROLLER_FF);
	}
}
