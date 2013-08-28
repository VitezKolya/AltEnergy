
package altenergy.content.controllers;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import altenergy.AltEnergy;
import altenergy.core.misc.BlockContainerAE;
import altenergy.lib.Strings;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockControllerFF extends BlockContainerAE {

	public BlockControllerFF(int id) {

		super(id, Material.iron);
		setCreativeTab(AltEnergy.tabsAE);
		setUnlocalizedName(Strings.BLOCK_CONTROLLER_FF);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {

		// TODO Auto-generated method stub
		return new TileControllerFF();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {

		// iconRegister.registerIcon(Reference.MOD_ID.toLowerCase() + ":" +
		// Strings.BLOCK_CONTROLLER_FF);
	}
}
