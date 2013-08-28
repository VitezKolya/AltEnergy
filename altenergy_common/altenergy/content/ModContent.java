
package altenergy.content;

import net.minecraft.block.Block;
import altenergy.content.conductors.BlockEnergyBusway;
import altenergy.content.conductors.ItemBlockEnergyBusway;
import altenergy.content.conductors.TileEnergyBusway;
import altenergy.content.controllers.BlockController;
import altenergy.content.controllers.BlockControllerFF;
import altenergy.content.controllers.BlockControllerPC;
import altenergy.content.controllers.TileControllerFF;
import altenergy.content.controllers.TileControllerPC;
import altenergy.content.forcefielddoor.BlockEmiterFF;
import altenergy.content.minipowercore.BlockMiniPowerCore;
import altenergy.content.minipowercore.TileMiniPowerCore;
import altenergy.content.powerregulator.BlockPowerRegulator;
import altenergy.lib.BlockIds;
import altenergy.lib.Reference;
import altenergy.lib.Strings;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModContent {
	
	public static Block blockController;
	public static Block blockControllerFF;
	public static Block blockControllerPC;
	public static Block blockEmiterFF;
	public static Block blockPowerRegulator;
	public static Block blockEnergyBusway;
	public static Block blockEnergyCable;
	public static Block blockMiniPowerCore;

	
	public static void init() {

		blockController = new BlockController(BlockIds.CONTROLLER);
		blockControllerFF = new BlockControllerFF(BlockIds.CONTROLLER_FF);
		blockControllerPC = new BlockControllerPC(BlockIds.CONTROLLER_PC);
		blockEmiterFF = new BlockEmiterFF(BlockIds.EMITER_FF);
		blockPowerRegulator = new BlockPowerRegulator(BlockIds.POWER_REGULATOR);
		blockEnergyBusway = new BlockEnergyBusway(BlockIds.ENERGY_BUSWAY);
		blockMiniPowerCore = new BlockMiniPowerCore(BlockIds.MINI_POWERCORE);
		
		GameRegistry.registerBlock(blockEnergyBusway, ItemBlockEnergyBusway.class, Strings.BLOCK_ENERGY_BUSWAY, Reference.MOD_ID);
		GameRegistry.registerBlock(blockEmiterFF, Strings.BLOCK_EMITER_FF);
		GameRegistry.registerBlock(blockController, Strings.BLOCK_CONTROLLER);
		GameRegistry.registerBlock(blockControllerFF, Strings.BLOCK_CONTROLLER_FF);
		GameRegistry.registerBlock(blockControllerPC, Strings.BLOCK_CONTROLLER_PC);
		GameRegistry.registerBlock(blockPowerRegulator, Strings.BLOCK_POWER_REGULATOR);
		GameRegistry.registerBlock(blockMiniPowerCore, Strings.BLOCK_MINI_POWER_CORE);
		
		
		GameRegistry.registerTileEntity(TileControllerFF.class, Strings.TILE_CONTROLLER_FF);
		GameRegistry.registerTileEntity(TileControllerPC.class, Strings.TILE_CONTROLLER_PC);
		GameRegistry.registerTileEntity(TileEnergyBusway.class, Strings.TILE_ENERGY_BUSWAY);
		GameRegistry.registerTileEntity(TileMiniPowerCore.class, Strings.TILE_MINI_POWER_CORE);
	}
}
