
package altenergy.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import altenergy.AltEnergy;
import altenergy.items.ModItems;
import altenergy.lib.ItemIds;
import altenergy.lib.Reference;
import altenergy.lib.Strings;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockStrangeCrystalOre extends Block {

	public Icon icon;

	public BlockStrangeCrystalOre(int par1) {

		super(par1, Material.rock);

		setCreativeTab(AltEnergy.tabsAE);
		setUnlocalizedName(Strings.BLOCK_STRANGE_CRYSTAL_ORE_NAME);
		setHardness(20.0F);
		setResistance(4500.0F);
	}

	/**
	 * Returns the ID of the items to drop on destruction.
	 */
	// @Override
	@Override
	public int idDropped(int meta, Random par2Random, int fortune) {

		return ItemIds.GEMS;
	}

	/**
	 * Returns the quantity of items to drop on block destruction.
	 */
	// @Override
	@Override
	public int quantityDropped(Random par1Random) {

		return par1Random.nextInt(3);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int id, int meta) {

		return icon;
	}

	/**
	 * Drops the block items with a specified chance of dropping the specified
	 * items
	 */
	// @Override
	@Override
	public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7) {

		super.dropBlockAsItemWithChance(par1World, par2, par3, par4, par5, par6, par7);

		if (idDropped(par5, par1World.rand, par7) != blockID || idDropped(par5, par1World.rand, par7) != ItemIds.GEMS) {

			int var8 = 1 + par1World.rand.nextInt(5);

			dropBlockAsItem_do(par1World, par2, par3, par4, new ItemStack(ModItems.gems, 1, 0));

			if (par1World.rand.nextInt(5) != 4) {

				for (int i = 0; i < this.quantityDropped(par1World.rand); i++) {

					dropBlockAsItem_do(par1World, par2, par3, par4, new ItemStack(ModItems.dusts, 1, 0));
				}
			}

			dropXpOnBlockBreak(par1World, par2, par3, par4, var8);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {

		icon = iconRegister.registerIcon(Reference.MOD_ID.toLowerCase() + ":" + Strings.BLOCK_STRANGE_CRYSTAL_ORE_NAME);
	}
}
