
package altenergy.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import universalelectricity.prefab.block.BlockAdvanced;
import altenergy.AltEnergy;
import altenergy.lib.Reference;
import altenergy.lib.Strings;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockMiniPowerCore extends BlockAdvanced {

	private Icon iconOutput;
	private Icon iconTop;
	private Icon iconBlock;
	private Icon iconFace;

	public BlockMiniPowerCore(int id) {

		super(id, Material.iron);
		this.setUnlocalizedName(Strings.BLOCK_MINI_POWER_CORE);
		this.setCreativeTab(AltEnergy.tabsAE);
	}

	@Override
	public void registerIcons(IconRegister iconRegister) {

		iconOutput = iconRegister.registerIcon(Reference.MOD_ID.toLowerCase() + ":" + Strings.BLOCK_MINI_POWER_CORE
				+ "_OutputSide");
		iconTop = iconRegister.registerIcon(Reference.MOD_ID.toLowerCase() + ":" + Strings.BLOCK_MINI_POWER_CORE
				+ "TopSide");
		iconBlock = iconRegister.registerIcon(Reference.MOD_ID.toLowerCase() + ":" + Strings.BLOCK_MINI_POWER_CORE
				+ "_MachineSide");
		iconFace = iconRegister.registerIcon(Reference.MOD_ID.toLowerCase() + ":" + Strings.BLOCK_MINI_POWER_CORE
				+ "_FaceSide");
	}

	@Override
	public Icon getIcon(int side, int metadata) {

		if (side == 1) {
			return iconTop;
		} else {
			return iconBlock;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getBlockTexture(IBlockAccess world, int x, int y, int z, int side) {

		return null;
	}
}
