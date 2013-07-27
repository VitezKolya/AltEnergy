package altenergy.core.handlers;

import buildcraft.api.power.IPowerProvider;
import altenergy.lib.Reference;
import cpw.mods.fml.common.Loader;

public class AltEnergyHooks {
	
	public boolean IC2Loaded = false;
	public boolean BuildCraftLoaded = false;
	public boolean BasicComponetsLoaded = false;
	public boolean TELoaded = false;
	public boolean ICBMLoaded = false;
	
	public void hooks() {
		
		if(Loader.isModLoaded("IC2")) this.IC2Loaded = true;
		if(Loader.isModLoaded("BuildCraft|Energy")) this.BuildCraftLoaded = true;
		if(Loader.isModLoaded("BasicComponents")) this.BasicComponetsLoaded = true;
		if(Loader.isModLoaded("ThermalExpansion")) this.TELoaded = true;
		if(Loader.isModLoaded("ICBM|Explosion")) this.ICBMLoaded = true;
		
		if(IC2Loaded) {

			System.out.print("[" + Reference.MOD_ID + "] Hooked into IC2 successfully.");
		}
		
		if(BuildCraftLoaded) {
			
			System.out.print("[" + Reference.MOD_ID + "] Hooked into BuildCraft successfully.");
		}
		
		if(BasicComponetsLoaded) {
			
			System.out.print("[" + Reference.MOD_ID + "] Hooked into BasicComponets successfully.");
		}
		
		if(TELoaded) {
			
			System.out.print("[" + Reference.MOD_ID + "] Hooked into ThermalExpansion successfully.");
		}
		
		if(ICBMLoaded) {
			
		}
	}
}
