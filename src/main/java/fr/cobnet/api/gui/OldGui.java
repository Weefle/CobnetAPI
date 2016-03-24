package fr.cobnet.api.gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Rémi on 02/01/2016.
 */
@Deprecated
public class OldGui implements Listener {

    private static List<OldGui> guis = new ArrayList<>();

    private Plugin plugin;
    private String name;
    private int slots;
    private HashMap<Integer, ItemStack> items;
    //private ClickEventHandler clickHandler;
    //private OpenEventHandler openHandler;
    //private CloseEventHandler closeHandler;

    /**
     * @param plugin Plugin
     * @param name String
     * @param slots int
     */
    public OldGui(Plugin plugin, String name, int slots) {
        this.plugin = plugin;
        this.name = name;
        this.slots = slots;
        this.items = new HashMap<>();

        guis.add(this);
    }

    /**
     * @return String
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return int
     */
    public int getSlots() {
        return this.slots;
    }

    /**
     * @param slots int
     */
    public void setSlots(int slots) {
        this.slots = slots;
    }

    /*
     * @param clickHandler ClickEventHandler

    public void setClickHandler(ClickEventHandler clickHandler) {
        this.clickHandler = clickHandler;
    }

    /**
     * @param openHandler OpenEventHandler
     */
    /*public void setOpenHandler(OpenEventHandler openHandler) {
        this.openHandler = openHandler;
    }

    /**
     * @param closeHandler CloseEventHandler
     */
    /*public void setCloseHandler(CloseEventHandler closeHandler) {
        this.closeHandler = closeHandler;
    }*/

    /**
     * @param player Player
     */
    public void open(Player player) {
        Inventory inventory = Bukkit.createInventory(null, this.slots, this.name);
        for(Map.Entry<Integer, ItemStack> item : this.items.entrySet()) {
            inventory.setItem(item.getKey(), item.getValue());
        }
        player.openInventory(inventory);
    }

    /**
     * @param slot int
     * @param item ItemStack
     */
    public void setItem(int slot, ItemStack item) {
        this.items.put(slot, item);
    }

    /**
     * @param slot int
     */
    public void removeItem(int slot) {
        if (this.items.containsKey(slot)) {
            this.items.remove(slot);
        }
    }

    /*interface ClickEventHandler {
        /**
         * @param e ClickEvent
         */
        /*public void onClick(ClickEvent e);
    }

    /*interface OpenEventHandler {
        /**
         * @param e OpenEvent
         */
        /*public void onOpen(OpenEvent e);
    }

    interface CloseEventHandler {
        /**
         * @param e CloseEvent
         */
        /*public void onClose(CloseEvent e);
    }

    /*class ClickEvent {
        private Player player;
        private int position;
        private Inventory inventory;
        private ItemStack item;

        /**
         * @param player Player
         * @param position int
         * @param inventory Inventory
         * @param item ItemStack
         */
        /*public ClickEvent(Player player, int position, Inventory inventory, ItemStack item) {
            this.player = player;
            this.position = position;
            this.inventory = inventory;
            this.item = item;
        }

        /**
         * @return Player
         */
        /*public Player getPlayer() {
            return this.player;
        }

        /**
         * @return int
         */
        /*public int getPosition() {
            return this.position;
        }

        /**
         * @return Inventory
         */
        /*public Inventory getInventory() {
            return this.inventory;
        }

        /**
         * @return ItemStack
         */
        /*public ItemStack getItem() {
            return this.item;
        }
    }

    class OpenEvent {
        private Player player;
        private Inventory inventory;

        /**
         * @param player Player
         * @param inventory Inventory
         */
        /*public OpenEvent(Player player, Inventory inventory) {
            this.player = player;
            this.inventory = inventory;
        }

        /**
         * @return Player
         */
        /*public Player getPlayer() {
            return this.player;
        }

        /**
         * @return Inventory
         */
        /*public Inventory getInventory() {
            return this.inventory;
        }
    }

    class CloseEvent {
        private Player player;
        private Inventory inventory;

        /**
         * @param player Player
         * @param inventory Inventory
         */
        /*public CloseEvent(Player player, Inventory inventory) {
            this.player = player;
            this.inventory = inventory;
        /*}

        /**
         * @return Player
         */
        /*public Player getPlayer() {
            return this.player;
        }

        /**
         * @return Inventory
         */
        /*public Inventory getInventory() {
            return this.inventory;
        }
    }*/

    /*@EventHandler(priority = EventPriority.HIGHEST)
    public void onInventoryClickEvent(InventoryClickEvent e) {
        if(e.getInventory().getName().equalsIgnoreCase(this.name)) {
            e.setCancelled(true);
            if(e.getCurrentItem() != null && e.getCurrentItem().getType() != null) {
                if(e.getCurrentItem().getType() != Material.AIR) {
                    if(this.clickHandler != null) {
                        this.clickHandler.onClick(new ClickEvent((Player) e.getWhoClicked(), e.getRawSlot(), e.getInventory(), e.getCurrentItem()));
                    }
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInventoryOpenEvent(InventoryOpenEvent e) {
        if(e.getInventory().getName().equalsIgnoreCase(this.name)) {
            if(this.openHandler != null) {
                this.openHandler.onOpen(new OpenEvent((Player) e.getPlayer(), e.getInventory()));
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInventoryCloseEvent(InventoryCloseEvent e) {
        if(e.getInventory().getName().equalsIgnoreCase(this.name)) {
            if(this.closeHandler != null) {
                this.closeHandler.onClose(new CloseEvent((Player) e.getPlayer(), e.getInventory()));
            }
            this.clickHandler = null;
            this.openHandler = null;
            this.closeHandler = null;
        }
    }*/
}
