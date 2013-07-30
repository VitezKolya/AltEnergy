package altenergy.tileentity;

import java.util.HashSet;
import java.util.Set;

import altenergy.items.ModItems;
import altenergy.lib.Reference;
import altenergy.lib.Strings;

import com.google.common.io.ByteArrayDataInput;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import universalelectricity.core.block.IConductor;
import universalelectricity.core.electricity.ElectricityNetworkHelper;
import universalelectricity.core.electricity.IElectricityNetwork;
import universalelectricity.core.vector.Vector3;
import universalelectricity.core.vector.VectorHelper;
import universalelectricity.prefab.network.IPacketReceiver;
import universalelectricity.prefab.network.PacketManager;
import universalelectricity.prefab.tile.TileEntityElectrical;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public class TileMiniPowerCore extends TileEntityElectrical implements IInventory, ISidedInventory, IPacketReceiver{

	/**
	 * Maximum amount of energy needed to generate electricity
	 */
	public static final int MAX_GENERATE_WATTS = 50000;
	
	public static final int MIN_GENERATE_WATTS = 500;
	
	public static final int MAX_OVERLOAD = 1000;
	
	private static final int VOLTAGE = 960;
	
	private int overloadHeat;
	
	/**
	 * Per second
	 */
	public double prevGenerateWatts, generateWatts = 0;
	
	public IConductor connectedElectricUnit = null;
	
	/**
	 * State of generator
	 */
	PowerCoreState state = PowerCoreState.Idle;
	
	/**
	 * The ItemStacks that hold the items currently being used in the generator
	 */
	private ItemStack[] containingItems = new ItemStack[1];
	
	public final Set<EntityPlayer> playerUsing = new HashSet<EntityPlayer>();
	
	@Override
	public boolean canConnect(ForgeDirection direction) {

		return direction == ForgeDirection.getOrientation(this.getBlockMetadata());
	}
	
	@Override
	public double getVoltage() {
		return VOLTAGE;
	}
	
	@Override
	public void updateEntity() {
		super.updateEntity();
		
		if(!this.worldObj.isRemote) {
			this.prevGenerateWatts = this.generateWatts;
			
			ForgeDirection outputDirection = ForgeDirection.getOrientation(this.getBlockMetadata());
			TileEntity outputTile = VectorHelper.getConnectorFromSide(this.worldObj,  new Vector3(this.xCoord,this.yCoord,this.zCoord), outputDirection);
			
			IElectricityNetwork network = ElectricityNetworkHelper.getNetworkFromTileEntity(outputTile, outputDirection);
			
			if(network != null) {
				if(network.getRequest().getWatts() > 0) {
					this.connectedElectricUnit = (IConductor) outputTile;
				} else {
					this.connectedElectricUnit = null;
				}
			} else {
				this.connectedElectricUnit = null;
			}
			
			if(!this.isDisabled()) {
				
				if(this.connectedElectricUnit != null) {
					
					if(state == PowerCoreState.Generate) {
						// Generate power normally
						// if crystal is damaged then reduce the output
						
					}
					
					if(state == PowerCoreState.Overload) {
						// Generate more power but damages the crystal
						// Builds up heat
						
					}
					
					if(state == PowerCoreState.Vent) {
						// Vents overloadHeat
						// prevents generator from exploding
						
					}
				}
					
				if(this.connectedElectricUnit != null) {
					if(this.generateWatts > MIN_GENERATE_WATTS) {
						this.connectedElectricUnit.getNetwork().startProducing(this, (this.generateWatts / this.getVoltage()) / Reference.SECOND_IN_TICKS, this.getVoltage());
					} else {
						this.connectedElectricUnit.getNetwork().stopProducing(this);
					}
				}
			}
		
			if(this.ticks % 3 == 0) {
				for(EntityPlayer player: this.playerUsing) {
					PacketDispatcher.sendPacketToPlayer(getDescriptionPacket(), (Player) player);
				}
			}
			
			if(this.prevGenerateWatts <= 0 && this.generateWatts > 0 || this.prevGenerateWatts > 0 && this.generateWatts <= 0) {
				PacketManager.sendPacketToClients(getDescriptionPacket(), this.worldObj);
			}
		}
	}
	
	@Override
	public Packet getDescriptionPacket() {
		return PacketManager.getPacket(Reference.CHANNEL_NAME, this, this.generateWatts, this.overloadHeat, this.disabledTicks);
	}

	@Override
	public void handlePacketData(INetworkManager network, int packetType, Packet250CustomPayload packet,
			EntityPlayer player, ByteArrayDataInput dataStream) {

		try {
			if(this.worldObj.isRemote) {
				this.generateWatts = dataStream.readDouble();
				this.overloadHeat = dataStream.readInt();
				this.disabledTicks = dataStream.readInt();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {
		super.readFromNBT(nbtTagCompound);
		
		this.generateWatts = nbtTagCompound.getInteger("generateRate");
		this.overloadHeat = nbtTagCompound.getInteger("overloadHeat");
		NBTTagList items = nbtTagCompound.getTagList("Items");
		this.containingItems = new ItemStack[this.getSizeInventory()];
		
		for (int index = 0; index < items.tagCount(); ++index) {
			NBTTagCompound var1 = (NBTTagCompound) items.tagAt(index);
			byte var2 = var1.getByte("Slot");
			
			if(var2 >= 0 && var2 < this.containingItems.length) {
				this.containingItems[var2] = ItemStack.loadItemStackFromNBT(var1);
			}
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound) {
		super.writeToNBT(nbtTagCompound);
		
		nbtTagCompound.setDouble("generateRate", this.generateWatts);
		nbtTagCompound.setInteger("overloadHeat", this.overloadHeat);
		NBTTagList items = new NBTTagList();
		
		for(int index = 0; index < this.containingItems.length; ++ index)  {
			if(this.containingItems[index] != null) {
				NBTTagCompound var1 = new NBTTagCompound();
				var1.setByte("Slot", (byte) index);
				this.containingItems[index].writeToNBT(var1);
				items.appendTag(var1);
			}
		}
		
		nbtTagCompound.setTag("Items", items);
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int var1) {

		return new int[] {0};
	}

	@Override
	public boolean canInsertItem(int slotID, ItemStack itemstack, int j) {

		return this.isStackValidForSlot(slotID, itemstack);
	}

	@Override
	public boolean canExtractItem(int slotID, ItemStack itemstack, int j) {

		return slotID == 0;
	}

	@Override
	public int getSizeInventory() {

		return this.containingItems.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {

		return this.containingItems[i];
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {

		ItemStack var3;
		
		if(this.containingItems[i] != null) {
			
			if(this.containingItems[i].stackSize <= j) {
				var3 = this.containingItems[i];
				this.containingItems[i] = null;
				return var3;
			} else {
				var3 = this.containingItems[i].splitStack(j);
				
				if(this.containingItems[i].stackSize == 0) {
					this.containingItems[i] = null;
				}
				
				return var3;
			}
		} else {
			
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {

		if(this.containingItems[i] != null) {
			ItemStack var2 = this.containingItems[i];
			this.containingItems[i] = null;
					return var2;
		} else {
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {

		this.containingItems[i] = itemstack;
		
		if(itemstack != null && itemstack.stackSize > this.getInventoryStackLimit()) {
			itemstack.stackSize = this.getInventoryStackLimit();
		}
		
	}

	@Override
	public String getInvName() {

		return Strings.TILE_MINI_POWER_CORE;
	}

	@Override
	public boolean isInvNameLocalized() {

		return true;
	}

	@Override
	public int getInventoryStackLimit() {

		return 1;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityPlayer) {

		return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : entityPlayer.getDistanceSq(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D) <= 64.0D;
	}

	@Override
	public void openChest() {
		
	}

	@Override
	public void closeChest() {

	}

	@Override
	public boolean isStackValidForSlot(int i, ItemStack itemstack) {

		return itemstack.itemID == ModItems.gemPowerCrystal.itemID;
	}

}
