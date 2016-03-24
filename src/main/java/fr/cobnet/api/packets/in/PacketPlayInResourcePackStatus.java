package fr.cobnet.api.packets.in;

import fr.cobnet.api.packets.InPacketType;
import fr.cobnet.api.packets.ReceivedPacket;
import net.minecraft.server.v1_8_R3.Packet;
import org.bukkit.entity.Player;

/**
 * Créé par Creart le 26/01/2016.
 */
public class PacketPlayInResourcePackStatus extends ReceivedPacket {

    public PacketPlayInResourcePackStatus(Packet<?> packet, Player player) {
        super(packet, player);
    }

    @Override
    public InPacketType getType() {
        return InPacketType.RESOURCE_PACK_STATUS;
    }

    public net.minecraft.server.v1_8_R3.PacketPlayInResourcePackStatus.EnumResourcePackStatus getStatus() {
        return reflection().get("b");
    }

}
