package altenergy.blocks;

import altenergy.AltEnergy;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockEmiterFF extends BlockContainerAE{

	protected BlockEmiterFF(int id) {

		super(id, Material.iron);
		this.setCreativeTab(AltEnergy.tabsAE);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {

		// TODO Auto-generated method stub
		return null;
	}

}
