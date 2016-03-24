package fr.cobnet.api.packets.in;

import fr.cobnet.api.packets.InPacketType;
import fr.cobnet.api.packets.ReceivedPacket;
import net.minecraft.server.v1_8_R3.Packet;
import org.bukkit.entity.Player;

/**
 * Créé par Creart le 26/01/2016.
 */
public class PacketPlayInCloseWindow extends ReceivedPacket {

    public PacketPlayInCloseWindow(Packet<?> packet, Player player) {
        super(packet, player);
    }

    public int getId() {
        return reflection().get("id");
    }

    @Override
    public InPacketType getType() {
        return InPacketType.CLOSE_WINDOW;
    }

}
