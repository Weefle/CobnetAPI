package fr.cobnet.api.gui;

import org.bukkit.inventory.ItemStack;

/**
 * Created by creart on 22/02/16.
 */
public class ItemStackSlot {

    private ItemStack itemStack;
    private int slot;

    public ItemStackSlot(ItemStack itemStack, int slot) {
        this.itemStack = itemStack;
        this.slot = slot;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public int getSlot() {
        return slot;
    }

}
