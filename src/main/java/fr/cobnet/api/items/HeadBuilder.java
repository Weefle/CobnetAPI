package fr.cobnet.api.items;

import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.*;

/**
 * Created by remi on 24/01/2016.
 */
public class HeadBuilder {
    private String displayName;

    public String getDisplayName() {
        return displayName;
    }

    private String name;

    public String getName() {
        return name;
    }

    private int amount;

    public int getAmount() {
        return amount;
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
     * @param name String Name of the head
     */
    public HeadBuilder(String name) {
        this.name = name;
        this.amount = 1;
    }

    /**
     * @param name String Name of the head
     * @param amount int
     */
    public HeadBuilder(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    /**
     * @param displayName String
     * @return Item
     */
    public HeadBuilder withDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    /**
     * @param amount int
     * @return Item
     */
    public HeadBuilder withAmount(int amount) {
        this.amount = amount;
        return this;
    }

    /**
     * @return Item
     */
    public HeadBuilder hideEnchant() {
        if(this.flags == null) {
            this.flags = new ArrayList<>();
        }
        this.flags.add(ItemFlag.HIDE_ENCHANTS);
        return this;
    }

    /**
     * @param lore String[]
     * @return HeadBuilder
     */
    public HeadBuilder withLore(String[] lore) {
        this.lore = Arrays.asList(lore);
        return this;
    }

    /**
     * @param flag ItemFlag
     * @return HeadBuilder
     */
    public HeadBuilder withItemFlag(ItemFlag flag) {
        if(this.flags == null) {
            this.flags = new ArrayList<>();
        }
        this.flags.add(flag);
        return this;
    }

    /**
     * @param enchant Enchantment
     * @param level int
     * @return HeadBuilder
     */
    public HeadBuilder withEnchant(Enchantment enchant, int level) {
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
    @SuppressWarnings("deprecation")
	public ItemStack build() {
        ItemStack item = new ItemStack(Material.SKULL_ITEM, this.amount, (short) SkullType.PLAYER.ordinal());
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        meta.setLore(this.lore);
        meta.setOwner(this.name);
        if(this.name != null) {
            meta.setDisplayName(this.displayName);
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
