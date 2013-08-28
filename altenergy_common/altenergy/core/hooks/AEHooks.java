
package altenergy.core.hooks;

import ic2.api.recipe.Recipes;

import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import thermalexpansion.api.crafting.CraftingManagers;
import thermalexpansion.api.crafting.IPulverizerRecipe;
import altenergy.blocks.ModBlocks;
import altenergy.core.util.AltEnergyUtils;
import altenergy.items.ModItems;
import altenergy.lib.Reference;
import altenergy.recipes.RecipeHandler;
import altenergy.recipes.RecipeHandler.Recipe;
import cpw.mods.fml.common.Loader;

public class AEHooks {

	private Class BasicComponents;
	
	public boolean IC2Loaded = false;
	public boolean BasicComponentsLoaded = false;
	public boolean BuildCraftLoaded = false;
	public boolean TELoaded = false;

	public void hook() {

		if (Loader.isModLoaded("IC2")) {
			IC2Loaded = true;
		}
		if (Loader.isModLoaded("BasicComponents")) {
			BasicComponentsLoaded = true;
		}
		if (Loader.isModLoaded("BuildCraft|Energy")) {
			BuildCraftLoaded = true;
		}
		if (Loader.isModLoaded("ThermalExpansion")) {
			TELoaded = true;
		}

		if (IC2Loaded) {
			Recipes.macerator.addRecipe(new ItemStack(ModBlocks.strangeCrystalOre, 1), new ItemStack(ModItems.gems, 2,
					0));
			Recipes.macerator.addRecipe(new ItemStack(ModItems.gems, 1, 0), new ItemStack(ModItems.dusts, 1, 0));
			Recipes.macerator.addRecipe(new ItemStack(ModItems.gems, 1, 1), new ItemStack(ModItems.dusts, 1, 2));
			Recipes.macerator.addRecipe(new ItemStack(Item.diamond, 1), new ItemStack(ModItems.dusts, 1, 6));
			Recipes.macerator.addRecipe(new ItemStack(Item.netherQuartz, 1), new ItemStack(ModItems.dusts, 1, 4));

			for (Map.Entry<ItemStack, ItemStack> entry : Recipes.macerator.getRecipes().entrySet()) {
				if (AltEnergyUtils.getName(entry.getKey()).startsWith("ore")) {
					if (!Recipe.ADVANCED_SMELTING_FURNACE.containsRecipe(entry.getKey())) {

						RecipeHandler.addAdvSmeltingFurnaceRecipe(entry.getKey(), entry.getValue());
					}
					if (!Recipe.GRINDER.containsRecipe(entry.getKey())) {

						RecipeHandler.addGrinderRecipe(entry.getKey(), entry.getValue());
					}
				} else if (AltEnergyUtils.getName(entry.getKey()).startsWith("ingot")) {
					if (!Recipe.GRINDER.containsRecipe(entry.getKey())) {

						RecipeHandler.addGrinderRecipe(entry.getKey(), entry.getValue());
					}
				}
			}

			System.out.println("[" + Reference.MOD_ID + "] Hooked into IC2 successfully.");
		}

		if (BasicComponentsLoaded) {

			System.out.println("[" + Reference.MOD_ID + "] Hooked into BasicComponents successfully.");
		}

		if (BuildCraftLoaded) {
			System.out.println("[" + Reference.MOD_ID + "] Hooked into BuildCraft successfully.");
		}

		if (TELoaded) {

			for (IPulverizerRecipe recipe : CraftingManagers.pulverizerManager.getRecipeList()) {
				if (recipe.getSecondaryOutput() == null) {
					if (AltEnergyUtils.getName(recipe.getInput()).startsWith("ore")) {
						if (!Recipe.ADVANCED_SMELTING_FURNACE.containsRecipe(recipe.getInput())) {

							RecipeHandler.addAdvSmeltingFurnaceRecipe(recipe.getInput(), recipe.getPrimaryOutput());
						}
						if (!Recipe.GRINDER.containsRecipe(recipe.getInput())) {

							RecipeHandler.addGrinderRecipe(recipe.getInput(), recipe.getPrimaryOutput());
						}
					} else if (AltEnergyUtils.getName(recipe.getInput()).startsWith("ingot")) {
						if (!Recipe.GRINDER.containsRecipe(recipe.getInput())) {
							
							RecipeHandler.addGrinderRecipe(recipe.getInput(), recipe.getPrimaryOutput());
						}
					}
				}
			}

			System.out.println("[" + Reference.MOD_ID + "] Hooked into ThermalExpansion successfully.");
		}
	}
	
	public ItemStack getBasicComponentsItem(String name) {
		try {
			if(BasicComponents == null) BasicComponents = Class.forName("basiccomponents.common.BasicComponents");
			if(BasicComponents == null) BasicComponents = Class.forName("net.minecraft.src.basiccomponents.common.BasicComponents");
			
			Object ret = BasicComponents.getField(name).get(null);
			
			if(ret instanceof Item) {
				return new ItemStack((Item) ret);
			} else if(ret instanceof Block) {
				return new ItemStack((Block) ret);
			} else {
				throw new Exception("not instanceof ItemStack");
			}
		} catch(Exception e) {
			System.out.println("[" + Reference.MOD_ID + "] Unable to retrieve Basic Components item " + name + ".");
			return null;
		}
	}
}
