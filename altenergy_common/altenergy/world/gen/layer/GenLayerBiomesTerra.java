package altenergy.world.gen.layer;

import altenergy.world.biomes.AEBiomeList;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;


public class GenLayerBiomesTerra extends GenLayer {

	protected BiomeGenBase[] allowedBiomes = {
			AEBiomeList.terraOcean
	};
	
	public GenLayerBiomesTerra(long seed, GenLayer genlayer) {

		super(seed);
		this.parent = genlayer;
	}
	
	public GenLayerBiomesTerra(long seed) {
		super(seed);
	}

	@Override
	public int[] getInts(int x, int z, int width, int depth) {

		int[] dest = IntCache.getIntCache(width*depth);
		
		for(int dz = 0; dz < depth; dz++) {
			for(int dx = 0; dx < width; dx++) {
				this.initChunkSeed(dx + x,  dz + z);
				dest[(dx + dz * width)] = this.allowedBiomes[nextInt(this.allowedBiomes.length)].biomeID;
			}
		}
		
		return dest;
	}

}
