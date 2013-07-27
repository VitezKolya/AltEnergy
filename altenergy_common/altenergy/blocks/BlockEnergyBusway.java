package altenergy.blocks;

import altenergy.AltEnergy;
import altenergy.lib.Reference;
import altenergy.lib.Strings;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import universalelectricity.prefab.block.BlockConductor;

public class BlockEnergyBusway extends BlockConductor{

	public BlockEnergyBusway(int id) {

		super(id, Material.iron);
		this.setResistance(0.0001f);
		this.setHardness(0.1f);
		this.setCreativeTab(AltEnergy.tabsAE);
	}
	
	@Override
	public void registerIcons(IconRegister iconRegister) {
		this.blockIcon = iconRegister.registerIcon(Reference.MOD_ID.toLowerCase() + ":" + Strings.BLOCK_ENERGY_BUSWAY);
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	@Override
	public int getRenderType() {
		return -1;
	}

	@Override
	public TileEntity createNewTileEntity(World world) {

		// TODO Auto-generated method stub
		return null;
	}

}
