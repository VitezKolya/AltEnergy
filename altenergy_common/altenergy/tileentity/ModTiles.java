package altenergy.tileentity;

import altenergy.lib.Strings;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModTiles {
	public static void init() {
		
		GameRegistry.registerTileEntity(TileControllerFF.class, Strings.TILE_CONTROLLER_FF);
		GameRegistry.registerTileEntity(TileMiniPowerCore.class, Strings.TILE_MINI_POWER_CORE);
	}
}
