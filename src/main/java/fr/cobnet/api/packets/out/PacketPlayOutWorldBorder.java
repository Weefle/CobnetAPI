package fr.cobnet.api.packets.out;

import fr.cobnet.api.packets.OutPacketType;
import fr.cobnet.api.packets.SentPacket;
import net.minecraft.server.v1_12_R1.Packet;
import org.bukkit.entity.Player;

/**
 * Créé par Creart le 15/02/2016.
 */
public class PacketPlayOutWorldBorder extends SentPacket {

    public PacketPlayOutWorldBorder(Packet<?> packet, Player player) {
        super(packet, player);
    }

    @Override
    public OutPacketType getType() {
        return OutPacketType.WORLD_BORDER;
    }

}
