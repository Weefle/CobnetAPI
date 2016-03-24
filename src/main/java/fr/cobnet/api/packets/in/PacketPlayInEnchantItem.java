package fr.cobnet.api.packets.in;

import fr.cobnet.api.packets.InPacketType;
import fr.cobnet.api.packets.ReceivedPacket;
import net.minecraft.server.v1_8_R3.Packet;
import org.bukkit.entity.Player;

/**
 * Créé par Creart le 26/01/2016.
 */
public class PacketPlayInEnchantItem extends ReceivedPacket {

    public PacketPlayInEnchantItem(Packet<?> packet, Player player) {
        super(packet, player);
    }

    public int getEnchantmentWindowId() {
        return reflection().get("a");
    }

    public int getEnchantmentNumber() {
        return reflection().get("b");
    }

    public void setEnchantmentNumber(int number) {
        reflection().set("b", number);
    }

    @Override
    public InPacketType getType() {
        return InPacketType.ENCHANT_ITEM;
    }

}
