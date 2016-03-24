package fr.cobnet.api.packets.out;

import fr.cobnet.api.packets.OutPacketType;
import fr.cobnet.api.packets.SentPacket;
import net.minecraft.server.v1_8_R3.Packet;
import org.bukkit.entity.Player;

/**
 * Créé par Creart le 15/02/2016.
 */
public class PacketPlayOutPlayerListHeaderFooter extends SentPacket {

    public PacketPlayOutPlayerListHeaderFooter(Packet<?> packet, Player player) {
        super(packet, player);
    }

    @Override
    public OutPacketType getType() {
        return OutPacketType.PLAYER_LIST_HEADER_FOOTER;
    }

}