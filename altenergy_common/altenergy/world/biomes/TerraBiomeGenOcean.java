
package altenergy.world.biomes;

import net.minecraft.world.biome.BiomeGenBase;

public class TerraBiomeGenOcean extends BiomeGenBase {

	public TerraBiomeGenOcean(int par1) {

		super(par1);
		spawnableCreatureList.clear();
        setBiomeName("Ocean");
        setColor(112);
        setMinMaxHeight(-1.0F, 0.4F);
	}
}
