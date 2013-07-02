package altenergy.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import altenergy.lib.ItemIds;
import altenergy.lib.Strings;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModItems {

	/* Mod item instances */
	public static Item gemStrangeCrystal;
	public static Item gemPowerCrystal;
	public static Item dustSrangeCrystal;
	public static Item dustQuartz;
	public static Item dustTinyQuartz;
	public static Item dustDiamond;
	public static Item dustTinyDiamond;

	public static void init() {

		gemStrangeCrystal = new ItemStrangeCrystal(ItemIds.STRANGE_CRYSTAL)
				.setUnlocalizedName(Strings.GEM_STRANGE_CRYSTAL_NAME);
		dustSrangeCrystal = new ItemDusts(ItemIds.STRANGE_CRYSTAL_DUST)
				.setUnlocalizedName(Strings.ITEM_STRANGE_CRYSTAL_DUST_NAME);
		dustQuartz = new ItemDusts(ItemIds.QUARTZ_DUST).setUnlocalizedName(Strings.ITEM_QUARTZ_DUST_NAME);
		dustTinyQuartz = new ItemDusts(ItemIds.TINY_QUARTZ_DUST).setUnlocalizedName(Strings.ITEM_TINY_QUARTZ_DUST_NAME);
		dustDiamond = new ItemDusts(ItemIds.DIAMOND_DUST).setUnlocalizedName(Strings.ITEM_DIAMOND_DUST_NAME);
		dustTinyDiamond = new ItemDusts(ItemIds.TINY_DIAMOND_DUST)
				.setUnlocalizedName(Strings.ITEM_TINY_DIAMOND_DUST_NAME);

		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.dustTinyDiamond, 9), new Object[] {
			new ItemStack(ModItems.dustDiamond, 1)
		});
		GameRegistry.addRecipe(new ItemStack(ModItems.dustDiamond, 1), new Object[] {
				"sss", "sss", "sss", Character.valueOf('s'), new ItemStack(ModItems.dustTinyDiamond, 1)
		});

		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.dustTinyQuartz, 9), new Object[] {
			new ItemStack(ModItems.dustQuartz, 1)
		});
		GameRegistry.addRecipe(new ItemStack(ModItems.dustQuartz, 1), new Object[] {
				"sss", "sss", "sss", Character.valueOf('s'), new ItemStack(ModItems.dustTinyQuartz, 1)
		});
	}
}
