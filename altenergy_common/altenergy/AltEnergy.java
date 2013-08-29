
package altenergy;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.DimensionManager;
import universalelectricity.core.UniversalElectricity;
import universalelectricity.prefab.network.PacketManager;
import altenergy.blocks.ModBlocks;
import altenergy.configuration.ConfigurationHandler;
import altenergy.content.ModContent;
import altenergy.core.handlers.AltEnergyHooks;
import altenergy.core.handlers.LocalizationHandler;
import altenergy.core.proxy.CommonProxy;
import altenergy.creativetab.CreativeTabAE;
import altenergy.items.ModItems;
import altenergy.lib.DimensionIds;
import altenergy.lib.Reference;
import altenergy.world.WorldProviderTerra;
import altenergy.world.gen.WorldGenManager;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.Metadata;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * AltEnergy
 * 
 * AltEnergy
 * 
 * @author VitezKolya
 * @license GNU Public License v3 (http://www.gnu.org/licenses/gpl.html)
 * 
 */

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, certificateFingerprint = Reference.FINGERPRINT)
@NetworkMod(channels = {
	Reference.CHANNEL_NAME
}, clientSideRequired = true, serverSideRequired = false, packetHandler = PacketManager.class)
public class AltEnergy {

	@Instance(Reference.MOD_ID)
	public static AltEnergy instance;

	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;

	public static CreativeTabs tabsAE = new CreativeTabAE(CreativeTabs.getNextID(), Reference.MOD_ID);

	public static WorldGenManager genManager = new WorldGenManager();

	public static AltEnergyHooks hooks;

	@Metadata(Reference.MOD_ID)
	public static ModMetadata meta;

	public static Logger AELogger = Logger.getLogger(Reference.MOD_ID);

	public static void log(Level level, String msg, String... replacements) {

		for (String replace : replacements) {
			msg = msg.replace("%s", replace);
		}

		AELogger.log(level, msg);
	}

	/**
	 * This is were you load up blocks/items
	 * 
	 * @param event
	 */
	@PreInit
	public void preInit(FMLPreInitializationEvent event) {

		log(Level.INFO, "PreInitializing " + Reference.MOD_ID + " version: " + meta.version);

		LocalizationHandler.loadLanguages();

		// Initialize the configuration
		ConfigurationHandler.init(new File(event.getModConfigurationDirectory().getAbsolutePath() + File.separator
				+ Reference.CHANNEL_NAME + File.separator + Reference.MOD_ID + ".cfg"));

		ModBlocks.init();
		ModItems.init();
		ModContent.init();

		GameRegistry.registerWorldGenerator(genManager);

		NetworkRegistry.instance().registerGuiHandler(this, proxy);
	}

	@Init
	public void init(FMLInitializationEvent event) {

		log(Level.INFO, "Initializing " + Reference.MOD_ID + " version: " + meta.version);

		proxy.registerTileEntities();

		proxy.initRenderingAndTextures();

		/*
		 * if (!useUeVoltageSensitivity) {
		 * UniversalElectricity.isVoltageSensitive = true;
		 * //EELogger.finest("Successfully toggled Voltage Sensitivity!");
		 * }
		 */

		UniversalElectricity.isNetworkActive = true;

		DimensionManager.registerProviderType(DimensionIds.TERRA, WorldProviderTerra.class, false);
		DimensionManager.registerDimension(DimensionIds.TERRA, DimensionIds.TERRA);
	}

	@PostInit
	public void postInit(FMLPostInitializationEvent event) {

		log(Level.INFO, "PostInitializing " + Reference.MOD_ID + " version: " + meta.version);
	}
}
