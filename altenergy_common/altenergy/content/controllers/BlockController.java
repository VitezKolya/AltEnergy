
package altenergy.content.controllers;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import altenergy.AltEnergy;
import altenergy.core.misc.BlockAE;
import altenergy.lib.Reference;
import altenergy.lib.Strings;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockController extends BlockAE {

	public BlockController(int id) {

		super(id, Material.iron);
		setCreativeTab(AltEnergy.tabsAE);
		setUnlocalizedName(Strings.BLOCK_CONTROLLER);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {

		iconRegister.registerIcon(Reference.MOD_ID.toLowerCase() + ":" + Strings.BLOCK_CONTROLLER);
	}
}
