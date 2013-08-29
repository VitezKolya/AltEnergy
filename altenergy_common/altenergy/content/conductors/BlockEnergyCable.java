
package altenergy.content.conductors;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemDye;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import universalelectricity.core.block.IConductor;
import universalelectricity.prefab.block.BlockConductor;
import universalelectricity.prefab.network.PacketManager;
import universalelectricity.prefab.tile.TileEntityConductor;
import altenergy.AltEnergy;
import altenergy.lib.Reference;
import altenergy.lib.RenderIds;
import altenergy.lib.Strings;

public class BlockEnergyCable extends BlockConductor {

	public BlockEnergyCable(int id) {

		super(id, Material.iron);
		setResistance(0.2f);
		setUnlocalizedName(Strings.BLOCK_ENERGY_CABLE);
		setHardness(0.1f);
		setCreativeTab(AltEnergy.tabsAE);
		setBlockBounds(0.25f, 0.25f, 0.25f, 0.75f, 0.75f, 0.75f);
	}

	@Override
	public void registerIcons(IconRegister iconRegister) {

		// blockIcon = iconRegister.registerIcon(Reference.MOD_ID + ":" +
		// Strings.BLOCK_ENERGY_BUSWAY);
	}

	@Override
	public boolean hasTileEntity(int metadata) {

		return true;
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
	public int damageDropped(int i) {

		return i;
	}

	@Override
	public int getRenderType() {

		return RenderIds.cableRenderId;
	}

	@Override
	public TileEntity createNewTileEntity(World world) {

		return new TileEnergyCable();
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z) {

		super.onBlockAdded(world, x, y, z);

		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);

		if (tileEntity == null) {
			if (tileEntity instanceof IConductor) {

				((IConductor) tileEntity).updateAdjacentConnections();
				updateConductorSwitch(world, x, y, z);
			}

		}
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7,
			float par8, float par9) {

		TileEnergyBusway tileEntity = (TileEnergyBusway) world.getBlockTileEntity(x, y, z);

		if (!world.isRemote) {
			if (player.inventory.getCurrentItem() != null) {
				if (player.inventory.getCurrentItem().getItem() instanceof ItemDye) {

					player.inventory.getCurrentItem().stackSize = player.inventory.getCurrentItem().stackSize - 1;

					PacketManager.sendPacketToClients(PacketManager.getPacket(Reference.CHANNEL_NAME, tileEntity,
							(byte) 0));

					((IConductor) tileEntity).updateAdjacentConnections();

					updateConductorSwitch(world, x, y, z);

					return true;
				}
			}
		}

		return false;
	}

	private void updateConductorSwitch(World world, int x, int y, int z) {

		TileEnergyBusway tileEntity = (TileEnergyBusway) world.getBlockTileEntity(x, y, z);

		TileEntity tileEntity1;

		if (!world.isRemote && tileEntity != null) {
			for (byte sides = 0; sides < 6; sides++) {
				switch (sides) {
					case 0:
						tileEntity1 = world.getBlockTileEntity(x + 1, y, z);
						break;
					case 1:
						tileEntity1 = world.getBlockTileEntity(x - 1, y, z);
						break;
					case 2:
						tileEntity1 = world.getBlockTileEntity(x, y + 1, z);
						break;
					case 3:
						tileEntity1 = world.getBlockTileEntity(x, y - 1, z);
						break;
					case 4:
						tileEntity1 = world.getBlockTileEntity(x, y, z + 1);
						break;
					case 5:
						tileEntity1 = world.getBlockTileEntity(x, y, z - 1);
						break;
					default:
						tileEntity1 = world.getBlockTileEntity(x, y, z);
				}

				if (tileEntity1 instanceof IConductor) {
					((IConductor) tileEntity1).updateAdjacentConnections();
					tileEntity1.worldObj.markBlockForUpdate(x, y, z);
				}
			}
		}
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess blockAccess, int x, int y, int z) {

		TileEntity tileEntity = blockAccess.getBlockTileEntity(x, y, z);
		if (tileEntity instanceof TileEntityConductor) {
			TileEntityConductor te = (TileEntityConductor) tileEntity;

			minX = te.connectedBlocks[4] != null ? 0F : 0.3F;
			minY = te.connectedBlocks[0] != null ? 0F : 0.3F;
			minZ = te.connectedBlocks[2] != null ? 0F : 0.3F;
			maxX = te.connectedBlocks[5] != null ? 1F : 0.7F;
			maxY = te.connectedBlocks[1] != null ? 1F : 0.7F;
			maxZ = te.connectedBlocks[3] != null ? 1F : 0.7F;
		}
	}
}
