package fr.cobnet.api.packets.in;

import fr.cobnet.api.packets.InPacketType;
import fr.cobnet.api.packets.ReceivedPacket;
import net.minecraft.server.v1_12_R1.Packet;
import org.bukkit.entity.Player;

/**
 * Créé par Creart le 26/01/2016.
 */
public class PacketPlayInLook extends ReceivedPacket {

    public PacketPlayInLook(Packet<?> packet, Player player) {
        super(packet, player);
    }

    public float getYaw() {
        return reflection().get("yaw");
    }

    public float getPitch() {
        return reflection().get("pitch");
    }

    @Override
    public InPacketType getType() {
        return InPacketType.LOOK;
    }

}
