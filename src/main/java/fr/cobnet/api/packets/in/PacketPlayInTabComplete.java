package fr.cobnet.api.packets.in;

import fr.cobnet.api.packets.InPacketType;
import fr.cobnet.api.packets.ReceivedPacket;
import net.minecraft.server.v1_12_R1.Packet;
import org.bukkit.entity.Player;

/**
 * Créé par Creart le 26/01/2016.
 */
public class PacketPlayInTabComplete extends ReceivedPacket {

    public PacketPlayInTabComplete(Packet<?> packet, Player player) {
        super(packet, player);
    }

    public String getMessage() {
        return reflection().get("a");
    }

    @Override
    public InPacketType getType() {
        return InPacketType.TAB_COMPLETE;
    }

}
