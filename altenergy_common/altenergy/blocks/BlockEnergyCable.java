package altenergy.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import universalelectricity.prefab.block.BlockConductor;

public class BlockEnergyCable extends BlockConductor{

	public BlockEnergyCable(int id) {

		super(id, Material.cloth);
		// TODO Auto-generated constructor stub
	}

	@Override
	public TileEntity createNewTileEntity(World world) {

		// TODO Auto-generated method stub
		return null;
	}

}
