package fr.cobnet.api.packets.in;

import fr.cobnet.api.packets.InPacketType;
import fr.cobnet.api.packets.ReceivedPacket;
import net.minecraft.server.v1_8_R3.Packet;
import org.bukkit.entity.Player;

/**
 * Créé par Creart le 26/01/2016.
 */
public class PacketPlayInPosition extends ReceivedPacket {

    public PacketPlayInPosition(Packet<?> packet, Player player) {
        super(packet, player);
    }

    @Override
    public InPacketType getType() {
        return InPacketType.POSITION;
    }

    public double getX() {
        return reflection().get("x");
    }

    public double getY() {
        return reflection().get("y");
    }

    public double getZ() {
        return reflection().get("z");
    }

}
