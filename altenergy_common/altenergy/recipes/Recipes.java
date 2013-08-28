package altenergy.recipes;

import altenergy.blocks.ModBlocks;
import altenergy.items.ModItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;


public class Recipes {
	
	public void init() {
		
	/**
	 * Decoration Block Recipes 
	 */
		
	//Granite Recipes
	GameRegistry.addRecipe(new ItemStack(ModBlocks.graniteBrick, 4, 0), new Object[] {
			"XX", "XX", 'X', ModBlocks.granite
	});
	GameRegistry.addRecipe(new ItemStack(ModBlocks.graniteBrick, 4, 1), new Object[] {
			"XX", "XX", 'X', ModBlocks.graniteBrick
	});
	GameRegistry.addRecipe(new ItemStack(ModBlocks.graniteBrick, 4, 2), new Object[] {
			"XX", "XX", 'X', ModBlocks.graniteBrick
	});
	
	/**
	 *	Machine Processing Recipes 
	 */
	
	//Grinder Recipes
	RecipeHandler.addGrinderRecipe(new ItemStack(ModBlocks.strangeCrystalOre, 1), new ItemStack(ModItems.gems,2,0));
	RecipeHandler.addGrinderRecipe(new ItemStack(ModItems.gems, 1, 0), new ItemStack(ModItems.dusts,1,0));
	RecipeHandler.addGrinderRecipe(new ItemStack(ModItems.gems, 1,1), new ItemStack(ModItems.dusts,1,2));
	RecipeHandler.addGrinderRecipe(new ItemStack(Item.netherQuartz,1), new ItemStack(ModItems.gems,1,4));
	RecipeHandler.addGrinderRecipe(new ItemStack(Block.blockNetherQuartz,1), new ItemStack(Item.netherQuartz,2));
	RecipeHandler.addGrinderRecipe(new ItemStack(Item.diamond,1), new ItemStack(ModItems.dusts,1));
	RecipeHandler.addGrinderRecipe(new ItemStack(Block.oreDiamond,1), new ItemStack(Item.diamond,2));
	RecipeHandler.addGrinderRecipe(new ItemStack(Block.oreIron,1), new ItemStack(ModItems.dusts,2,10));
	RecipeHandler.addGrinderRecipe(new ItemStack(Block.oreGold,1), new ItemStack(ModItems.dusts,2,12));
	RecipeHandler.addGrinderRecipe(new ItemStack(Item.coal,1), new ItemStack(ModItems.dusts,1,14));
	RecipeHandler.addGrinderRecipe(new ItemStack(Item.coal,1,1), new ItemStack(ModItems.dusts,1,16));
	RecipeHandler.addGrinderRecipe(new ItemStack(Block.oreCoal,1), new ItemStack(Item.coal,2));
	RecipeHandler.addGrinderRecipe(new ItemStack(Block.oreRedstone,1), new ItemStack(Item.redstone,8));
	RecipeHandler.addGrinderRecipe(new ItemStack(Block.oreLapis,1), new ItemStack(Item.dyePowder,4));
	RecipeHandler.addGrinderRecipe(new ItemStack(Block.oreEmerald,1), new ItemStack(Item.redstone,2));
	
	//Purification Chamber
	RecipeHandler.addCrystalPurifierRecipe(new ItemStack(ModItems.dusts, 1, 14), new ItemStack(ModItems.dusts,1,8));
	RecipeHandler.addCrystalPurifierRecipe(new ItemStack(ModItems.dusts, 2, 16), new ItemStack(ModItems.dusts,1,8));
	RecipeHandler.addCrystalPurifierRecipe(new ItemStack(Item.diamond, 1), new ItemStack(ModItems.gems,1,2));
	RecipeHandler.addCrystalPurifierRecipe(new ItemStack(Item.coal, 1), new ItemStack(ModItems.gems,1,2));
	}
}
