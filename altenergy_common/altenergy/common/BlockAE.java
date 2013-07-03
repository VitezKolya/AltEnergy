package altenergy.common;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import altenergy.AltEnergy;
import altenergy.lib.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class BlockAE extends Block {

	public BlockAE(int id, Material material) {
		super(id, material);
		this.setCreativeTab(AltEnergy.tabsAE);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister register) {

		register.registerIcon(Reference.MOD_ID + ":"
				+ this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
	}
}
