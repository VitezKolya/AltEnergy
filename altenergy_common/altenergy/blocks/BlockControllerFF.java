package altenergy.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import altenergy.AltEnergy;
import altenergy.lib.Reference;
import altenergy.lib.Strings;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockControllerFF extends BlockContainerAE {

	protected BlockControllerFF(int id) {

		super(id, Material.iron);
		this.setCreativeTab(AltEnergy.tabsAE);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world) {

		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {

		iconRegister.registerIcon(Reference.MOD_ID.toLowerCase() + ":" + Strings.BLOCK_CONTROLLER_FF);
	}
}
