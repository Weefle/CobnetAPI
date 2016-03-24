package fr.cobnet.api.items;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

/**
 * @TODO: lazy initializer
 * Created by Rémi on 02/01/2016.
 */
public class ItemBuilder {
    private String name;

    public String getName() {
        return name;
    }

    private Material material;

    public Material getMaterial() {
        return material;
    }

    private int amount;

    public int getAmount() {
        return amount;
    }

    private byte data;

    public byte getData() {
        return data;
    }

    private List<String> lore;

    public List<String> getLore() {
        return lore;
    }

    private List<ItemFlag> flags;

    public List<ItemFlag> getFlags() {
        return flags;
    }

    private Map<Enchantment, Integer> enchantments;

    public Map<Enchantment, Integer> getEnchantments() {
        return enchantments;
    }

    /**
     * @param material Material
     */
    public ItemBuilder(Material material) {
        this.material = material;
        this.amount = 1;
        this.data = (byte) 0;
    }

    /**
     * @param material Material
     * @param amount int
     */
    public ItemBuilder(Material material, int amount) {
        this.material = material;
        this.amount = amount;
        this.data = (byte) 0;
    }

    /**
     * @param name String
     * @return Item
     */
    public ItemBuilder withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * @param amount int
     * @return Item
     */
    public ItemBuilder withAmount(int amount) {
        this.amount = amount;
        return this;
    }

    /**
     * @return Item
     */
    public ItemBuilder hideEnchant() {
        if(this.flags == null) {
            this.flags = new ArrayList<>();
        }
        this.flags.add(ItemFlag.HIDE_ENCHANTS);
        return this;
    }

    /**
     * @param data byte
     * @return Item
     */
    public ItemBuilder withData(byte data) {
        this.data = data;
        return this;
    }

    /**
     * @param lore String[]
     * @return Item
     */
    public ItemBuilder withLore(String[] lore) {
        this.lore = Arrays.asList(lore);
        return this;
    }

    /**
     * @param flag ItemFlag
     * @return
     */
    public ItemBuilder withItemFlag(ItemFlag flag) {
        if(this.flags == null) {
            this.flags = new ArrayList<>();
        }
        this.flags.add(flag);
        return this;
    }

    /**
     * @param enchant Enchantment
     * @param level int
     * @return Item
     */
    public ItemBuilder withEnchant(Enchantment enchant, int level) {
        if(this.enchantments == null) {
            this.enchantments = new HashMap<>();
        }
        this.enchantments.put(enchant, level);
        return this;
    }

    /**
     * Return the item.
     * @return ItemStack
     */
    public ItemStack build() {
        ItemStack item = new ItemStack(this.material, this.amount);
        item.setDurability((short) this.data);
        ItemMeta meta = item.getItemMeta();
        meta.setLore(this.lore);
        if(this.name != null) {
            meta.setDisplayName(this.name);
        }
        if(this.enchantments != null) {
            for(Map.Entry<Enchantment, Integer> enchant : this.enchantments.entrySet()) {
                meta.addEnchant(enchant.getKey(), enchant.getValue(), true);
            }
        }
        if(this.flags != null) {
            meta.addItemFlags(this.flags.toArray(new ItemFlag[this.flags.size()]));
        }
        item.setItemMeta(meta);
        return item;
    }
}
