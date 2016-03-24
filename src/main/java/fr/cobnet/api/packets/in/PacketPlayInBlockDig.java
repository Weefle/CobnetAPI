package fr.cobnet.api.packets.in;

import fr.cobnet.api.packets.InPacketType;
import fr.cobnet.api.packets.ReceivedPacket;
import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.EnumDirection;
import net.minecraft.server.v1_8_R3.Packet;
import org.bukkit.entity.Player;

/**
 * Créé par Creart le 26/01/2016.
 */
public class PacketPlayInBlockDig extends ReceivedPacket {

    public PacketPlayInBlockDig(Packet<?> packet, Player player) {
        super(packet, player);
    }

    public BlockPosition getPosition() {
        return reflection().get("a");
    }

    public EnumDirection getDirection() {
        return reflection().get("b");
    }

    public net.minecraft.server.v1_8_R3.PacketPlayInBlockDig.EnumPlayerDigType getDigType() {
        return reflection().get("c");
    }

    public void setPosition(BlockPosition position) {
        reflection().set("a", position);
    }

    public void setDirection(EnumDirection direction) {
        reflection().set("b", direction);
    }

    public void setDigType(net.minecraft.server.v1_8_R3.PacketPlayInBlockDig.EnumPlayerDigType type) {
        reflection().set("c", type);
    }

    @Override
    public InPacketType getType() {
        return InPacketType.BLOCK_DIG;
    }

}
