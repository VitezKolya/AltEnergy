
package altenergy.configuration;

import java.io.File;
import java.util.logging.Level;

import net.minecraftforge.common.Configuration;
import altenergy.lib.BlockIds;
import altenergy.lib.ItemIds;
import altenergy.lib.Reference;
import altenergy.lib.Strings;
import cpw.mods.fml.common.FMLLog;

public class ConfigurationHandler {
	public static Configuration configuration;

	public static final String CATEGORY_GRAPHICS = "graphics";
	public static final String CATEGORY_GENERATION = "gerneration";
	public static final String CATEGORY_AUDIO = "audio";
	public static final String CATEGORY_BLOCK = "block";
	public static final String CATEGORY_ITEM = "item";

	public static void init(File configFile) {

		configuration = new Configuration(configFile);

		try {

			configuration.load();

			/* General configs */
			ConfigurationSettings.GRANITE_CHEAT_ENABLE = configuration.get(Configuration.CATEGORY_GENERAL,
					ConfigurationSettings.GRANITE_CHEAT_ENABLE_CONFIGNAME,
					ConfigurationSettings.GRANITE_CHEAT_ENABLE_DEFAULT).getBoolean(
					ConfigurationSettings.GRANITE_CHEAT_ENABLE_DEFAULT);
			ConfigurationSettings.FORCEFIELD_DOOR_MAXWIDTH = configuration.get(Configuration.CATEGORY_GENERAL,
					ConfigurationSettings.FORCEFIELD_DOOR_MAXWIDTH_CONFIGNAME,
					ConfigurationSettings.FORCEFIELD_DOOR_MAXWIDTH_DEFAULT).getInt(
					ConfigurationSettings.FORCEFIELD_DOOR_MAXWIDTH_DEFAULT);
			ConfigurationSettings.FORCEFIELD_DOOR_MAXHEIGHT = configuration.get(Configuration.CATEGORY_GENERAL,
					ConfigurationSettings.FORCEFIELD_DOOR_MAXHEIGHT_CONFIGNAME,
					ConfigurationSettings.FORCEFIELD_DOOR_MAXHEIGHT_DEFAULT).getInt(
					ConfigurationSettings.FORCEFIELD_DOOR_MAXHEIGHT_DEFAULT);

			/* Generation configs */
			ConfigurationSettings.ORE_GENERATION_ENABLE = configuration.get(CATEGORY_GENERATION,
					ConfigurationSettings.ORE_GENERATION_ENABLE_CONFIGNAME,
					ConfigurationSettings.ORE_GENERATION_ENABLE_DEFAULT).getBoolean(
					ConfigurationSettings.ORE_GENERATION_ENABLE_DEFAULT);
			ConfigurationSettings.GRANITE_GENERATION_ENABLE = configuration.get(CATEGORY_GENERATION,
					ConfigurationSettings.GRANITE_GENERATION_ENABLE_CONFIGNAME,
					ConfigurationSettings.GRANITE_GENERATION_ENABLE_DEFAULT).getBoolean(
					ConfigurationSettings.GRANITE_GENERATION_ENABLE_DEFAULT);
			ConfigurationSettings.GRANITE_GENERATION_MAX_HIGHT = configuration.get(CATEGORY_GENERATION,
					ConfigurationSettings.GRANITE_GENERATION_MAX_HIGHT_CONFIGNAME,
					ConfigurationSettings.GRANITE_GENERATION_MAX_HIGHT_DEFAULT).getInt(
					ConfigurationSettings.GRANITE_GENERATION_MAX_HIGHT_DEFAULT);
			ConfigurationSettings.GRANITE_GENERATION_MIN_HIGHT = configuration.get(CATEGORY_GENERATION,
					ConfigurationSettings.GRANITE_GENERATION_MIN_HIGHT_CONFIGNAME,
					ConfigurationSettings.GRANITE_GENERATION_MIN_HIGHT_DEFAULT).getInt(
					ConfigurationSettings.GRANITE_GENERATION_MIN_HIGHT_DEFAULT);
			ConfigurationSettings.GRANITE_GENERATION_VEIN_SIZE = configuration.get(CATEGORY_GENERATION,
					ConfigurationSettings.GRANITE_GENERATION_VEIN_SIZE_CONFIGNAME,
					ConfigurationSettings.GRANITE_GENERATION_VEIN_SIZE_DEFAULT).getInt(
					ConfigurationSettings.GRANITE_GENERATION_VEIN_SIZE_DEFAULT);
			ConfigurationSettings.GRANITE_GENERATION_VEIN_PERCHUNK = configuration.get(CATEGORY_GENERATION,
					ConfigurationSettings.GRANITE_GENERATION_VEIN_PERCHUNK_CONFIGNAME,
					ConfigurationSettings.GRANITE_GENERATION_VEIN_PERCHUNK_DEFAULT).getInt(
					ConfigurationSettings.GRANITE_GENERATION_VEIN_PERCHUNK_DEFAULT);

			ConfigurationSettings.STRANGE_CRYSTAL_GENERATION_ENABLE = configuration.get(CATEGORY_GENERATION,
					ConfigurationSettings.STRANGE_CRYSTAL_GENERATION_ENABLE_CONFIGNAME,
					ConfigurationSettings.STRANGE_CRYSTAL_GENERATION_ENABLE_DEFAULT).getBoolean(
					ConfigurationSettings.STRANGE_CRYSTAL_GENERATION_ENABLE_DEFAULT);
			ConfigurationSettings.STRANGE_CRYSTAL_GENERATE_GRANITE_ONLY_ENABLE = configuration.get(CATEGORY_GENERATION,
					ConfigurationSettings.STRANGE_CRYSTAL_GENERATE_GRANITE_ONLY_ENABLE_CONFIGNAME,
					ConfigurationSettings.STRANGE_CRYSTAL_GENERATE_GRANITE_ONLY_ENABLE_DEFAULT).getBoolean(
					ConfigurationSettings.STRANGE_CRYSTAL_GENERATE_GRANITE_ONLY_ENABLE_DEFAULT);
			ConfigurationSettings.STRANGE_CRYSTAL_GENERATION_MAX_HIGHT = configuration.get(CATEGORY_GENERATION,
					ConfigurationSettings.STRANGE_CRYSTAL_GENERATION_MAX_HIGHT_CONFIGNAME,
					ConfigurationSettings.STRANGE_CRYSTAL_GENERATION_MAX_HIGHT_DEFAULT).getInt(
					ConfigurationSettings.STRANGE_CRYSTAL_GENERATION_MAX_HIGHT_DEFAULT);
			ConfigurationSettings.STRANGE_CRYSTAL_GENERATION_MIN_HIGHT = configuration.get(CATEGORY_GENERATION,
					ConfigurationSettings.STRANGE_CRYSTAL_GENERATION_MIN_HIGHT_CONFIGNAME,
					ConfigurationSettings.STRANGE_CRYSTAL_GENERATION_MIN_HIGHT_DEFAULT).getInt(
					ConfigurationSettings.STRANGE_CRYSTAL_GENERATION_MIN_HIGHT_DEFAULT);
			ConfigurationSettings.STRANGE_CRYSTAL_GENERATION_VEIN_SIZE = configuration.get(CATEGORY_GENERATION,
					ConfigurationSettings.STRANGE_CRYSTAL_GENERATION_VEIN_SIZE_CONFIGNAME,
					ConfigurationSettings.STRANGE_CRYSTAL_GENERATION_VEIN_SIZE_DEFAULT).getInt(
					ConfigurationSettings.STRANGE_CRYSTAL_GENERATION_VEIN_SIZE_DEFAULT);
			ConfigurationSettings.STRANGE_CRYSTAL_GENERATION_VEIN_PERCHUNK = configuration.get(CATEGORY_GENERATION,
					ConfigurationSettings.STRANGE_CRYSTAL_GENERATION_VEIN_PERCHUNK_CONFIGNAME,
					ConfigurationSettings.STRANGE_CRYSTAL_GENERATION_VEIN_PERCHUNK_DEFAULT).getInt(
					ConfigurationSettings.STRANGE_CRYSTAL_GENERATION_VEIN_PERCHUNK_DEFAULT);

			/* Graphic configs */

			/* Audio configs */

			/* Item configs */
			
			ItemIds.ITEMS = configuration.getItem("items", ItemIds.ITEMS_DEFAULT).getInt(ItemIds.ITEMS_DEFAULT);
			ItemIds.ITEMS_3D = configuration.getItem("items3d", ItemIds.ITEMS_3D_DEFAULT).getInt(ItemIds.ITEMS_3D_DEFAULT);
			ItemIds.GEMS = configuration.getItem("gemItems", ItemIds.GEMS_DEFAULT).getInt(ItemIds.GEMS_DEFAULT);
			ItemIds.DUSTS = configuration.getItem("dustItems", ItemIds.DUSTS_DEFAULT).getInt(ItemIds.DUSTS_DEFAULT);
			ItemIds.ENERGY_CRYSTAL_TINY = configuration.getItem(Strings.ENERGY_CRYSTAL_TINY, ItemIds.ENERGY_CRYSTAL_TINY_DEFAULT).getInt(ItemIds.ENERGY_CRYSTAL_TINY_DEFAULT);
			ItemIds.ENERGY_CRYSTAL_SMALL = configuration.getItem(Strings.ENERGY_CRYSTAL_SMALL, ItemIds.ENERGY_CRYSTAL_SMALL_DEFAULT).getInt(ItemIds.ENERGY_CRYSTAL_SMALL_DEFAULT);
			ItemIds.ENERGY_CRYSTAL_MEDIUM = configuration.getItem(Strings.ENERGY_CRYSTAL_MEDIUM, ItemIds.ENERGY_CRYSTAL_MEDIUM_DEFAULT).getInt(ItemIds.ENERGY_CRYSTAL_MEDIUM_DEFAULT);
			ItemIds.ENERGY_CRYSTAL_LARGE = configuration.getItem(Strings.ENERGY_CRYSTAL_LARGE, ItemIds.ENERGY_CRYSTAL_LARGE_DEFAULT).getInt(ItemIds.ENERGY_CRYSTAL_LARGE_DEFAULT);
			ItemIds.ENERGY_CRYSTAL_HUGE = configuration.getItem(Strings.ENERGY_CRYSTAL_HUGE, ItemIds.ENERGY_CRYSTAL_HUGE_DEFAULT).getInt(ItemIds.ENERGY_CRYSTAL_HUGE_DEFAULT);
			ItemIds.ENERGY_CRYSTAL_GIGANTIC = configuration.getItem(Strings.ENERGY_CRYSTAL_GIGANTIC, ItemIds.ENERGY_CRYSTAL_GIGANTIC_DEFAULT).getInt(ItemIds.ENERGY_CRYSTAL_GIGANTIC_DEFAULT);

			/* Block configs */
			BlockIds.GRANITE = configuration.getBlock(Strings.BLOCK_GRANITE_NAME, BlockIds.GRANITE_DEFAULT).getInt(
					BlockIds.GRANITE_DEFAULT);
			BlockIds.GRANITEDEC = configuration.getBlock(Strings.BLOCK_GRANITEDEC_NAME, BlockIds.GRANITEDEC_DEFAULT)
					.getInt(BlockIds.GRANITEDEC_DEFAULT);
			BlockIds.GRANITE_BRICK = configuration.getBlock(Strings.BLOCK_GRANITE_BRICKS_NAME,
					BlockIds.GRANITE_BRICK_DEFAULT).getInt(BlockIds.GRANITE_BRICK_DEFAULT);
			BlockIds.STRANGE_CRYSTAL_ORE = configuration.getBlock(Strings.BLOCK_STRANGE_CRYSTAL_ORE_NAME,
					BlockIds.STRANGE_CRYSTAL_ORE_DEFAULT).getInt(BlockIds.STRANGE_CRYSTAL_ORE_DEFAULT);

			/* Tile config */
			BlockIds.CONTROLLER = configuration.getBlock(Strings.BLOCK_CONTROLLER, BlockIds.CONTROLLER_DEFAULT).getInt(
					BlockIds.CONTROLLER_DEFAULT);
			BlockIds.CONTROLLER_FF = configuration
					.getBlock(Strings.BLOCK_CONTROLLER_FF, BlockIds.CONTROLLER_FF_DEFAULT).getInt(
							BlockIds.CONTROLLER_FF_DEFAULT);
			BlockIds.CONTROLLER_PC = configuration
					.getBlock(Strings.BLOCK_CONTROLLER_PC, BlockIds.CONTROLLER_PC_DEFAULT).getInt(
							BlockIds.CONTROLLER_PC_DEFAULT);
			BlockIds.EMITER_FF = configuration.getBlock(Strings.BLOCK_EMITER_FF, BlockIds.EMITER_FF_DEFAULT).getInt(
					BlockIds.EMITER_FF_DEFAULT);
			BlockIds.ENERGY_BUSWAY = configuration.getBlock(Strings.BLOCK_ENERGY_BUSWAY,
					BlockIds.ENERGY_BUSWAY_DEFAULT).getInt(BlockIds.ENERGY_BUSWAY_DEFAULT);
			BlockIds.POWER_REGULATOR = configuration.getBlock(Strings.BLOCK_POWER_REGULATOR,
					BlockIds.POWER_REGULATOR_DEFAULT).getInt(BlockIds.POWER_REGULATOR_DEFAULT);
			BlockIds.MINI_POWERCORE = configuration.getBlock(Strings.BLOCK_MINI_POWER_CORE,
					BlockIds.MINI_POWERCORE_DEFAULT).getInt(BlockIds.MINI_POWERCORE_DEFAULT);

		} catch (Exception e) {
			FMLLog.log(Level.SEVERE, e, Reference.MOD_NAME + " has had a problem loading its configuration");
		} finally {
			configuration.save();
		}
	}

	public static void set(String categoryName, String propertyName, String newValue) {

		configuration.load();
		if (configuration.getCategoryNames().contains(categoryName)) {
			if (configuration.getCategory(categoryName).containsKey(propertyName)) {
				configuration.getCategory(categoryName).get(propertyName).set(newValue);
			}
		}
		configuration.save();
	}
}
