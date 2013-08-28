
package altenergy.content.minipowercore;

import java.util.HashSet;
import java.util.Set;

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
import universalelectricity.core.block.IConductor;
import universalelectricity.core.electricity.ElectricityNetworkHelper;
import universalelectricity.core.electricity.IElectricityNetwork;
import universalelectricity.core.vector.Vector3;
import universalelectricity.core.vector.VectorHelper;
import universalelectricity.prefab.network.IPacketReceiver;
import universalelectricity.prefab.network.PacketManager;
import altenergy.core.misc.PowerCoreState;
import altenergy.core.misc.TilePowerCoreBase;
import altenergy.lib.ItemIds;
import altenergy.lib.Reference;
import altenergy.lib.Strings;

import com.google.common.io.ByteArrayDataInput;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public class TileMiniPowerCore extends TilePowerCoreBase implements IInventory, ISidedInventory, IPacketReceiver {

	public int overloadHeat;
	private long lastTicks;

	/**
	 * Per second
	 */
	public double prevGenerateWatts, generateWatts = 0;

	public IConductor connectedElectricUnit = null;

	/**
	 * The ItemStacks that hold the items currently being used in the generator
	 */
	private ItemStack[] containingItems = new ItemStack[1];

	public final Set<EntityPlayer> playerUsing = new HashSet<EntityPlayer>();

	public TileMiniPowerCore() {
		
		// Overload when generated watts are above 50kw
		MAX_GENERATE_WATTS = 100000;
		MIN_GENERATE_WATTS = 500;
		MAX_OVERLOAD = 1000;
		MAX_OUTPUT_SETTING = 2.0F;
		VOLTAGE = 960;
		
		if(!isLinked) {
		state = PowerCoreState.Idle;
		} else {
			findController();
		}
		lastTicks = ticks;
	}

	@Override
	public boolean canConnect(ForgeDirection direction) {

		return direction == ForgeDirection.getOrientation(getBlockMetadata());
	}

	@Override
	public double getVoltage() {

		return VOLTAGE;
	}

	public void setOutputSetting(float setting) {

		
	}

	@Override
	public void updateEntity() {

		super.updateEntity();

		if (!worldObj.isRemote) {
			if(isLinked) {
				prevGenerateWatts = generateWatts;
	
				ForgeDirection outputDirection = ForgeDirection.getOrientation(getBlockMetadata());
				TileEntity outputTile = VectorHelper.getConnectorFromSide(worldObj, new Vector3(xCoord, yCoord, zCoord),
						outputDirection);
	
				IElectricityNetwork network = ElectricityNetworkHelper
						.getNetworkFromTileEntity(outputTile, outputDirection);
	
				if (network != null) {
					if (network.getRequest().getWatts() > 0) {
						connectedElectricUnit = (IConductor) outputTile;
					} else {
						connectedElectricUnit = null;
					}
				} else {
					connectedElectricUnit = null;
				}
	
				if (!isDisabled()) {
	
					if (connectedElectricUnit != null) {
	
						if (state == PowerCoreState.Generate) {
							// Generate power normally
							// if crystal is damaged then reduce the output
	
							// max crystal damage should be 1000 and dividing that
							// by 1000 should be 1. So that
							// when crystal gets damaged by overloading it become
							// 0.9 something.
							int crystalDamage = containingItems[0].getItemDamage() / 1000;
							// This is normal generate state so max watts should be
							// halved.
							int currentMaxWatts = MAX_GENERATE_WATTS / 2 * crystalDamage;
	
							generateWatts = currentMaxWatts * outputLevel;
						}
	
						if (state == PowerCoreState.Overload) {
							// Generate more power but damages the crystal
							// Builds up heat
	
							if (lastTicks - ticks <= Reference.SECOND_IN_TICKS && overloadHeat >= MAX_OVERLOAD * 0.50) {
								containingItems[0].setItemDamage(containingItems[0].getItemDamage() - 1
										* (MAX_OVERLOAD / 100));
							}
	
							int crystalDamage = containingItems[0].getItemDamage() / 1000;
							int currentMaxWatts = MAX_GENERATE_WATTS * crystalDamage;
							generateWatts = currentMaxWatts * outputLevel;
							overloadHeat++;
						}
	
						if (state == PowerCoreState.Vent) {
							// Vents overloadHeat
							// prevents generator from exploding
	
							if (lastTicks - ticks <= Reference.SECOND_IN_TICKS) {
								if (overloadHeat > 0) {
									overloadHeat--;
								} else {
									state = PowerCoreState.Idle;
								}
							}
	
						}
					}
	
					if (connectedElectricUnit != null) {
						if (generateWatts > MIN_GENERATE_WATTS) {
							connectedElectricUnit.getNetwork().startProducing(this,
									generateWatts / getVoltage() / Reference.SECOND_IN_TICKS, getVoltage());
						} else {
							connectedElectricUnit.getNetwork().stopProducing(this);
						}
					}
				}
	
				if (ticks % 3 == 0) {
					for (EntityPlayer player : playerUsing) {
						PacketDispatcher.sendPacketToPlayer(getDescriptionPacket(), (Player) player);
					}
				}
	
				if (prevGenerateWatts <= 0 && generateWatts > 0 || prevGenerateWatts > 0 && generateWatts <= 0) {
					PacketManager.sendPacketToClients(getDescriptionPacket(), worldObj);
				}
			}
		}

		lastTicks = ticks;
	}

	@Override
	public Packet getDescriptionPacket() {

		return PacketManager.getPacket(Reference.CHANNEL_NAME, this, generateWatts, overloadHeat, disabledTicks,
				outputLevel);
	}

	@Override
	public void handlePacketData(INetworkManager network, int packetType, Packet250CustomPayload packet,
			EntityPlayer player, ByteArrayDataInput dataStream) {

		try {
			if (worldObj.isRemote) {
				outputLevel = dataStream.readFloat();
				generateWatts = dataStream.readDouble();
				overloadHeat = dataStream.readInt();
				disabledTicks = dataStream.readInt();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {

		super.readFromNBT(nbt);

		outputLevel = nbt.getFloat("outputLevel");
		generateWatts = nbt.getDouble("generateRate");
		overloadHeat = nbt.getInteger("overloadHeat");
		linkedX = nbt.getInteger("linkedX");
		linkedY = nbt.getInteger("linkedY");
		linkedZ = nbt.getInteger("linkedZ");
		isLinked = nbt.getBoolean("isLinked");
		NBTTagList items = nbt.getTagList("Items");
		containingItems = new ItemStack[getSizeInventory()];

		for (int index = 0; index < items.tagCount(); ++index) {
			NBTTagCompound var1 = (NBTTagCompound) items.tagAt(index);
			byte var2 = var1.getByte("Slot");

			if (var2 >= 0 && var2 < containingItems.length) {
				containingItems[var2] = ItemStack.loadItemStackFromNBT(var1);
			}
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {

		super.writeToNBT(nbt);

		nbt.setFloat("outputLevel", outputLevel);
		nbt.setDouble("generateRate", generateWatts);
		nbt.setInteger("overloadHeat", overloadHeat);
		nbt.setInteger("linkedX", linkedX);
		nbt.setInteger("linkedY", linkedY);
		nbt.setInteger("linkedZ", linkedZ);
		nbt.setBoolean("isLinked", isLinked);
		NBTTagList items = new NBTTagList();

		for (int index = 0; index < containingItems.length; ++index) {
			if (containingItems[index] != null) {
				NBTTagCompound var1 = new NBTTagCompound();
				var1.setByte("Slot", (byte) index);
				containingItems[index].writeToNBT(var1);
				items.appendTag(var1);
			}
		}
		nbt.setTag("Items", items);
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int var1) {

		return new int[] {
			0
		};
	}

	@Override
	public boolean canInsertItem(int slotID, ItemStack itemstack, int j) {

		return isStackValidForSlot(slotID, itemstack);
	}

	@Override
	public boolean canExtractItem(int slotID, ItemStack itemstack, int j) {

		return slotID == 0;
	}

	@Override
	public int getSizeInventory() {

		return containingItems.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {

		return containingItems[i];
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {

		ItemStack var3;

		if (containingItems[i] != null) {

			if (containingItems[i].stackSize <= j) {
				var3 = containingItems[i];
				containingItems[i] = null;
				return var3;
			} else {
				var3 = containingItems[i].splitStack(j);

				if (containingItems[i].stackSize == 0) {
					containingItems[i] = null;
				}

				return var3;
			}
		} else {

			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {

		if (containingItems[i] != null) {
			ItemStack var2 = containingItems[i];
			containingItems[i] = null;
			return var2;
		} else {
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {

		containingItems[i] = itemstack;

		if (itemstack != null && itemstack.stackSize > getInventoryStackLimit()) {
			itemstack.stackSize = getInventoryStackLimit();
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

		return worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) != this ? false : entityPlayer.getDistanceSq(
				xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D) <= 64.0D;
	}

	@Override
	public void openChest() {

	}

	@Override
	public void closeChest() {

	}

	@Override
	public boolean isStackValidForSlot(int i, ItemStack itemstack) {

		if (itemstack.itemID == ItemIds.ENERGY_CRYSTAL_TINY) {
			return itemstack.itemID == ItemIds.ENERGY_CRYSTAL_TINY;
		}

		return itemstack.itemID == ItemIds.ENERGY_CRYSTAL_SMALL;

	}

	public int getOverloadheat() {

		return this.overloadHeat;
	}

	public PowerCoreState getStatus() {
		return this.state;
	}
}
