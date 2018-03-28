package fr.cobnet.api.packets.in;

import fr.cobnet.api.packets.InPacketType;
import fr.cobnet.api.packets.ReceivedPacket;
import net.minecraft.server.v1_12_R1.BlockPosition;
import net.minecraft.server.v1_12_R1.Packet;
import net.minecraft.server.v1_12_R1.Vector3f;
import org.bukkit.entity.Player;

/**
 * Créé par Creart le 26/01/2016.
 */
public class PacketPlayInBlockPlace extends ReceivedPacket {

    public PacketPlayInBlockPlace(Packet<?> packet, Player player) {
        super(packet, player);
    }

    BlockPosition getPos() {
        return reflection().get("a");
    }

    public BlockPosition getPosition() {
        return isInteraction() ? null : reflection().get("a");
    }

    /**
     *
     * @return <code>true</code> si le joueur n'a pas fait de click gauche
     */
    public boolean isInteraction() {
        BlockPosition blockPosition = getPos();
        return blockPosition.getX() == -1 && blockPosition.getY() == -1 && blockPosition.getZ() == -1;
    }

    /**
     *
     * @return Position du curseur sur le Block
     */
    public Vector3f getCursorPosition() {
        return new Vector3f(reflection().get("e"), reflection().get("f"), reflection().get("g"));
    }

    public PacketPlayInBlockPlace setPosition(BlockPosition position) {
        reflection().set("a", position);
        return this;
    }

    public PacketPlayInBlockPlace setCursorPosition(Vector3f vector3f) {
        reflection().set("e", vector3f.getX());
        reflection().set("f", vector3f.getY());
        reflection().set("g", vector3f.getZ());
        return this;
    }

    @Override
    public InPacketType getType() {
        return InPacketType.BLOCK_PLACE;
    }

}
