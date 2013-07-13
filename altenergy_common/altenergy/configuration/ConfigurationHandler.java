
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
			ItemIds.STRANGE_CRYSTAL = configuration.getItem(Strings.GEM_STRANGE_CRYSTAL_NAME,
					ItemIds.STRANGE_CRYSTAL_DEFAULT).getInt(ItemIds.STRANGE_CRYSTAL_DEFAULT);
			ItemIds.STRANGE_CRYSTAL_DUST = configuration.getItem(Strings.ITEM_STRANGE_CRYSTAL_DUST_NAME,
					ItemIds.STRANGE_CRYSTAL_DUST_DEFAULT).getInt(ItemIds.STRANGE_CRYSTAL_DUST_DEFAULT);
			ItemIds.QUARTZ_DUST = configuration.getItem(Strings.ITEM_QUARTZ_DUST_NAME, ItemIds.QUARTZ_DUST_DEFAULT)
					.getInt(ItemIds.QUARTZ_DUST_DEFAULT);
			ItemIds.TINY_QUARTZ_DUST = configuration.getItem(Strings.ITEM_TINY_QUARTZ_DUST_NAME,
					ItemIds.TINY_QUARTZ_DUST_DEFAULT).getInt(ItemIds.TINY_QUARTZ_DUST_DEFAULT);
			ItemIds.DIAMOND_DUST = configuration.getItem(Strings.ITEM_DIAMOND_DUST_NAME, ItemIds.DIAMOND_DUST_DEFAULT)
					.getInt(ItemIds.DIAMOND_DUST_DEFAULT);
			ItemIds.TINY_DIAMOND_DUST = configuration.getItem(Strings.ITEM_TINY_DIAMOND_DUST_NAME,
					ItemIds.TINY_DIAMOND_DUST_DEFAULT).getInt(ItemIds.TINY_DIAMOND_DUST_DEFAULT);

			/* Block configs */
			BlockIds.GRANITE = configuration.getBlock(Strings.BLOCK_GRANITE_NAME, BlockIds.GRANITE_DEFAULT).getInt(
					BlockIds.GRANITE_DEFAULT);
			BlockIds.GRANITEDEC = configuration.getBlock(Strings.BLOCK_GRANITEDEC_NAME, BlockIds.GRANITEDEC_DEFAULT)
					.getInt(BlockIds.GRANITEDEC_DEFAULT);
			BlockIds.GRANITE_BRICK = configuration.getBlock(Strings.BLOCK_GRANITE_BRICKS_NAME,
					BlockIds.GRANITE_BRICK_DEFAULT).getInt(BlockIds.GRANITE_BRICK_DEFAULT);
			BlockIds.STRANGE_CRYSTAL_ORE = configuration.getBlock(Strings.BLOCK_STRANGE_CRYSTAL_ORE_NAME,
					BlockIds.STRANGE_CRYSTAL_ORE_DEFAULT).getInt(BlockIds.STRANGE_CRYSTAL_ORE_DEFAULT);
			BlockIds.META_BLOCK = configuration.getBlock(Strings.BLOCK_META_NAME, BlockIds.META_BLOCK_DEFAULT).getInt(
					BlockIds.META_BLOCK_DEFAULT);

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
