
package altenergy.core.misc;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import altenergy.lib.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class BlockContainerAE extends BlockContainer {

	protected BlockContainerAE(int id, Material material) {

		super(id, material);
		// TODO Auto-generated constructor stub

		if (material == Material.rock) {
			setStepSound(soundStoneFootstep);
		} else if (material == Material.iron) {
			setStepSound(soundMetalFootstep);
		} else if (material == Material.glass) {
			setStepSound(soundGlassFootstep);
		} else if (material == Material.sand) {
			setStepSound(soundGravelFootstep);
		} else if (material == Material.ground) {
			setStepSound(soundGravelFootstep);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {

		iconRegister.registerIcon(Reference.MOD_ID + ":"
				+ getUnlocalizedName().substring(getUnlocalizedName().indexOf(".") + 1));
	}
}
