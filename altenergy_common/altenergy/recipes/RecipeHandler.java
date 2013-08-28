
package altenergy.recipes;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.item.ItemStack;

/**
 * 
 * @author VitezKolya
 * 
 *         Class used to handle machine recipes. This is used for both adding
 *         recipes and checking outputs.
 * 
 */
public final class RecipeHandler {

	public static void addRecipe(Recipe recipe, Object input, Object output) {

		recipe.put(input, output);
	}
	
	public static void addGrinderRecipe(Object input, Object output) {

		Recipe.GRINDER.put(input, output);
	}
	
	public static void addAdvCompressorRecipe(Object input, Object output) {

		Recipe.ADVANCED_COMPRESSOR.put(input, output);
	}
	
	public static void addAdvSmeltingFurnaceRecipe(Object input, Object output) {

		Recipe.ADVANCED_SMELTING_FURNACE.put(input, output);
	}
	
	public static void addCrystalPurifierRecipe(Object input, Object output) {

		Recipe.PURIFICATION_CHAMBER.put(input, output);
	}
	
	public static void addCrystalProcessorRecipe(Object input, Object output) {

		Recipe.CRYSTAL_PROCESSOR.put(input, output);
	}
	
	public static void addQauntumEnchichmentChamberRecipe(Object input, Object output) {

		Recipe.QAUNTUM_ENRICHMENT_CHAMBER.put(input, output);
	}
	
	public static enum Recipe {
		GRINDER(new HashMap<ItemStack, ItemStack>()),
		ADVANCED_COMPRESSOR(new HashMap<ItemStack, ItemStack>()),
		PURIFICATION_CHAMBER(new HashMap<ItemStack, ItemStack>()),
		QAUNTUM_ENRICHMENT_CHAMBER(new HashMap<ItemStack, ItemStack>()),
		CRYSTAL_PROCESSOR(new HashMap<ItemStack, ItemStack>()),
		ADVANCED_SMELTING_FURNACE(new HashMap<ItemStack, ItemStack>());

		private HashMap recipes;

		private Recipe(HashMap map) {

			recipes = map;
		}

		public void put(Object input, Object output) {

			recipes.put(input, output);
		}

		public boolean containsRecipe(ItemStack input) {

			for (Object obj : get().entrySet()) {
				if (obj instanceof Map.Entry) {
					Map.Entry entry = (Map.Entry) obj;

					if (entry.getKey() instanceof ItemStack) {
						if (((ItemStack) entry.getKey()).isItemEqual(input)) {
							return true;
						}
					}
				}
			}

			return false;
		}

		public HashMap get() {

			return recipes;
		}
	}
}
