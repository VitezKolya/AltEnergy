
package altenergy.core.misc;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import altenergy.AltEnergy;
import altenergy.lib.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class ItemAE extends Item {

	public ItemAE(int id) {

		super(id - Reference.SHIFTED_ID_RANGE_CORRECTION);
		setCreativeTab(AltEnergy.tabsAE);
		setMaxStackSize(64);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {

		itemIcon = iconRegister.registerIcon(Reference.MOD_ID.toLowerCase() + ":"
				+ this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
	}
}
