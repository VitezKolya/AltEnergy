
package altenergy.core.proxy;

import altenergy.client.gui.GuiMachine;
import altenergy.client.gui.GuiMiniPowerCore;
import altenergy.client.gui.GuiControllerPC;
import altenergy.content.minipowercore.ContainerMiniPowerCore;
import altenergy.content.minipowercore.TileMiniPowerCore;
import altenergy.core.misc.ContrainerMachine;
import altenergy.lib.GuiIds;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import cpw.mods.fml.common.network.IGuiHandler;

public class CommonProxy implements IGuiHandler{

	
	public void registerSoundHandler() {
		
	}
	
	public void initRenderingAndTextures() {
		
	}
	
	public void registerTileEntities() {
		
	}
	
	public void sendRequestEventPacket(byte eventType, int originX, int originY, int originZ, byte sideHit, byte rangeX, byte rangeY, byte rangeZ, String data) {

    }

    public void handleTileEntityPacket(int x, int y, int z, ForgeDirection orientation, byte state, String customName) {

    }

    public void handleTileWithItemPacket(int x, int y, int z, ForgeDirection orientation, byte state, String customName, int itemID, int metaData, int stackSize, int color) {

    }
    
    public void bindTexture(String texture) {
	}
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

		if(ID == GuiIds.MINI_POWERCORE) {
				return new ContainerMiniPowerCore(player.inventory, world, x, y, z);
		} else if(ID == GuiIds.MACHINE) {
				return new ContrainerMachine(player.inventory, world, z, z, z);
		}
		
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		System.out.println("ID: " + ID);
		if(ID == GuiIds.MINI_POWERCORE) {
				return new GuiMiniPowerCore(player, world, x, y, z);
		} else if(ID == GuiIds.CONTROLLER_PC) {
				System.out.println("Opening Controller Gui");
				return new GuiControllerPC(player, world, x, y, z);
		} else if(ID == GuiIds.MACHINE) {
				return new GuiMachine(player, world, x, y, z);
		}
		
		return null;
	}

}
