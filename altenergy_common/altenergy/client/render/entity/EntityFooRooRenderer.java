
package altenergy.client.render.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.EntityLiving;
import altenergy.entity.monster.FooRoo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EntityFooRooRenderer extends RenderLiving {

	/** The fooroo model. */
	// private ModelBase foorooModel = new ModelFooRoo(2.0F);

	public EntityFooRooRenderer(ModelBase par1ModelBase, float par2) {

		super(par1ModelBase, par2);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Updates color multiplier based on creeper state called by
	 * getColorMultiplier
	 */
	protected int updateFooRooColorMultiplier(FooRoo fooroo, float par2, float par3) {

		float f2 = fooroo.getFooRooFlashIntensity(par3);

		if ((int) (f2 * 10.0F) % 2 == 0) {
			return 0;
		} else {
			int i = (int) (f2 * 0.2F * 255.0F);

			if (i < 0) {
				i = 0;
			}

			if (i > 255) {
				i = 255;
			}

			short short1 = 255;
			short short2 = 255;
			short short3 = 255;
			return i << 24 | short1 << 16 | short2 << 8 | short3;
		}
	}

	/**
	 * Returns an ARGB int color back. Args: entityLiving, lightBrightness,
	 * partialTickTime
	 */
	@Override
	protected int getColorMultiplier(EntityLiving par1EntityLiving, float par2, float par3) {

		return updateFooRooColorMultiplier((FooRoo) par1EntityLiving, par2, par3);
	}

}
