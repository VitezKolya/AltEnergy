package altenergy.items;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import altenergy.lib.Strings;

public class ItemGraniteBrickBlock extends ItemBlock {

	public ItemGraniteBrickBlock(int id) {
		super(id);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack) {

		for (int i = 0; i < Strings.BLOCK_GRANITE_BRICKS_SUBNAMES.length; i++) {
			if (i == this.getDamage(itemstack)) {
				return this.getUnlocalizedName() + Strings.BLOCK_GRANITE_BRICKS_SUBNAMES[i];
			}
		}

		return "";
	}

	public int getMetadata(int meta) {
		return meta;
	}
}
