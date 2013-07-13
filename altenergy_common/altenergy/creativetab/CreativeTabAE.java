
package altenergy.creativetab;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CreativeTabAE extends CreativeTabs {

	public CreativeTabAE(int par1, String par2Str) {

		super(par1, par2Str);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getTabIconItemIndex() {

		return Block.stone.blockID;
	}
}
