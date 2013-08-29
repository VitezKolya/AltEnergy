
package altenergy.core.proxy;

import net.minecraftforge.client.MinecraftForgeClient;
import altenergy.client.render.item.ItemEnergyBuswayRenderer;
import altenergy.client.render.item.ItemEnergyCrystalRenderer;
import altenergy.client.render.tileentity.TileEntityEnergyBuswayRenderer;
import altenergy.client.render.tileentity.TileEntityMiniPowerCoreRenderer;
import altenergy.content.conductors.TileEnergyBusway;
import altenergy.content.minipowercore.TileMiniPowerCore;
import altenergy.lib.BlockIds;
import altenergy.lib.ItemIds;
import altenergy.lib.RenderIds;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {

	@Override
	public void initRenderingAndTextures() {

		RenderIds.buswayRenderId = RenderingRegistry.getNextAvailableRenderId();
		RenderIds.cableRenderId = RenderingRegistry.getNextAvailableRenderId();
		RenderIds.utilBundleRenderId = RenderingRegistry.getNextAvailableRenderId();
		RenderIds.controllerPC = RenderingRegistry.getNextAvailableRenderId();
		RenderIds.mini_powercore = RenderingRegistry.getNextAvailableRenderId();

		MinecraftForgeClient.registerItemRenderer(BlockIds.ENERGY_BUSWAY, new ItemEnergyBuswayRenderer());
		MinecraftForgeClient.registerItemRenderer(ItemIds.ENERGY_CRYSTAL_TINY, new ItemEnergyCrystalRenderer());
		MinecraftForgeClient.registerItemRenderer(ItemIds.ENERGY_CRYSTAL_SMALL, new ItemEnergyCrystalRenderer());
		MinecraftForgeClient.registerItemRenderer(ItemIds.ENERGY_CRYSTAL_MEDIUM, new ItemEnergyCrystalRenderer());
		MinecraftForgeClient.registerItemRenderer(ItemIds.ENERGY_CRYSTAL_LARGE, new ItemEnergyCrystalRenderer());
		MinecraftForgeClient.registerItemRenderer(ItemIds.ENERGY_CRYSTAL_HUGE, new ItemEnergyCrystalRenderer());
		MinecraftForgeClient.registerItemRenderer(ItemIds.ENERGY_CRYSTAL_GIGANTIC, new ItemEnergyCrystalRenderer());
	}

	@Override
	public void registerTileEntities() {

		super.registerTileEntities();

		ClientRegistry.bindTileEntitySpecialRenderer(TileEnergyBusway.class, new TileEntityEnergyBuswayRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileMiniPowerCore.class, new TileEntityMiniPowerCoreRenderer());
	}
}
