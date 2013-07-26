package altenergy.blocks;

import altenergy.AltEnergy;
import net.minecraft.block.material.Material;

public class BlockPowerRegulator extends BlockAE{

	public BlockPowerRegulator(int id) {

		super(id, Material.iron);
		this.setCreativeTab(AltEnergy.tabsAE);
	}

}
