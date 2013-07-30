
package altenergy.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import universalelectricity.prefab.block.BlockConductor;
import altenergy.AltEnergy;
import altenergy.lib.Reference;
import altenergy.lib.Strings;
import altenergy.tileentity.TileEnergyCable;

public class BlockEnergyCable extends BlockConductor {

	public BlockEnergyCable(int id) {

		super(id, Material.iron);
		this.setResistance(0.0001f);
		this.setHardness(0.1f);
		this.setCreativeTab(AltEnergy.tabsAE);
		Block.setBurnProperties(id, 0, 0);
	}

	@Override
	public void registerIcons(IconRegister iconRegister) {

		blockIcon = iconRegister.registerIcon(Reference.MOD_ID.toLowerCase() + ":" + Strings.BLOCK_ENERGY_CABLE);
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

		return new TileEnergyCable();
	}

}
