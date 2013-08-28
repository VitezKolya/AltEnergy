package altenergy.content.scalertransmiter;

import com.google.common.io.ByteArrayDataInput;

import altenergy.core.misc.IWirelessPowerMachine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraftforge.common.ForgeDirection;
import universalelectricity.prefab.network.IPacketReceiver;
import universalelectricity.prefab.tile.TileEntityElectricityStorage;

public class TileScalerEnergyTransmiter extends TileEntityElectricityStorage implements IWirelessPowerMachine, IPacketReceiver, IInventory {

	@Override
	public double getMaxJoules() {

		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean canConnect(ForgeDirection direction) {

		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getSizeInventory() {

		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ItemStack getStackInSlot(int i) {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {

		// TODO Auto-generated method stub
		
	}

	@Override
	public String getInvName() {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isInvNameLocalized() {

		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getInventoryStackLimit() {

		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {

		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void openChest() {

		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeChest() {

		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isStackValidForSlot(int i, ItemStack itemstack) {

		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void handlePacketData(INetworkManager network, int packetType, Packet250CustomPayload packet,
			EntityPlayer player, ByteArrayDataInput dataStream) {

		// TODO Auto-generated method stub
		
	}

	@Override
	public byte getFrequency() {

		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setFrequency(byte newFrequency) {

		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isPrivate() {

		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getType() {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeJoules(double outputWatts) {

		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPlayer(EntityPlayer player) {

		// TODO Auto-generated method stub
		
	}

	public String getOwningPlayer() {

		// TODO Auto-generated method stub
		return null;
	}

}
