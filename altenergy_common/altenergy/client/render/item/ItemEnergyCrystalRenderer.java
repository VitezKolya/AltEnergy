
package altenergy.client.render.item;

import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import altenergy.lib.ItemIds;
import altenergy.lib.Textures;
import cpw.mods.fml.client.FMLClientHandler;

public class ItemEnergyCrystalRenderer implements IItemRenderer {

	private ModelEnergyCrystals model;

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
				renderModel(item, -0.5F, 0.0F, 0.5F, 1.0F);
				return;
			}
			case EQUIPPED: {
				renderModel(item, -0.3F, -0.35F, 0.6F, 3.0F);
				return;
			}
			case EQUIPPED_FIRST_PERSON: {
				renderModel(item, -0.2F, -0.4F, 0.5F, 2.0F);
				return;
			}
			case INVENTORY: {
				renderModel(item, 0.0F, 0F, 1.0F, 1.0F);
				return;
			}
			default:
				return;
		}

	}

	public void renderModel(ItemStack item, float x, float y, float z, float scale) {

		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);

		// Scale, Translate, Rotate
		GL11.glScalef(scale, scale, scale);
		GL11.glTranslatef(x, y, z);
		GL11.glRotatef(0F, 1F, 0, 0);

		// Bind texture
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(Textures.MODEL_ENERGY_CRYSTAL);

		// Render
		if (item.itemID == ItemIds.ENERGY_CRYSTAL_TINY) {
			model.renderPart("tinyEnergyCrystal");
		} else if (item.itemID == ItemIds.ENERGY_CRYSTAL_SMALL) {
			model.renderPart("smallEnergyCrystal");
		} else if (item.itemID == ItemIds.ENERGY_CRYSTAL_MEDIUM) {
			model.renderPart("mediumEnergyCrystal");
		} else if (item.itemID == ItemIds.ENERGY_CRYSTAL_LARGE) {
			model.renderPart("largeEnergyCrystal");
		} else if (item.itemID == ItemIds.ENERGY_CRYSTAL_HUGE) {
			model.renderPart("hugeEnergyCrystal");
		} else if (item.itemID == ItemIds.ENERGY_CRYSTAL_GIGANTIC) {
			model.renderPart("giganticEnergyCrystal");
		}

		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}

}
