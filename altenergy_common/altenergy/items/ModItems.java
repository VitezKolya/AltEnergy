
package altenergy.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import altenergy.lib.ItemIds;
import altenergy.lib.Strings;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModItems {

	/* Mod item instances */
	// public static Item gemStrangeCrystal;
	public static Item energyCrystals;
	public static Item gems;
	public static Item dusts;
	public static Item items;
	public static Item items3d;
	public static Item ingots;

	public static void init() {

		items = new ItemBase(ItemIds.ITEMS);
		items3d = new ItemBase3D(ItemIds.ITEMS_3D);
		gems = new ItemGems(ItemIds.GEMS);
		dusts = new ItemDusts(ItemIds.DUSTS);
		energyCrystals = new ItemEnergyCrystals(ItemIds.ENERGY_CRYSTAL_TINY)
				.setUnlocalizedName(Strings.ENERGY_CRYSTAL_TINY);
		energyCrystals = new ItemEnergyCrystals(ItemIds.ENERGY_CRYSTAL_SMALL)
				.setUnlocalizedName(Strings.ENERGY_CRYSTAL_SMALL);
		energyCrystals = new ItemEnergyCrystals(ItemIds.ENERGY_CRYSTAL_MEDIUM)
				.setUnlocalizedName(Strings.ENERGY_CRYSTAL_MEDIUM);
		energyCrystals = new ItemEnergyCrystals(ItemIds.ENERGY_CRYSTAL_LARGE)
				.setUnlocalizedName(Strings.ENERGY_CRYSTAL_LARGE);
		energyCrystals = new ItemEnergyCrystals(ItemIds.ENERGY_CRYSTAL_HUGE)
				.setUnlocalizedName(Strings.ENERGY_CRYSTAL_HUGE);
		energyCrystals = new ItemEnergyCrystals(ItemIds.ENERGY_CRYSTAL_GIGANTIC)
				.setUnlocalizedName(Strings.ENERGY_CRYSTAL_GIGANTIC);

		// Diamond Dust
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.dusts, 9, 7), new Object[] {
			new ItemStack(ModItems.dusts, 1, 6)
		});
		GameRegistry.addRecipe(new ItemStack(ModItems.dusts, 1, 6), new Object[] {
				"sss", "sss", "sss", Character.valueOf('s'), new ItemStack(ModItems.dusts, 1, 7)
		});

		// Quartz Dust
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.dusts, 9, 3), new Object[] {
			new ItemStack(ModItems.dusts, 1, 2)
		});
		GameRegistry.addRecipe(new ItemStack(ModItems.dusts, 1, 2), new Object[] {
				"sss", "sss", "sss", Character.valueOf('s'), new ItemStack(ModItems.dusts, 1, 3)
		});

		// Strange Crystal Dust

	}
}
