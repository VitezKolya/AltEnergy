
package altenergy.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import altenergy.AltEnergy;
import altenergy.lib.Reference;
import altenergy.tileentity.TileAE;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class BlockAE extends Block {

	public BlockAE(int id, Material material) {

		super(id, material);
		this.setCreativeTab(AltEnergy.tabsAE);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister register) {

		register.registerIcon(Reference.MOD_ID + ":"
				+ this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
	}
/*
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving entityLiving, ItemStack itemStack) {

		int direction = 0;
		int facing = MathHelper.floor_double(entityLiving.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

		switch (facing) {
			case 0:
				direction = ForgeDirection.NORTH.ordinal();
				break;
			case 1:
				direction = ForgeDirection.EAST.ordinal();
				break;
			case 2:
				direction = ForgeDirection.SOUTH.ordinal();
				break;
			case 3:
				direction = ForgeDirection.WEST.ordinal();
				break;
		}

		world.setBlockMetadataWithNotify(x, y, z, direction, 3);

		if (itemStack.hasDisplayName()) {
			((TileAE) world.getBlockTileEntity(x, y, z)).setCustomName(itemStack.getDisplayName());
		}

		((TileAE) world.getBlockTileEntity(x, y, z)).setOrientation(direction);
	}*/
}
