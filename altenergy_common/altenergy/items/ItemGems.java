
package altenergy.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import altenergy.AltEnergy;
import altenergy.core.misc.ItemAE;
import altenergy.lib.Strings;

public class ItemGems extends ItemAE {

	public ItemGems(int id) {

		super(id);
		setHasSubtypes(true);
		setCreativeTab(AltEnergy.tabsAE);

		if (this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".")) == Strings.GEMS[0]) {
			setMaxStackSize(16);
		} else if (this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".")) == Strings.GEMS[1]) {
			setMaxStackSize(1);
		} else if (this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".")) == Strings.GEMS[2]) {
			setMaxStackSize(64);
		}
	}

	@Override
	public void registerIcons(IconRegister register) {

	}

	@Override
	public void getSubItems(int id, CreativeTabs tabs, List itemList) {

		for (int counter = 0; counter <= Strings.GEMS.length - 1; ++counter) {
			itemList.add(new ItemStack(this, 1, counter));
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack item) {

		return "item.gem" + Strings.GEMS[item.getItemDamage()];
	}
}
