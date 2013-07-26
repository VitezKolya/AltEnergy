
package altenergy.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import altenergy.AltEnergy;
import altenergy.lib.Reference;
import altenergy.lib.Strings;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockController extends BlockAE {

	protected BlockController(int id) {

		super(id, Material.iron);
		this.setCreativeTab(AltEnergy.tabsAE);
		this.setUnlocalizedName(Strings.BLOCK_CONTROLLER);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {

		iconRegister.registerIcon(Reference.MOD_ID.toLowerCase() + ":" + Strings.BLOCK_CONTROLLER);
	}
}
