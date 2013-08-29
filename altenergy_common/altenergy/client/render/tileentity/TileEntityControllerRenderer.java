
package altenergy.client.render.tileentity;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import altenergy.client.model.ModelEnergyBusway;
import altenergy.content.conductors.TileEnergyBusway;
import altenergy.lib.Textures;
import cpw.mods.fml.client.FMLClientHandler;

public class TileEntityControllerRenderer extends TileEntitySpecialRenderer {

	private ModelEnergyBusway model = new ModelEnergyBusway();

	public void renderModelAt(TileEnergyBusway tileEntity, double x, double y, double z, float tick) {

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
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(Textures.MODEL_ENERGY_BUSWAY);

		model.setConnections(tileEntity.visuallyConnected);

		model.renderSides(tileEntity.getNumConnections());

		/*
		 * if (tileEntity.visuallyConnected[0]) {
		 * model.renderPart("BuswayConnectionF");
		 * }
		 * 
		 * if (tileEntity.visuallyConnected[1]) {
		 * model.renderPart("BuswayConnectionE");
		 * }
		 * 
		 * if (connectedSides[2]) {
		 * model.renderPart("BuswayConnectionA");
		 * }
		 * 
		 * if (connectedSides[3]) {
		 * model.renderPart("BuswayConnectionB");
		 * }
		 * 
		 * if (connectedSides[4]) {
		 * model.renderPart("BuswayConnectionC");
		 * }
		 * 
		 * if (connectedSides[5]) {
		 * model.renderPart("BuswayConnectionD");
		 * }
		 * 
		 * model.renderPart("BuswayNone");
		 */

		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float tick) {

		renderModelAt((TileEnergyBusway) tileEntity, x, y, z, tick);
	}
}