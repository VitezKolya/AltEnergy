
package altenergy.blocks;

import net.minecraft.block.material.Material;
import altenergy.AltEnergy;
import altenergy.lib.Strings;

public class BlockPowerRegulator extends BlockAE {

	public BlockPowerRegulator(int id) {

		super(id, Material.iron);
		this.setCreativeTab(AltEnergy.tabsAE);
		this.setUnlocalizedName(Strings.BLOCK_POWER_REGULATOR);
	}

}
