package fr.cobnet.api.gui;

import org.apache.commons.lang3.Validate;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.*;

/**
 * Created by creart on 22/02/16.
 */
public abstract class InventoryHandle {

    private static List<InventoryHandle> handles = new ArrayList<>();

    public static List<InventoryHandle> getHandles() {
        return Collections.unmodifiableList(handles);
    }

    static List<InventoryHandle> getRawList() {
        return handles;
    }

    protected final String name;
    protected String displayName;
    protected final int slots;
    protected final Map<Integer, ItemStack> items;
    protected ItemStack opener;

    private boolean modified = true;
    private Inventory inventory;

    public InventoryHandle(int slots, String displayName, String name) {
        Validate.notEmpty(name);
        Validate.isTrue( (slots > 0 && slots % 9 == 0) || slots == 5 );

        this.name = name;
        if (displayName == null || displayName.length() == 0)
            this.displayName = this.name;
        else
            this.displayName = displayName;

        this.slots = slots;
        this.items = new HashMap<>();

        handles.add(this);
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        Validate.notEmpty(displayName);

        this.displayName = displayName;
    }

    public ItemStack getOpener() {
        return opener;
    }

    public void setOpener(ItemStack stack) {
        this.opener = stack;
    }

    public boolean isValidOpener(ItemStack itemStack) {
        return opener.getType() == itemStack.getType() && opener.getData() == itemStack.getData() && opener.getAmount() == itemStack.getAmount()
                && (opener.hasItemMeta() && itemStack.hasItemMeta()
                && opener.getItemMeta().getDisplayName().toLowerCase().equals(itemStack.getItemMeta().getDisplayName().toLowerCase()));
    }

    public int getSlots() {
        return slots;
    }

    public Map<Integer, ItemStack> getItems() {
        return Collections.unmodifiableMap(items);
    }

    Map<Integer, ItemStack> getRawItems() {
        return items;
    }

    public void addItem(int slot, ItemStack stack) {
        Validate.isTrue(slot >= 0 && slot < slots);
        Validate.notNull(stack);

        items.put(slot, stack);
        modified = true;
    }

    public void addItems(Map<Integer, ItemStack> items) {
        for (Map.Entry<Integer, ItemStack> entry : items.entrySet())
            addItem(entry.getKey(), entry.getValue());
    }

    public void addItems(ItemStackSlot... items) {
        if (items.length > 0) {
            for (ItemStackSlot item : items) {
                addItem(item.getSlot(), item.getItemStack());
            }
        }
    }

    public void open(Player player) {
        if (modified) {
            inventory = Bukkit.createInventory(null, slots, displayName);

            for (Map.Entry<Integer, ItemStack> item : items.entrySet()) {
                inventory.setItem(item.getKey(), item.getValue());
            }
        }

        player.openInventory(inventory);
    }

    public abstract void onItemClick(Player player, ItemStack itemStack, boolean isShiftClick, boolean isRightClick, boolean isLeftClick, InventoryAction action);

    public abstract void onRightClickItem(Player player);

    public abstract void onLeftClickItem(Player player);

}
