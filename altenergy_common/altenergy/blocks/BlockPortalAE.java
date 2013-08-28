
package altenergy.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPortal;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import altenergy.lib.BlockIds;
import altenergy.lib.DimensionIds;
import altenergy.lib.Reference;
import altenergy.lib.Strings;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockPortalAE extends BlockPortal {

	Icon icon;

	public BlockPortalAE(int id) {

		super(id);
		// this.setCreativeTab(AltEnergy.tabsAE);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int id, int meta) {

		return icon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {

		icon = iconRegister.registerIcon(Reference.MOD_ID.toLowerCase() + ":" + Strings.BLOCK_GRANITE_NAME);
	}

	@Override
	public void updateTick(World world, int par1, int par2, int par3, Random random) {

	}

	@Override
	public boolean tryToCreatePortal(World world, int x, int y, int z) {

		byte b0 = 0;
		byte b1 = 0;

		if (world.getBlockId(x - 1, y, z) == BlockIds.GRANITEDEC
				|| world.getBlockId(x + 1, y, z) == BlockIds.GRANITEDEC) {
			b0 = 1;
		}

		if (world.getBlockId(x, y, z - 1) == BlockIds.GRANITEDEC
				|| world.getBlockId(x, y, z + 1) == BlockIds.GRANITEDEC) {
			b1 = 1;
		}

		if (b0 == b1) {
			return false;
		} else {
			if (world.isAirBlock(x - b0, y, z - b1)) {
				x -= b0;
				z -= b1;
			}

			int l;
			int i1;

			for (l = -1; l <= 2; ++l) {
				for (i1 = -1; i1 <= 3; ++i1) {
					boolean flag = l == -1 || l == 2 || i1 == -1 || i1 == 3;

					if (l != -1 && l != 2 || i1 != -1 && i1 != 3) {
						int j1 = world.getBlockId(x + b0 * l, y + i1, z + b1 * l);
						boolean isAirBlock = world.isAirBlock(x + b0 * l, y + i1, z + b1 * l);

						if (flag) {
							if (j1 != BlockIds.GRANITEDEC) {
								return false;
							}
						} else if (!isAirBlock && j1 != Block.fire.blockID) {
							return false;
						}
					}
				}
			}

			for (l = 0; l < 2; ++l) {
				for (i1 = 0; i1 < 3; ++i1) {
					world.setBlock(x + b0 * l, y + i1, z + b1 * l, BlockIds.PORTAL, 0, 2);
				}
			}

			return true;
		}
	}

	@Override
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5) {

		byte b0 = 0;
		byte b1 = 1;

		if (par1World.getBlockId(par2 - 1, par3, par4) == blockID
				|| par1World.getBlockId(par2 + 1, par3, par4) == blockID) {
			b0 = 1;
			b1 = 0;
		}

		int i1;

		for (i1 = par3; par1World.getBlockId(par2, i1 - 1, par4) == blockID; --i1) {
			;
		}

		if (par1World.getBlockId(par2, i1 - 1, par4) != BlockIds.GRANITEDEC) {
			par1World.setBlockToAir(par2, par3, par4);
		} else {
			int j1;

			for (j1 = 1; j1 < 4 && par1World.getBlockId(par2, i1 + j1, par4) == blockID; ++j1) {
				;
			}

			if (j1 == 3 && par1World.getBlockId(par2, i1 + j1, par4) == BlockIds.GRANITEDEC) {
				boolean flag = par1World.getBlockId(par2 - 1, par3, par4) == blockID
						|| par1World.getBlockId(par2 + 1, par3, par4) == blockID;
				boolean flag1 = par1World.getBlockId(par2, par3, par4 - 1) == blockID
						|| par1World.getBlockId(par2, par3, par4 + 1) == blockID;

				if (flag && flag1) {
					par1World.setBlockToAir(par2, par3, par4);
				} else {
					if ((par1World.getBlockId(par2 + b0, par3, par4 + b1) != BlockIds.GRANITEDEC || par1World
							.getBlockId(par2 - b0, par3, par4 - b1) != blockID)
							&& (par1World.getBlockId(par2 - b0, par3, par4 - b1) != BlockIds.GRANITEDEC || par1World
									.getBlockId(par2 + b0, par3, par4 + b1) != blockID)) {
						par1World.setBlockToAir(par2, par3, par4);
					}
				}
			} else {
				par1World.setBlockToAir(par2, par3, par4);
			}
		}
	}
	
	public void onEntityCollidedWithBlock(World world, int par2, int par3, int par4, Entity entity)
    {
        if (entity.ridingEntity == null && entity.riddenByEntity == null)
        {
        	entity.travelToDimension(DimensionIds.TERRA);
        	//entity.setInPortal();
        }
    }
}
