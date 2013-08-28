
package altenergy.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import altenergy.AltEnergy;
import altenergy.core.misc.ItemAE;
import altenergy.lib.Reference;
import altenergy.lib.Strings;

public class ItemDusts extends ItemAE {

	public Icon[] icons;

	public ItemDusts(int id) {

		super(id);
		setHasSubtypes(true);
		setCreativeTab(AltEnergy.tabsAE);
	}

	@Override
	public void registerIcons(IconRegister register) {

		icons = new Icon[Strings.ITEM_DUSTS.length];
		for (int meta = 0; meta < icons.length - 1; meta++) {
			icons[meta] = register.registerIcon(Reference.MOD_ID + ":" + "dust" + Strings.ITEM_DUSTS[meta]);
		}
	}

	@Override
	public Icon getIconFromDamage(int meta) {

		return icons[meta];
	}

	@Override
	public void getSubItems(int id, CreativeTabs tabs, List itemList) {

		for (int counter = 0; counter <= Strings.ITEM_DUSTS.length - 1; ++counter) {
			itemList.add(new ItemStack(this, 1, counter));
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack item) {

		return "item.dust" + Strings.ITEM_DUSTS[item.getItemDamage()];
	}

}
