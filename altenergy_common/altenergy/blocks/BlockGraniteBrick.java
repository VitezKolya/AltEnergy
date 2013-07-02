package altenergy.blocks;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import altenergy.AltEnergy;
import altenergy.lib.Reference;
import altenergy.lib.Strings;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockGraniteBrick extends BlockAE {

	public Icon[] icons;

	public BlockGraniteBrick(int id) {

		super(id, Material.rock);
		this.setUnlocalizedName(Strings.BLOCK_GRANITE_BRICKS_NAME);
		this.setCreativeTab(AltEnergy.tabsAE);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {

		icons = new Icon[Strings.BLOCK_GRANITE_BRICKS_SUBNAMES.length];

		for (int meta = 0; meta < icons.length; meta++) {
			icons[meta] = iconRegister.registerIcon(Reference.MOD_ID + ":" + this.getUnlocalizedName2()
					+ Strings.BLOCK_GRANITE_BRICKS_SUBNAMES[meta]);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta) {

		return icons[meta % icons.length];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int id, CreativeTabs creativeTab, List list) {
		for (int meta = 0; meta < icons.length; meta++) {
			list.add(new ItemStack(id, 1, meta));
		}
	}
}
