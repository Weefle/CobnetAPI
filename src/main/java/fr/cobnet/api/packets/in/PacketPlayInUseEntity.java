package fr.cobnet.api.packets.in;

import fr.cobnet.api.packets.InPacketType;
import fr.cobnet.api.packets.ReceivedPacket;
import net.minecraft.server.v1_12_R1.Packet;
import net.minecraft.server.v1_12_R1.Vec3D;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

/**
 * Créé par Creart le 26/01/2016.
 */
public class PacketPlayInUseEntity extends ReceivedPacket {

    public PacketPlayInUseEntity(Packet<?> packet, Player player) {
        super(packet, player);
    }

    public Vector getBlockInteractAt() {
        Vec3D vec3d = reflection().get("c");
        return (vec3d == null) ? null : new Vector(Double.valueOf(vec3d.a().toString()), vec3d.b(), vec3d.c(vec3d.x, vec3d.y, vec3d.z));
    }

    public net.minecraft.server.v1_12_R1.PacketPlayInUseEntity.EnumEntityUseAction getAction() {
        return reflection().get("action");
    }

    public int getEntityId() {
        return reflection().get("a");
    }

    @Override
    public InPacketType getType() {
        return InPacketType.USE_ENTITY;
    }

}
