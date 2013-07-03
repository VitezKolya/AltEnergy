package altenergy.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import altenergy.common.BlockContainerAE;

public class BlockGeneratorCapDummy extends BlockContainerAE{

	protected BlockGeneratorCapDummy(int id, Material material) {
		super(id, material);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		// TODO Auto-generated method stub
		return null;
	}

}
