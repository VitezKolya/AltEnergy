
package altenergy.network;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import altenergy.network.packet.PacketAE;
import cpw.mods.fml.common.network.Player;

public class PacketHandler {

	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {

		PacketAE packetAE = PacketTypeHandler.buildPacket(packet.data);

		packetAE.execute(manager, player);
	}
}
