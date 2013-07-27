package altenergy.tileentity;

import universalelectricity.prefab.tile.TileEntityConductor;

public class TileEnergyBusway extends TileEntityConductor{

	public static float RESISTANCE = 0.0001f;
	public static float MAX_AMPS = 5000000f;
	
	@Override
	public double getResistance() {

		return RESISTANCE;
	}

	@Override
	public double getCurrentCapcity() {

		return MAX_AMPS;
	}

}
