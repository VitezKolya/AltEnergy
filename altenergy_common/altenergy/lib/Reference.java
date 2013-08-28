
package altenergy.lib;

public class Reference {
	public static final String MOD_ID = "alte";
	public static final String MOD_NAME = "AltEnergy";
	public static final String VERSION = "@VERSION@ (build @BUILD_NUMBER@)";
	public static final String CHANNEL_NAME = MOD_ID;
	public static final String DEPENDENCIES = "required-after:Forge@[7.8.1.738]";
	public static final String FINGERPRINT = "@FINGERPRINT@";
	public static final int SECOND_IN_TICKS = 20;
	public static final int SHIFTED_ID_RANGE_CORRECTION = 256;
	public static final int ITEM_ID_PREFIX = 18700;
	public static final int BLOCK_ID_PREFIX = 1640;
	public static final String SERVER_PROXY_CLASS = "altenergy.core.proxy.CommonProxy";
	public static final String CLIENT_PROXY_CLASS = "altenergy.core.proxy.ClientProxy";
}
