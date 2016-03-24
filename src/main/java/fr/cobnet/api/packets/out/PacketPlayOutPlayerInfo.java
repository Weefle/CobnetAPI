package fr.cobnet.api.packets.out;

import fr.cobnet.api.packets.OutPacketType;
import fr.cobnet.api.packets.SentPacket;
import java.util.List;
import net.minecraft.server.v1_8_R3.Packet;
import org.bukkit.entity.Player;

/**
 * Créé par Creart le 15/02/2016.
 */
public class PacketPlayOutPlayerInfo extends SentPacket {

    public PacketPlayOutPlayerInfo(Packet<?> packet, Player player) {
        super(packet, player);
    }

    public List getProfile() {
        return reflection().get("b");
    }

    @Override
    public OutPacketType getType() {
        return OutPacketType.PLAYER_INFO;
    }

}
