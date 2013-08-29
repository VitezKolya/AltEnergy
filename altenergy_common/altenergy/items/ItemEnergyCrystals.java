
package altenergy.items;

import net.minecraft.client.renderer.texture.IconRegister;
import altenergy.AltEnergy;
import altenergy.core.misc.ItemAE;
import altenergy.lib.Strings;

public class ItemEnergyCrystals extends ItemAE {

	public ItemEnergyCrystals(int id) {

		super(id);
		setCreativeTab(AltEnergy.tabsAE);
		setMaxStackSize(1);
		if (this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".")) == Strings.ENERGY_CRYSTAL_TINY) {
			setMaxDamage(1000);
		} else if (this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".")) == Strings.ENERGY_CRYSTAL_SMALL) {
			setMaxDamage(2000);
		} else if (this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".")) == Strings.ENERGY_CRYSTAL_MEDIUM) {
			setMaxDamage(4000);
		} else if (this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".")) == Strings.ENERGY_CRYSTAL_LARGE) {
			setMaxDamage(7000);
		} else if (this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".")) == Strings.ENERGY_CRYSTAL_HUGE) {
			setMaxDamage(10000);
		} else if (this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".")) == Strings.ENERGY_CRYSTAL_GIGANTIC) {
			setMaxDamage(14000);
		}
	}

	@Override
	public void registerIcons(IconRegister register) {

	}
}
