
package altenergy.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import altenergy.items.IKeyBound;
import altenergy.network.PacketTypeHandler;
import cpw.mods.fml.common.network.Player;

public class PacketKeyPressed extends PacketAE {

	public String key;

	public PacketKeyPressed() {

		super(PacketTypeHandler.KEY, false);
	}

	public PacketKeyPressed(String key) {

		super(PacketTypeHandler.KEY, false);
		this.key = key;
	}

	@Override
	public void writeData(DataOutputStream data) throws IOException {

		data.writeUTF(key);
	}

	@Override
	public void readData(DataInputStream data) throws IOException {

		key = data.readUTF();
	}

	public void excute(INetworkManager manager, Player player) {

		EntityPlayer thePlayer = (EntityPlayer) player;

		if (thePlayer.getCurrentEquippedItem() != null
				&& thePlayer.getCurrentEquippedItem().getItem() instanceof IKeyBound) {
			((IKeyBound) thePlayer.getCurrentEquippedItem().getItem()).doKeyBindingAction(thePlayer,
					thePlayer.getCurrentEquippedItem(), key);
			;
		}
	}
}
