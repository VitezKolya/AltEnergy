package altenergy.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import altenergy.AltEnergy;
import altenergy.core.misc.ItemAE;
import altenergy.lib.Strings;


public class ItemBase3D extends ItemAE{
	
	public ItemBase3D(int id) {

		super(id);
		setHasSubtypes(true);
		setCreativeTab(AltEnergy.tabsAE);
		this.setMaxStackSize(16);
		
		/*if(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".")) == Strings.ITEMS_3D[0]) {
			this.setMaxStackSize(16);
		} else if(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".")) == Strings.ITEMS_3D[1]) {
			this.setMaxStackSize(1);
		} else if(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".")) == Strings.ITEMS_3D[2]) {
			this.setMaxStackSize(64);
		}*/
	}

	@Override
	public void registerIcons(IconRegister register) {
		
	}
	
	@Override
	public void getSubItems(int id, CreativeTabs tabs, List itemList) {

		for (int counter = 0; counter <= Strings.ITEMS_3D.length - 1; ++counter) {
			itemList.add(new ItemStack(this, 1, counter));
		}
	}
	
	@Override
	public String getUnlocalizedName(ItemStack item) {

		return "item." + Strings.ITEMS_3D[item.getItemDamage()];
	}
}
