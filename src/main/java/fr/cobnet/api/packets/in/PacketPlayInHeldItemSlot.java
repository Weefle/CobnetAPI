package fr.cobnet.api.packets.in;

import fr.cobnet.api.packets.InPacketType;
import fr.cobnet.api.packets.ReceivedPacket;
import net.minecraft.server.v1_12_R1.Packet;
import org.bukkit.entity.Player;

/**
 * Créé par Creart le 26/01/2016.
 */
public class PacketPlayInHeldItemSlot extends ReceivedPacket {

    public PacketPlayInHeldItemSlot(Packet<?> packet, Player player) {
        super(packet, player);
    }

    public int getItemSlot() {
        return reflection().get("itemInHandIndex");
    }

    @Override
    public InPacketType getType() {
        return InPacketType.HELD_ITEM_SLOT;
    }

}
