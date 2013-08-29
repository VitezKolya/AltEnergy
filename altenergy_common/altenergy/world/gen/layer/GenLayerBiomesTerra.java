
package altenergy.world.gen.layer;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import altenergy.world.biomes.AEBiomeList;

public class GenLayerBiomesTerra extends GenLayer {

	protected BiomeGenBase[] allowedBiomes = {
		AEBiomeList.terraOcean
	};

	public GenLayerBiomesTerra(long seed, GenLayer genlayer) {

		super(seed);
		parent = genlayer;
	}

	public GenLayerBiomesTerra(long seed) {

		super(seed);
	}

	@Override
	public int[] getInts(int x, int z, int width, int depth) {

		int[] dest = IntCache.getIntCache(width * depth);

		for (int dz = 0; dz < depth; dz++) {
			for (int dx = 0; dx < width; dx++) {
				initChunkSeed(dx + x, dz + z);
				dest[dx + dz * width] = allowedBiomes[nextInt(allowedBiomes.length)].biomeID;
			}
		}

		return dest;
	}

}
