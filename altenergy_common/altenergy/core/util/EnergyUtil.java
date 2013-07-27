package altenergy.core.util;

import altenergy.AltEnergy;
import buildcraft.api.power.IPowerReceptor;
import ic2.api.energy.tile.IEnergySink;
import mekanism.api.IStrictEnergyAcceptor;
import mekanism.api.IUniversalCable;
import mekanism.api.Object3D;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public class EnergyUtil {
	
	/**
	 * Gets all the connected energy acceptors, whether IC2-based or BuildCraft-based, surrounding a specific tile entity.
	 * @param tileEntity - center of tile entity
	 * @return TileEntity[] of connected acceptors
	 */
	public static TileEntity[] getConnectedEnergyRecivers(TileEntity tileEntity) {
		
		TileEntity[] acceptors = new TileEntity[] {null, null, null, null, null, null};
		
		for(ForgeDirection orientation : ForgeDirection.VALID_DIRECTIONS) {
			TileEntity acceptor = Object3D.get(tileEntity).getFromSide(orientation).getTileEntity(tileEntity.worldObj);
			
			if(acceptor instanceof IStrictEnergyAcceptor || acceptor instanceof IEnergySink || (acceptor instanceof IPowerReceptor && !(acceptor instanceof IUniversalCable) && AltEnergy.hooks.BuildCraftLoaded)) {
				acceptors[orientation.ordinal()] = acceptor;
			}
		}
		
		return acceptors;
	}
	
	/**
	* Gets all the connected cables around a specific tile entity.
	* @param tileEntity - center tile entity
	* @return TileEntity[] of connected cables
	*/
	public static TileEntity[] getConnectedCables(TileEntity tileEntity) {
		
		TileEntity[] cables = new TileEntity[] {null, null, null, null, null, null};
		
		for(ForgeDirection orientation : ForgeDirection.VALID_DIRECTIONS) {
			TileEntity cable = Object3D.get(tileEntity).getFromSide(orientation).getTileEntity(tileEntity.worldObj);
			
			if(cable instanceof IUniversalCable) {
				cables[orientation.ordinal()] = cable;
			}
		}
		return cables;
	}
	
	
}
