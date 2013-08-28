package altenergy.client.gui;

import org.lwjgl.opengl.GL11;

import altenergy.content.controllers.TileControllerPC;
import altenergy.core.misc.PowerCoreState;
import altenergy.lib.Textures;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GuiControllerPC extends GuiScreen{

private TileControllerPC tileEntity;
	
	private int containerWidth;
	private int contrainerHeight;
	private EntityPlayer player;
	private boolean isActive = false;
	private int xSize;
	private int ySize;
	
	
	public GuiControllerPC(EntityPlayer player, World world, int x, int y, int z) {
		System.out.println("Gui open");
		this.tileEntity = (TileControllerPC) world.getBlockTileEntity(x, y, z);
		this.player = player;
		this.xSize = 146;
		this.ySize = 67;
	}
	
	@Override
    public void drawScreen(int x, int y, float f) {
		drawGuiContainerBackgroundLayer();
		drawGuiContainerForegroundLayer();
	}
	
	protected void drawGuiContainerForegroundLayer() {

		this.fontRenderer.drawString("Power Core Controller", 44, 8, 4210752);
		
		String stateText = "";
		String displayText = "";

		if(this.tileEntity.getStatus() == PowerCoreState.Idle) {
			stateText = "Idle";
		} else if(this.tileEntity.getStatus() == PowerCoreState.Generate) {
			stateText = "Generating";
		} else if(this.tileEntity.getStatus() == PowerCoreState.Overload) {
			stateText = "Warning: Overload";
		} else if(this.tileEntity.getStatus() == PowerCoreState.Vent) {
			stateText = "Warning: Venting";
		}
		displayText = "100000.0Watt";//ElectricityDisplay.getDisplay(tileEntity.generateWatts, ElectricUnit.WATT);
		this.fontRenderer.drawString("Status: " + stateText, (int) (33), 24, 4210752);
		this.fontRenderer.drawString("Output: " + displayText, (int) (30 - displayText.length() * 1.25), 44, 4210752);
	}

	protected void drawGuiContainerBackgroundLayer() {

		this.mc.renderEngine.bindTexture(Textures.GUI_MINI_POWERCORE);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		
		containerWidth = (this.width - this.xSize) / 2;
		contrainerHeight = (this.height - this.ySize) / 2;
		
		this.drawTexturedModalRect(containerWidth, contrainerHeight, 0, 0, this.xSize, this.ySize);
		if(this.isActive) {
			this.drawTexturedModalRect(containerWidth + 133, contrainerHeight + 18, 171, 25, 9, 7);
			player.closeScreen();
		} else {
			this.drawTexturedModalRect(containerWidth + 133, contrainerHeight + 18, 171, 16, 9, 7);
		}
	}
	
	private boolean toggleButton() {
		return !isActive;
	}
	
	@Override
	protected void mouseClicked(int x, int y, int buttonID) {
		super.mouseClicked(x,y,buttonID);
		int xAxis= (x - (width - xSize) / 2);
		int yAxis= (y - (height - ySize) / 2);
		
		if(xAxis > 133 && xAxis < 141 && yAxis > 18 && yAxis < 24) {
			toggleButton();
		}
		
	}

}
