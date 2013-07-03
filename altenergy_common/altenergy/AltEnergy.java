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
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
@NetworkMod(channels = Reference.CHANNEL_NAME, serverSideRequired = false, clientSideRequired = true)
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
		
		int[][][] test = {{{1,2,3,4},
					 {5,6,7,8},
					 {9,10,11,12}},
					{{101,102,103,104},
					 {105,106,107,108},
					 {109,110,111,112}}};
		
		System.out.println("X, Y, Z: " + test[1][0][0]);

		GameRegistry.registerWorldGenerator(genManager);
	}

	@Init
	public void init(FMLInitializationEvent event) {

	}

	@PostInit
	public void postInit(FMLPostInitializationEvent event) {

	}
}
