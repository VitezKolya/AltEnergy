
package altenergy.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import altenergy.content.controllers.TileControllerPC;
import altenergy.core.misc.ContainerEmpty;
import altenergy.core.misc.PowerCoreState;
import altenergy.lib.Textures;

public class GuiControllerPC extends GuiContainer {

	private TileControllerPC tileEntity;

	private int containerWidth;
	private int contrainerHeight;
	private EntityPlayer player;
	private boolean isExitActive = false;

	public GuiControllerPC(EntityPlayer player, World world, int x, int y, int z) {

		super(new ContainerEmpty());
		System.out.println("Gui open");
		tileEntity = (TileControllerPC) world.getBlockTileEntity(x, y, z);
		this.player = player;
		xSize = 165;
		ySize = 67;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {

		fontRenderer.drawString("Power Core Controller", 44, 8, 4210752);

		String stateText = "";
		// String displayText = "";

		if (tileEntity.getStatus() == PowerCoreState.Idle) {
			stateText = "Idle";
		} else if (tileEntity.getStatus() == PowerCoreState.Generate) {
			stateText = "Generating";
		} else if (tileEntity.getStatus() == PowerCoreState.Overload) {
			stateText = "Warning: Overload";
		} else if (tileEntity.getStatus() == PowerCoreState.Vent) {
			stateText = "Warning: Venting";
		}
		// displayText =
		// "100000.0Watt";//ElectricityDisplay.getDisplay(tileEntity.generateWatts,
		// ElectricUnit.WATT);
		fontRenderer.drawString((int) (tileEntity.getOutputLevel() * 100) + "%", 24, 22, 4210752);
		fontRenderer.drawString("Status: " + stateText, 57, 22, 4210752);
		fontRenderer.drawString("Vent", 57, 40, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {

		mc.renderEngine.bindTexture(Textures.GUI_CONTROLLER_PC);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		int scrollTabHight = (int) (42 / 2 * tileEntity.getOutputLevel());

		containerWidth = (width - xSize) / 2;
		contrainerHeight = (height - ySize) / 2;

		drawTexturedModalRect(containerWidth, contrainerHeight, 0, 0, xSize, ySize);
		if (isExitActive) {
			System.out.println("Exiting");
			drawTexturedModalRect(containerWidth + 152, contrainerHeight + 18, 166, 7, 9, 7);
			player.closeScreen();
		} else {
			drawTexturedModalRect(containerWidth + 152, contrainerHeight + 18, 166, 0, 9, 7);
		}

		drawTexturedModalRect(containerWidth + 10, contrainerHeight + 20 + scrollTabHight, 177, 0, 7, 4);
	}

	private boolean toggleExitButton() {

		isExitActive = !isExitActive;
		return isExitActive;
	}

	@Override
	protected void mouseClicked(int x, int y, int buttonID) {

		super.mouseClicked(x, y, buttonID);

		int xAxis = x - (width - xSize) / 2;
		int yAxis = y - (height - ySize) / 2;
		float setLevel = 0;

		if (xAxis > 152 && xAxis < 160 && yAxis > 18 && yAxis < 24) {
			System.out.println("Exit Clicked");
			toggleExitButton();
		}

		if (xAxis > 57 && xAxis < 74 && yAxis > 40 && yAxis < 50) {
			if (tileEntity.isLinked()) {

			}
		}

		if (tileEntity.isLinked()) {
			if (xAxis > 10 && xAxis < 17 && yAxis > 20 && yAxis < 64) {
				setLevel = (float) (yAxis - 20) / (float) (42 / 2);
				tileEntity.setOutputLevel(setLevel);
			}
		}

	}

}
