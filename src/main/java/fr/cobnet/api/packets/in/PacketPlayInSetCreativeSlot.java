package fr.cobnet.api.packets.in;

import fr.cobnet.api.packets.InPacketType;
import fr.cobnet.api.packets.ReceivedPacket;
import net.minecraft.server.v1_12_R1.Packet;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Créé par Creart le 26/01/2016.
 */
public class PacketPlayInSetCreativeSlot extends ReceivedPacket {

    public PacketPlayInSetCreativeSlot(Packet<?> packet, Player player) {
        super(packet, player);
    }

    public int getSlot() {
        return reflection().get("slot");
    }

    public net.minecraft.server.v1_12_R1.ItemStack getRawItemStack() {
        return reflection().get("b");
    }

    public ItemStack getItemStack() {
        return CraftItemStack.asBukkitCopy(getRawItemStack());
    }

    @Override
    public InPacketType getType() {
        return InPacketType.SET_CREATIVE_SLOT;
    }

}
