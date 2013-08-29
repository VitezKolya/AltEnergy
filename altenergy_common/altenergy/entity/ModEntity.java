
package altenergy.entity;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import altenergy.entity.monster.FooRoo;
import cpw.mods.fml.common.registry.EntityRegistry;

public class ModEntity {

	public void init() {

		EntityRegistry.addSpawn(FooRoo.class, 5, 1, 5, EnumCreatureType.monster, BiomeGenBase.biomeList);
	}
}
