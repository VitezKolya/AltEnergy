
package altenergy;

import java.io.File;

import net.minecraft.creativetab.CreativeTabs;
import altenergy.blocks.ModBlocks;
import altenergy.configuration.ConfigurationHandler;
import altenergy.core.handlers.LocalizationHandler;
import altenergy.core.proxy.CommonProxy;
import altenergy.creativetab.CreativeTabAE;
import altenergy.items.ModItems;
import altenergy.lib.Reference;
import altenergy.worldGen.WorldGenManager;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, certificateFingerprint = Reference.FINGERPRINT)

// Don't understand packets at this time so commenting out for now.
//@NetworkMod(channels = Reference.CHANNEL_NAME, serverSideRequired = false, clientSideRequired = true)
public class AltEnergy {

	@Instance(Reference.MOD_ID)
	public static AltEnergy instance;

	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;

	public static CreativeTabs tabsAE = new CreativeTabAE(CreativeTabs.getNextID(), Reference.MOD_ID);

	public static WorldGenManager genManager = new WorldGenManager();

	/**
	 * This is were you load up blocks/items
	 * 
	 * @param event
	 */
	@PreInit
	public void preInit(FMLPreInitializationEvent event) {

		LocalizationHandler.loadLanguages();

		// Initialize the configuration
		ConfigurationHandler.init(new File(event.getModConfigurationDirectory().getAbsolutePath() + File.separator
				+ Reference.CHANNEL_NAME + File.separator + Reference.MOD_ID + ".cfg"));

		ModBlocks.init();
		ModItems.init();

		GameRegistry.registerWorldGenerator(genManager);
		
		//NetworkRegistry.instance().registerGuiHandler(this, proxy);
	}

	@Init
	public void init(FMLInitializationEvent event) {

	}

	@PostInit
	public void postInit(FMLPostInitializationEvent event) {

	}
}
