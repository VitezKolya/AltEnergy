
package altenergy.items;

import altenergy.AltEnergy;

public class ItemDusts extends ItemAE {
	public ItemDusts(int id) {

		super(id);
		this.setHasSubtypes(true);
		this.setCreativeTab(AltEnergy.tabsAE);
		maxStackSize = 64;
	}
}
