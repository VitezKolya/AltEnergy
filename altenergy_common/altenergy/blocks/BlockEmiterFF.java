
package altenergy.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import altenergy.AltEnergy;
import altenergy.lib.Strings;

public class BlockEmiterFF extends BlockContainerAE {

	protected BlockEmiterFF(int id) {

		super(id, Material.iron);
		this.setCreativeTab(AltEnergy.tabsAE);
		this.setUnlocalizedName(Strings.BLOCK_EMITER_FF);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {

		// TODO Auto-generated method stub
		return null;
	}

}
