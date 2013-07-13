
package altenergy.network;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import altenergy.lib.Reference;
import altenergy.network.packet.PacketAE;
import altenergy.network.packet.PacketItemUpdate;
import altenergy.network.packet.PacketKeyPressed;
import altenergy.network.packet.PacketRequestEvent;
import altenergy.network.packet.PacketSoundEvent;
import altenergy.network.packet.PacketSpawnParticle;
import altenergy.network.packet.PacketTileUpdate;
import altenergy.network.packet.PacketTileWithItemUpdate;

public enum PacketTypeHandler {
	KEY(PacketKeyPressed.class),
	TILE(PacketTileUpdate.class),
	REQUEST_EVENT(PacketRequestEvent.class),
	SPAWN_PARTICLE(PacketSpawnParticle.class),
	SOUND_EVENT(PacketSoundEvent.class),
	ITEM_UPDATE(PacketItemUpdate.class),
	TILE_WITH_ITEM(PacketTileWithItemUpdate.class);

	private Class<? extends PacketAE> clazz;

	PacketTypeHandler(Class<? extends PacketAE> clazz) {

		this.clazz = clazz;
	}

	public static PacketAE buildPacket(byte[] data) {

		ByteArrayInputStream bis = new ByteArrayInputStream(data);
		int selector = bis.read();
		DataInputStream dis = new DataInputStream(bis);

		PacketAE packet = null;

		try {
			packet = values()[selector].clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}

		packet.readPopulate(dis);

		return packet;
	}

	public static PacketAE buildPacket(PacketTypeHandler type) {

		PacketAE packet = null;

		try {
			packet = values()[type.ordinal()].clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}

		return packet;
	}

	public static Packet populatePacket(PacketAE packetAE) {

		byte[] data = packetAE.pupulate();

		Packet250CustomPayload packet250 = new Packet250CustomPayload();
		packet250.channel = Reference.CHANNEL_NAME;
		packet250.data = data;
		packet250.length = data.length;
		packet250.isChunkDataPacket = packetAE.isChunkDataPacket;

		return packet250;
	}
}
