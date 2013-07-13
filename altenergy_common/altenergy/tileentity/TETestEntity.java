
package altenergy.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import altenergy.lib.Strings;
import altenergy.network.PacketTypeHandler;
import altenergy.network.packet.PacketTileUpdate;

public class TETestEntity extends TileEntity {

	protected ForgeDirection orientation;
	protected byte state;
	protected String customName;

	public TETestEntity() {

		orientation = ForgeDirection.SOUTH;
		state = 0;
		customName = "";
	}

	public ForgeDirection getOrientation() {

		return orientation;
	}

	public void setOrientation(ForgeDirection orientation) {

		this.orientation = orientation;
	}

	public short getState() {

		return state;
	}

	public void setState(byte state) {

		this.state = state;
	}

	public boolean hasCustomName() {

		return customName != null && customName.length() > 0;
	}

	public void setCustomName(String customName) {

		this.customName = customName;
	}

	public boolean isUseableByPlayer(EntityPlayer player) {

		return true;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {

		super.readFromNBT(nbt);

		if (nbt.hasKey(Strings.NBT_TE_DIRECTION_KEY)) {
			orientation = ForgeDirection.getOrientation(nbt.getByte(Strings.NBT_TE_DIRECTION_KEY));
		}

		if (nbt.hasKey(Strings.NBT_TE_STATE_KEY)) {
			state = nbt.getByte(Strings.NBT_TE_STATE_KEY);
		}

		if (nbt.hasKey(Strings.NBT_TE_CUSTOM_NAME)) {
			customName = nbt.getString(Strings.NBT_TE_CUSTOM_NAME);
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {

		super.writeToNBT(nbt);

		nbt.setByte(Strings.NBT_TE_DIRECTION_KEY, (byte) orientation.ordinal());
		nbt.setByte(Strings.NBT_TE_STATE_KEY, state);

		if (this.hasCustomName()) {
			nbt.setString(Strings.NBT_TE_CUSTOM_NAME, customName);
		}
	}

	@Override
	public Packet getDescriptionPacket() {

		return PacketTypeHandler.populatePacket(new PacketTileUpdate(xCoord, yCoord, zCoord, orientation, state,
				customName));
	}

	@Override
	public String toString() {

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(String.format(
				"TileAE - xCoord: %d, yCoord %d, zCoord %d, customName: '%s', orientation: %s, state: %d\n", xCoord,
				yCoord, zCoord, customName, orientation, state));

		return stringBuilder.toString();
	}
}
