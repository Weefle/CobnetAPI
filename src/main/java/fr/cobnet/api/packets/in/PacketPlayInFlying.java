package fr.cobnet.api.packets.in;

import fr.cobnet.api.packets.InPacketType;
import fr.cobnet.api.packets.ReceivedPacket;
import net.minecraft.server.v1_8_R3.Packet;
import org.bukkit.entity.Player;

/**
 * Créé par Creart le 25/01/2016.
 */
public class PacketPlayInFlying extends ReceivedPacket {

    public PacketPlayInFlying(Packet<?> packet, Player player) {
        super(packet, player);
    }

    @Override
    public InPacketType getType() {
        return InPacketType.FLYING;
    }
}
