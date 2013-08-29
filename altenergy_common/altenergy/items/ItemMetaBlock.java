
package altenergy.items;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import altenergy.lib.Strings;

public class ItemMetaBlock extends ItemBlock {

	public ItemMetaBlock(int id) {

		super(id);
		setMaxDamage(0);
		setHasSubtypes(true);
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack) {

		return this.getUnlocalizedName() + Strings.BLOCK_GRANITE_BRICKS_SUBNAMES[itemstack.getItemDamage()];
		/*
		 * for (int i = 0; i < Strings.BLOCK_META_NAMES.length; i++) {
		 * if (i == this.getDamage(itemstack)) {
		 * return this.getUnlocalizedName() + Strings.BLOCK_META_NAMES[i];
		 * }
		 * }
		 * 
		 * return "";
		 */
	}

	public int getMeta(int meta) {

		return meta;
	}
}
