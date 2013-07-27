package altenergy.items;

import altenergy.AltEnergy;

public class ItemEnergyCrystal extends ItemAE{

	public ItemEnergyCrystal(int id) {

		super(id);
		this.setCreativeTab(AltEnergy.tabsAE);
		this.maxStackSize = 1;
	}
	
}
