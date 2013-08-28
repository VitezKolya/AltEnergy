
package altenergy.core.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import altenergy.client.gui.GuiControllerPC;
import altenergy.client.gui.GuiMiniPowerCore;
import altenergy.client.render.item.ItemEnergyBuswayRenderer;
import altenergy.client.render.tileentity.TileEntityEnergyBuswayRenderer;
import altenergy.content.conductors.TileEnergyBusway;
import altenergy.lib.BlockIds;
import altenergy.lib.GuiIds;
import altenergy.lib.RenderIds;

public class ClientProxy extends CommonProxy {

	@Override
    public void initRenderingAndTextures() {
		RenderIds.buswayRenderId = RenderingRegistry.getNextAvailableRenderId();
		RenderIds.cableRenderId = RenderingRegistry.getNextAvailableRenderId();
		RenderIds.utilBundleRenderId = RenderingRegistry.getNextAvailableRenderId();
		RenderIds.controllerPC = RenderingRegistry.getNextAvailableRenderId();
		RenderIds.mini_powercore = RenderingRegistry.getNextAvailableRenderId();
		
		MinecraftForgeClient.registerItemRenderer(BlockIds.ENERGY_BUSWAY, new ItemEnergyBuswayRenderer());
	}
	
	@Override
	public void registerTileEntities() {
		
		super.registerTileEntities();
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEnergyBusway.class, new TileEntityEnergyBuswayRenderer());
	}
}
