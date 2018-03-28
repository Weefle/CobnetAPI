package fr.cobnet.api.packets.in;

import fr.cobnet.api.packets.InPacketType;
import fr.cobnet.api.packets.ReceivedPacket;
import net.minecraft.server.v1_12_R1.Packet;
import org.bukkit.entity.Player;

/**
 * Créé par Creart le 26/01/2016.
 */
public class PacketPlayInTransaction extends ReceivedPacket {

    public PacketPlayInTransaction(Packet<?> packet, Player player) {
        super(packet, player);
    }

    public int getWindowId() {
        return (int) reflection().get("a");
    }

    public int getTransactionId() {
        return (int) reflection().get("b");
    }

    public boolean isAccepted() {
        return (boolean) reflection().get("c");
    }

    public PacketPlayInTransaction setTransactionId(short id) {
        reflection().set("b", id);
        return this;
    }

    public PacketPlayInTransaction setWindowId(short id) {
        reflection().set("b", id);
        return this;
    }

    public PacketPlayInTransaction setAccepted(boolean value) {
        reflection().set("c", value);
        return this;
    }

    @Override
    public InPacketType getType() {
        return InPacketType.TRANSACTION;
    }

}
