package fr.cobnet.api.packets.in;

import fr.cobnet.api.packets.InPacketType;
import fr.cobnet.api.packets.ReceivedPacket;
import net.minecraft.server.v1_12_R1.Packet;
import org.bukkit.entity.Player;

/**
 * Créé par Creart le 26/01/2016.
 */
public class PacketPlayInPositionLook extends ReceivedPacket {

    public PacketPlayInPositionLook(Packet<?> packet, Player player) {
        super(packet, player);
    }

    public float getYaw() {
        return reflection().get("yaw");
    }

    public float getPitch() {
        return reflection().get("pitch");
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

    @Override
    public InPacketType getType() {
        return InPacketType.POSITION_LOOK;
    }

}
