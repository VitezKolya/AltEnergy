package altenergy.configuration;

public class ConfigurationSettings {
	/*
	 * Enable ores
	 */
	public static boolean ORE_GENERATION_ENABLE;
	public static final String ORE_GENERATION_ENABLE_CONFIGNAME = "ore_generation.enable";
	public static final boolean ORE_GENERATION_ENABLE_DEFAULT = true;

	/* Granite */
	public static boolean GRANITE_GENERATION_ENABLE;
	public static final String GRANITE_GENERATION_ENABLE_CONFIGNAME = "granite_generation.enable";
	public static final boolean GRANITE_GENERATION_ENABLE_DEFAULT = true;

	public static int GRANITE_GENERATION_VEIN_SIZE;
	public static String GRANITE_GENERATION_VEIN_SIZE_CONFIGNAME = "granite_generation.vein_size";
	public static final int GRANITE_GENERATION_VEIN_SIZE_DEFAULT = 24;

	public static int GRANITE_GENERATION_VEIN_PERCHUNK;
	public static String GRANITE_GENERATION_VEIN_PERCHUNK_CONFIGNAME = "granite_generation.vein_perchunk";
	public static final int GRANITE_GENERATION_VEIN_PERCHUNK_DEFAULT = 5;

	public static int GRANITE_GENERATION_MAX_HIGHT;
	public static final String GRANITE_GENERATION_MAX_HIGHT_CONFIGNAME = "granite_generation.max_hight";
	public static final int GRANITE_GENERATION_MAX_HIGHT_DEFAULT = 64;

	public static int GRANITE_GENERATION_MIN_HIGHT;
	public static final String GRANITE_GENERATION_MIN_HIGHT_CONFIGNAME = "granite_generation.min_hight";
	public static final int GRANITE_GENERATION_MIN_HIGHT_DEFAULT = 4;

	public static boolean GRANITE_CHEAT_ENABLE;
	public static final String GRANITE_CHEAT_ENABLE_CONFIGNAME = "granite_cheat.enable";
	public static final boolean GRANITE_CHEAT_ENABLE_DEFAULT = false;

	/* Strange Crystal */
	public static boolean STRANGE_CRYSTAL_GENERATION_ENABLE;
	public static final String STRANGE_CRYSTAL_GENERATION_ENABLE_CONFIGNAME = "strange_crystal_generation.enable";
	public static final boolean STRANGE_CRYSTAL_GENERATION_ENABLE_DEFAULT = true;

	public static int STRANGE_CRYSTAL_GENERATION_VEIN_SIZE;
	public static String STRANGE_CRYSTAL_GENERATION_VEIN_SIZE_CONFIGNAME = "strange_crystal_generation.vein_size";
	public static final int STRANGE_CRYSTAL_GENERATION_VEIN_SIZE_DEFAULT = 2;

	public static int STRANGE_CRYSTAL_GENERATION_VEIN_PERCHUNK;
	public static String STRANGE_CRYSTAL_GENERATION_VEIN_PERCHUNK_CONFIGNAME = "strange_crystal_generation.vein_perchunk";
	public static final int STRANGE_CRYSTAL_GENERATION_VEIN_PERCHUNK_DEFAULT = 8;

	public static int STRANGE_CRYSTAL_GENERATION_MAX_HIGHT;
	public static final String STRANGE_CRYSTAL_GENERATION_MAX_HIGHT_CONFIGNAME = "strange_crystal_generation.max_hight";
	public static final int STRANGE_CRYSTAL_GENERATION_MAX_HIGHT_DEFAULT = 64;

	public static int STRANGE_CRYSTAL_GENERATION_MIN_HIGHT;
	public static final String STRANGE_CRYSTAL_GENERATION_MIN_HIGHT_CONFIGNAME = "strange_crystal_generation.min_hight";
	public static final int STRANGE_CRYSTAL_GENERATION_MIN_HIGHT_DEFAULT = 4;

	public static boolean STRANGE_CRYSTAL_GENERATE_GRANITE_ONLY_ENABLE;
	public static final String STRANGE_CRYSTAL_GENERATE_GRANITE_ONLY_ENABLE_CONFIGNAME = "strange_crystal_generate.only_granite";
	public static final boolean STRANGE_CRYSTAL_GENERATE_GRANITE_ONLY_ENABLE_DEFAULT = true;

	/* Mod API */
	public static boolean IC2_INTEGRATION_ENABLE;
	public static final String IC2_CONFIGNAME = "IC2";
	public static final boolean IC2_ENABLE_DEFAULT = false;

	public static boolean BUILDCRAFT_INTEGRATION_ENABLE;
	public static final String BUILDCRAFT_CONFIGNAME = "Buildcraft";
	public static final boolean BUILDCRAFT_ENABLE_DEFAULT = false;

	public static boolean THERMAL_EXPANSION_INTEGRATION_ENABLE;
	public static final String THERMAL_EXPANSION_CONFIGNAME = "Thermal Expansion";
	public static final boolean THERMAL_EXPANSION_ENABLE_DEFAULT = false;

	public static boolean COMPUTERCRAFT_INTEGRATION_ENABLE;
	public static final String COMPUTERCRAFT_CONFIGNAME = "ComputerCraft";
	public static final boolean COMPUTERCRAFT_ENABLE_DEFAULT = false;

	public static boolean ATOMICSCIENCE_INTERGRATION_ENABLE;
	public static final String ATOMICSCIENCE_CONFIGNAME = "Atomic Science";
	public static final boolean ATOMICSCIENCE_ENABLE_DEFAULT = false;

	public static boolean MEKANISM_INTERGRATION_ENABLE;
	public static final String MEKANISM_CONFIGNAME = "Mekanism";
	public static final boolean MEKANISM_ENABLE_DEFAULT = false;

	public static boolean UE_INTERGRATION_ENABLE;
	public static final String UE_CONFIGNAME = "Universal Electricity";
	public static final boolean UE_ENABLE_DEFAULT = false;

	public static boolean ICBM_INTERGRATION_ENABLE;
	public static final String ICBM_CONFIGNAME = "ICBM";
	public static final boolean ICBM_ENABLE_DEFAULT = false;
}
