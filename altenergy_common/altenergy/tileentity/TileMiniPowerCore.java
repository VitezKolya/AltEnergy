
package altenergy.tileentity;

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
import universalelectricity.prefab.tile.TileEntityElectrical;
import altenergy.items.ModItems;
import altenergy.lib.Reference;
import altenergy.lib.Strings;

import com.google.common.io.ByteArrayDataInput;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public class TileMiniPowerCore extends TileEntityElectrical implements IInventory, ISidedInventory, IPacketReceiver {

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

		if (!worldObj.isRemote) {
			prevGenerateWatts = generateWatts;

			ForgeDirection outputDirection = ForgeDirection.getOrientation(this.getBlockMetadata());
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

			if (!this.isDisabled()) {

				if (connectedElectricUnit != null) {

					if (state == PowerCoreState.Generate) {
						// Generate power normally
						// if crystal is damaged then reduce the output

					}

					if (state == PowerCoreState.Overload) {
						// Generate more power but damages the crystal
						// Builds up heat

					}

					if (state == PowerCoreState.Vent) {
						// Vents overloadHeat
						// prevents generator from exploding

					}
				}

				if (connectedElectricUnit != null) {
					if (generateWatts > MIN_GENERATE_WATTS) {
						connectedElectricUnit.getNetwork().startProducing(this,
								generateWatts / this.getVoltage() / Reference.SECOND_IN_TICKS, this.getVoltage());
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

	@Override
	public Packet getDescriptionPacket() {

		return PacketManager.getPacket(Reference.CHANNEL_NAME, this, generateWatts, overloadHeat, disabledTicks);
	}

	@Override
	public void handlePacketData(INetworkManager network, int packetType, Packet250CustomPayload packet,
			EntityPlayer player, ByteArrayDataInput dataStream) {

		try {
			if (worldObj.isRemote) {
				generateWatts = dataStream.readDouble();
				overloadHeat = dataStream.readInt();
				disabledTicks = dataStream.readInt();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {

		super.readFromNBT(nbtTagCompound);

		generateWatts = nbtTagCompound.getInteger("generateRate");
		overloadHeat = nbtTagCompound.getInteger("overloadHeat");
		NBTTagList items = nbtTagCompound.getTagList("Items");
		containingItems = new ItemStack[this.getSizeInventory()];

		for (int index = 0; index < items.tagCount(); ++index) {
			NBTTagCompound var1 = (NBTTagCompound) items.tagAt(index);
			byte var2 = var1.getByte("Slot");

			if (var2 >= 0 && var2 < containingItems.length) {
				containingItems[var2] = ItemStack.loadItemStackFromNBT(var1);
			}
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound) {

		super.writeToNBT(nbtTagCompound);

		nbtTagCompound.setDouble("generateRate", generateWatts);
		nbtTagCompound.setInteger("overloadHeat", overloadHeat);
		NBTTagList items = new NBTTagList();

		for (int index = 0; index < containingItems.length; ++index) {
			if (containingItems[index] != null) {
				NBTTagCompound var1 = new NBTTagCompound();
				var1.setByte("Slot", (byte) index);
				containingItems[index].writeToNBT(var1);
				items.appendTag(var1);
			}
		}

		nbtTagCompound.setTag("Items", items);
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int var1) {

		return new int[] {
			0
		};
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

		if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit()) {
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

		return itemstack.itemID == ModItems.gemPowerCrystal.itemID;
	}

}
