package fr.cobnet.api.packets.in;

import fr.cobnet.api.packets.InPacketType;
import fr.cobnet.api.packets.ReceivedPacket;
import net.minecraft.server.v1_12_R1.Packet;
import org.bukkit.entity.Player;

/**
 * Créé par Creart le 26/01/2016.
 */
public class PacketPlayInChat extends ReceivedPacket {

    public PacketPlayInChat(Packet<?> packet, Player player) {
        super(packet, player);
    }

    public String getMessage() {
        return reflection().get("a");
    }

    public void setMessage(String newValue) {
        if (newValue != null && newValue.length() > 0)
            reflection().set("a", newValue);
    }

    @Override
    public InPacketType getType() {
        return InPacketType.CHAT;
    }

}
