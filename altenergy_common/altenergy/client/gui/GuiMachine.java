
package altenergy.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import altenergy.core.misc.ContrainerMachine;

public class GuiMachine extends GuiContainer {

	public GuiMachine(EntityPlayer player, World world, int x, int y, int z) {

		super(new ContrainerMachine(player.inventory, world, x, y, z));
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {

	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {

		// TODO Auto-generated method stub

	}

}
