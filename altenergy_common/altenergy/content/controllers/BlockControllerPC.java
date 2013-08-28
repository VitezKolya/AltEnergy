
package altenergy.content.controllers;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import universalelectricity.prefab.block.BlockAdvanced;
import altenergy.AltEnergy;
import altenergy.core.misc.TilePowerCoreBase;
//import altenergy.content.controllers.TileControllerPC;
import altenergy.lib.GuiIds;
import altenergy.lib.RenderIds;
import altenergy.lib.Strings;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockControllerPC extends BlockAdvanced {

	public BlockControllerPC(int id) {

		super(id, Material.iron);
		setUnlocalizedName(Strings.BLOCK_CONTROLLER_PC);
		setCreativeTab(AltEnergy.tabsAE);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {

		return new TileControllerPC();
	}

	@Override
	public boolean renderAsNormalBlock() {

		return false;
	}

	@Override
	public boolean isOpaqueCube() {

		return false;
	}

	@Override
	public int getRenderType() {

		return RenderIds.controllerPC;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {

	}

	@Override
	public void breakBlock(World world, int x, int y, int z, int id, int data) {

		TileControllerPC tileEntity = (TileControllerPC) world.getBlockTileEntity(x, y, z);
		super.breakBlock(world, x, y, z, id, data);

		if (tileEntity == null) {
			System.out.printf("BlockControllerPC.breakBlock: No tile entity at (%d,%d,%d)\n", x, y, z);
		} else if (tileEntity.isLinked) {
			TilePowerCoreBase core = (TilePowerCoreBase) tileEntity.getLinked();
			if (core != null) {
				core.clearLink();
			}
		}
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving player, ItemStack stack) {

		super.onBlockPlacedBy(world, x, y, z, player, stack);
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		if (tileEntity instanceof TileControllerPC) {
			((TileControllerPC) tileEntity).linkToControlled();
		}
	}

	@Override
	public boolean onMachineActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX,
			float hitY, float hitZ) {

		if (player.isSneaking()) {
			return false;
		} else {
			if (!world.isRemote) {
				TileControllerPC tileEntity = (TileControllerPC) world.getBlockTileEntity(x, y, z);
				tileEntity.findControlled();
				if (tileEntity != null) {
					player.openGui(AltEnergy.instance, GuiIds.CONTROLLER_PC, world, x, y, z);
				}
			}
			return true;
		}
	}
}