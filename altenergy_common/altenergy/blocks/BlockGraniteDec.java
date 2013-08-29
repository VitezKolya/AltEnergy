
package altenergy.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import altenergy.AltEnergy;
import altenergy.core.misc.BlockAE;
import altenergy.lib.Reference;
import altenergy.lib.Strings;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockGraniteDec extends BlockAE {

	public Icon icon;

	public BlockGraniteDec(int id) {

		super(id, Material.rock);

		setCreativeTab(AltEnergy.tabsAE);
		setUnlocalizedName(Strings.BLOCK_GRANITEDEC_NAME);
		setHardness(20.0F);
		setResistance(4500.0F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int id, int meta) {

		return icon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {

		icon = iconRegister.registerIcon(Reference.MOD_ID.toLowerCase() + ":" + Strings.BLOCK_GRANITEDEC_NAME);
	}
}