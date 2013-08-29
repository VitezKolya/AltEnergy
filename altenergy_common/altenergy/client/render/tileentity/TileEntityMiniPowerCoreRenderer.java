
package altenergy.client.render.tileentity;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import altenergy.client.model.ModelMiniPowerCore;
import altenergy.content.minipowercore.TileMiniPowerCore;
import altenergy.lib.ItemIds;
import altenergy.lib.Textures;
import cpw.mods.fml.client.FMLClientHandler;

public class TileEntityMiniPowerCoreRenderer extends TileEntitySpecialRenderer {

	private ModelMiniPowerCore model = new ModelMiniPowerCore();

	public void renderModelAt(TileMiniPowerCore tileEntity, double x, double y, double z, float tick) {

		float scale = 1;

		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);

		// Scale, Translate, Rotate
		GL11.glScalef(scale, scale, scale);
		GL11.glTranslatef((float) x + 0F, (float) y, (float) z + 1F);
		// angle, xaxis, yaxis, zaxis
		GL11.glRotatef(0F, 0F, 0F, 0F);

		// Bind Texture
		// bindTextureByName(Textures.MODEL_BUSWAY);
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(Textures.MODEL_MINI_POWERCORE);

		model.renderPart("mini_powercore");

		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();

		renderCrystal(tileEntity, x, y, z, tick);
	}

	public void renderCrystal(TileMiniPowerCore tileEntity, double x, double y, double z, float tick) {

		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);
		float scale = 1;
		// Scale, Translate, Rotate
		GL11.glScalef(scale, scale, scale);
		GL11.glTranslatef((float) x + 0F, (float) y, (float) z + 1F);
		// angle, xaxis, yaxis, zaxis
		GL11.glRotatef(0F, 0F, 0F, 0F);

		FMLClientHandler.instance().getClient().renderEngine.bindTexture(Textures.MODEL_ENERGY_CRYSTAL);

		if (tileEntity.getItemId() == ItemIds.ENERGY_CRYSTAL_TINY) {
			model.renderPart("tinyEnergyCrystal");
		} else if (tileEntity.getItemId() == ItemIds.ENERGY_CRYSTAL_SMALL) {
			model.renderPart("smallEnergyCrystal");
		}

		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float tick) {

		renderModelAt((TileMiniPowerCore) tileEntity, x, y, z, tick);
	}
}