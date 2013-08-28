
package altenergy.content.powerregulator;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import altenergy.AltEnergy;
import altenergy.core.misc.BlockAE;
import altenergy.lib.Strings;

public class BlockPowerRegulator extends BlockAE {

	public BlockPowerRegulator(int id) {

		super(id, Material.iron);
		this.setCreativeTab(AltEnergy.tabsAE);
		this.setUnlocalizedName(Strings.BLOCK_POWER_REGULATOR);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {

		//iconRegister.registerIcon(Reference.MOD_ID.toLowerCase() + ":" + Strings.BLOCK_POWER_REGULATOR);
	}
}
