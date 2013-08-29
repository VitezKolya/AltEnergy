
package altenergy.client.render.item;

import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import altenergy.client.model.ModelEnergyBusway;
import altenergy.lib.Textures;
import cpw.mods.fml.client.FMLClientHandler;

public class ItemMiniPowerCoreRenderer implements IItemRenderer {

	private ModelEnergyBusway model;

	public ItemMiniPowerCoreRenderer() {

		model = new ModelEnergyBusway();
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {

		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {

		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {

		switch (type) {
			case ENTITY: {
				renderModel(-0.5F, 0.0F, 0.5F, 1.0F);
				return;
			}
			case EQUIPPED: {
				renderModel(-0.3F, -0.35F, 0.6F, 3.0F);
				return;
			}
			case EQUIPPED_FIRST_PERSON: {
				renderModel(-0.2F, -0.4F, 0.5F, 2.0F);
				return;
			}
			case INVENTORY: {
				renderModel(0.0F, 0F, 1.0F, 1.0F);
				return;
			}
			default:
				return;
		}

	}

	public void renderModel(float x, float y, float z, float scale) {

		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);

		// Scale, Translate, Rotate
		GL11.glScalef(scale, scale, scale);
		GL11.glTranslatef(x, y, z);
		GL11.glRotatef(0F, 1F, 0, 0);

		// Bind texture
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(Textures.MODEL_ENERGY_BUSWAY);

		// Render
		model.renderPart("BuswayNone");

		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}

}
