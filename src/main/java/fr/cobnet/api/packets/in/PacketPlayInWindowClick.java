package fr.cobnet.api.packets.in;

import fr.cobnet.api.packets.InPacketType;
import fr.cobnet.api.packets.ReceivedPacket;
import net.minecraft.server.v1_8_R3.ItemStack;
import net.minecraft.server.v1_8_R3.Packet;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;

/**
 * Créé par Creart le 26/01/2016.
 */
public class PacketPlayInWindowClick extends ReceivedPacket {

    public PacketPlayInWindowClick(Packet<?> packet, Player player) {
        super(packet, player);
    }

    public ItemStack getItem() {
        return reflection().get("item");
    }

    public org.bukkit.inventory.ItemStack getBukkitItem() {
        return CraftItemStack.asBukkitCopy(getItem());
    }

    public short getTransactionId() {
        return reflection().get("d");
    }

    public int getWindowId() {
        return reflection().get("a");
    }

    public PacketPlayInWindowClick setItem(org.bukkit.inventory.ItemStack item) {
        reflection().set("item", CraftItemStack.asNMSCopy(item));
        return this;
    }

    public PacketPlayInWindowClick setWindowId(int windowId) {
        reflection().set("a", windowId);
        return this;
    }

    public PacketPlayInWindowClick setTransactionId(short transactionId) {
        reflection().set("d", transactionId);
        return this;
    }

    public PacketPlayInWindowClick setButton(int button) {
        reflection().set("button", button);
        return this;
    }

    public PacketPlayInWindowClick setMode(int mode) {
        reflection().set("shift", mode);
        return this;
    }

    public PacketPlayInWindowClick setSlot(int slot) {
        reflection().set("slot", slot);
        return this;
    }

    @Override
    public InPacketType getType() {
        return InPacketType.WINDOW_CLICK;
    }

}
