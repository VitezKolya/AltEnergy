
package altenergy.items;

import net.minecraft.client.renderer.texture.IconRegister;
import altenergy.AltEnergy;
import altenergy.core.misc.ItemAE;
import altenergy.lib.Strings;

public class ItemEnergyCrystals extends ItemAE {

	public ItemEnergyCrystals(int id) {

		super(id);
		this.setCreativeTab(AltEnergy.tabsAE);
		this.setMaxStackSize(1);
		if(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".")) == Strings.ENERGY_CRYSTAL_TINY) {
			this.setMaxDamage(1000);
		} else if(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".")) == Strings.ENERGY_CRYSTAL_SMALL) {
			this.setMaxDamage(2000);
		} else if(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".")) == Strings.ENERGY_CRYSTAL_MEDIUM) {
			this.setMaxDamage(4000);
		} else if(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".")) == Strings.ENERGY_CRYSTAL_LARGE) {
			this.setMaxDamage(7000);
		} else if(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".")) == Strings.ENERGY_CRYSTAL_HUGE) {
			this.setMaxDamage(10000);
		} else if(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".")) == Strings.ENERGY_CRYSTAL_GIGANTIC) {
			this.setMaxDamage(14000);
		}
	}
	
	@Override
	public void registerIcons(IconRegister register) {
		
	}
}
