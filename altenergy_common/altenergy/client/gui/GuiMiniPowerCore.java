package altenergy.client.gui;

import org.lwjgl.opengl.GL11;

import universalelectricity.core.electricity.ElectricityDisplay;
import universalelectricity.core.electricity.ElectricityDisplay.ElectricUnit;
import altenergy.content.minipowercore.ContainerMiniPowerCore;
import altenergy.content.minipowercore.TileMiniPowerCore;
import altenergy.core.misc.PowerCoreState;
import altenergy.lib.Textures;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GuiMiniPowerCore extends GuiContainer{

	private TileMiniPowerCore tileEntity;
	
	private int containerWidth;
	private int contrainerHeight;
	private EntityPlayer player;
	private boolean isActive = false;
	
	public GuiMiniPowerCore(EntityPlayer player, World world, int x, int y, int z) {
		
		super(new ContainerMiniPowerCore(player.inventory, world, x, y, z));
		this.tileEntity = (TileMiniPowerCore) world.getBlockTileEntity(x, y, z);
		this.player = player;
		this.xSize = 170;
		this.ySize = 160;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {

		this.fontRenderer.drawString("Mini-Power Core", 44, 8, 4210752);
		
		String stateText = "";
		String displayText = "";
		
		if(this.tileEntity.isDisabled()) {
			stateText = "Disabled";
		} else if(!this.tileEntity.isLinked) {
			stateText = "Unlinked";
			displayText = "Needs controller";
		} else if(this.tileEntity.state == PowerCoreState.Idle) {
			stateText = "Idle";
			displayText = "Output: " + ElectricityDisplay.getDisplay(tileEntity.generateWatts, ElectricUnit.WATT);
		} else if(this.tileEntity.state == PowerCoreState.Generate) {
			stateText = "Generating";
			displayText = "Output: " + ElectricityDisplay.getDisplay(tileEntity.generateWatts, ElectricUnit.WATT);
		} else if(this.tileEntity.state == PowerCoreState.Overload) {
			stateText = "Warning: Overload";
			displayText = "Output: " + ElectricityDisplay.getDisplay(tileEntity.generateWatts, ElectricUnit.WATT);
		} else if(this.tileEntity.state == PowerCoreState.Vent) {
			stateText = "Venting";
			displayText = "Warning: Stay back";
		}
		
		this.fontRenderer.drawString("Status: " + stateText, (int) (33), 24, 4210752);
		this.fontRenderer.drawString(displayText, (int) 15, 44, 4210752);
		this.fontRenderer.drawString("Voltage: " + (int) this.tileEntity.getVoltage(), 15, 56, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {

		this.mc.renderEngine.bindTexture(Textures.GUI_MINI_POWERCORE);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		
		containerWidth = (this.width - this.xSize) / 2;
		contrainerHeight = (this.height - this.ySize) / 2;
		
		this.drawTexturedModalRect(containerWidth, contrainerHeight, 0, 0, this.xSize, this.ySize);
		
		if(this.tileEntity.getOverloadheat() >= 0) {
			int scale = (int) (this.tileEntity.getOverloadheat() * 0.044);
			this.drawTexturedModalRect(containerWidth + 9, contrainerHeight + 20, 180, 16, 2, 45 - scale);
		}
		
		if(this.isActive) {
			this.drawTexturedModalRect(containerWidth + 121, contrainerHeight + 22, 171, 25, 9, 7);
			player.closeScreen();
		} else {
			this.drawTexturedModalRect(containerWidth + 121, contrainerHeight + 22, 171, 16, 9, 7);
		}
	}
	
	private boolean toggleButton() {
		isActive = !isActive;
		return isActive;
	}
	
	@Override
	protected void mouseClicked(int x, int y, int buttonID) {
		super.mouseClicked(x,y,buttonID);
		int xAxis= (x - (width - xSize) / 2);
		int yAxis= (y - (height - ySize) / 2);
		
		if(xAxis > 121 && xAxis < 129 && yAxis > 22 && yAxis < 28) {
			toggleButton();
			mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
		}
		
	}

}
