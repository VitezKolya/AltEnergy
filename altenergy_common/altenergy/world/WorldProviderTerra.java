package altenergy.world;

import altenergy.lib.DimensionIds;
import altenergy.lib.Strings;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldProviderHell;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.DimensionManager;


public class WorldProviderTerra extends WorldProvider{

	@Override
	public void registerWorldChunkManager() {
		this.worldChunkMgr = new WorldChunkManagerTerra(worldObj.getSeed(), terrainType);
		this.hasNoSky = false;
	}
	
	@Override
	public String getDimensionName() {

		return Strings.DIM_TERRA;
	}
	
	public static WorldProvider getProviderForDimension(int id) {
		return DimensionManager.createProviderFor(DimensionIds.TERRA);
	}
	
	@Override
	public String getWelcomeMessage() {
		return "Entering " + Strings.DIM_TERRA;
	}
	
	@Override
	public IChunkProvider createChunkGenerator() {
		return new ChunkProviderTerra(worldObj, worldObj.getSeed(), true);
	}
	
	@Override
	public boolean canRespawnHere() {
		return true;
	}
	
	protected synchronized String setUserMessage(String msg) {
		return "Building " + Strings.DIM_TERRA;
	}
	
	@Override
	public int getRespawnDimension(EntityPlayerMP player) {
        return 0;
    }

	@Override
	 public double getMovementFactor() {
	    return 1.0;
	 }
}
