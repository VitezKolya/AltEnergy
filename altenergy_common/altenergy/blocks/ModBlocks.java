
package altenergy.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import altenergy.items.ItemGraniteBrickBlock;
import altenergy.lib.BlockIds;
import altenergy.lib.Strings;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlocks {
	public static Block granite;
	public static Block graniteBrick;
	public static Block strangeCrystalOre;
	public static Block graniteDec;
	public static Block controller;
	public static Block controllerFF;
	public static Block emiterFF;
	public static Block powerRegulator;

	public static void init() {

		granite = new BlockGranite(BlockIds.GRANITE);
		graniteDec = new BlockGranite(BlockIds.GRANITEDEC);
		graniteBrick = new BlockGraniteBrick(BlockIds.GRANITE_BRICK);
		strangeCrystalOre = new BlockStrangeCrystalOre(BlockIds.STRANGE_CRYSTAL_ORE);
		controller = new BlockController(BlockIds.CONTROLLER);
		controllerFF = new BlockControllerFF(BlockIds.CONTROLLER_FF);
		emiterFF = new BlockEmiterFF(BlockIds.EMITER_FF);
		powerRegulator = new BlockPowerRegulator(BlockIds.POWER_REGULATOR);

		MinecraftForge.setBlockHarvestLevel(ModBlocks.granite, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(ModBlocks.graniteDec, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(ModBlocks.graniteBrick, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(ModBlocks.strangeCrystalOre, "pickaxe", 2);

		GameRegistry.registerBlock(granite, Strings.BLOCK_GRANITE_NAME);
		GameRegistry.registerBlock(graniteDec, Strings.BLOCK_GRANITEDEC_NAME);
		GameRegistry.registerBlock(graniteBrick, ItemGraniteBrickBlock.class, Strings.BLOCK_GRANITE_BRICKS_NAME);
		GameRegistry.registerBlock(strangeCrystalOre, Strings.BLOCK_STRANGE_CRYSTAL_ORE_NAME);

		GameRegistry.registerBlock(emiterFF, Strings.BLOCK_EMITER_FF);
		GameRegistry.registerBlock(controller, Strings.BLOCK_CONTROLLER);
		GameRegistry.registerBlock(controllerFF, Strings.BLOCK_CONTROLLER_FF);
		GameRegistry.registerBlock(powerRegulator, Strings.BLOCK_POWER_REGULATOR);

		OreDictionary.registerOre(Strings.BLOCK_GRANITE_NAME, new ItemStack(granite, 1, 0));
		OreDictionary.registerOre(Strings.BLOCK_STRANGE_CRYSTAL_ORE_NAME, new ItemStack(strangeCrystalOre, 1, 0));

		GameRegistry.addRecipe(new ItemStack(graniteBrick, 4, 0), new Object[] {
				"XX", "XX", 'X', granite
		});
		GameRegistry.addRecipe(new ItemStack(graniteBrick, 4, 1), new Object[] {
				"XX", "XX", 'X', graniteBrick
		});
		GameRegistry.addRecipe(new ItemStack(graniteBrick, 4, 2), new Object[] {
				"XX", "XX", 'X', graniteBrick
		});
	}
}
