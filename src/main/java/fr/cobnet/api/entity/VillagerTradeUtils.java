package fr.cobnet.api.entity;

import java.lang.reflect.Field;
import net.minecraft.server.v1_12_R1.EntityVillager;
import net.minecraft.server.v1_12_R1.MerchantRecipe;
import net.minecraft.server.v1_12_R1.MerchantRecipeList;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftVillager;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemStack;

/**
 * @INDEV @TODO
 */
public class VillagerTradeUtils {

    /**
     * Xharos 11/12/2015
     * <p>
     * <p>
     * HOW TO USE :
     * - VillagerTradeUtils vt
     * - vt = new VillagerTrade(new
     * Location(@param Location)); vt.addTrade(new
     * ItemStack(Material.YELLOW_FLOWER), new ItemStack(Material.EMERALD));
     * <p>
     * -public void onDisable() { vt.getVillager().remove(); }
     */

    Villager villager;

    /**
     * @param location Create a CustomTraderPnj
     */
    public VillagerTradeUtils(Location location) {
        villager = (Villager) location.getWorld().spawnEntity(location, EntityType.VILLAGER);
    }

    /*
     *
     * Change a pnj already exist
     *
     */
    public VillagerTradeUtils(Villager pnj) {
        this.villager = pnj;
    }

    /*
     *
     * @param name
     *
     */
    public void setCustomName(String name) {
        villager.setCustomName(name);
    }

    /*
     *
     * Set name visible
     *
     */
    public void setCustomNameVisible(boolean visible) {
        villager.setCustomNameVisible(visible);
    }

    /**
     * Reset trade of a npc, YOU HAVE TO CALL THIS METHOD FIRST
     */
    public void resetTrade() {
        EntityVillager entityVillager = ((CraftVillager) villager).getHandle();
        try {
            Field careerLevelField = EntityVillager.class.getDeclaredField("by");
            careerLevelField.setAccessible(true);
            careerLevelField.set(entityVillager, 10);

            Field recipeListField = EntityVillager.class.getDeclaredField("br");
            recipeListField.setAccessible(true);
            MerchantRecipeList recipeList = (MerchantRecipeList) recipeListField.get(entityVillager);
            if (recipeList == null) {
                recipeList = new MerchantRecipeList();
                recipeListField.set(entityVillager, recipeList);
            }
            recipeList.clear();
        } catch (Exception exc) {
        }

    }

    /*
     *
     * buyingItem1 buyingItem2 SellingItem1
     *
     */
    public void addTrade(ItemStack buyingItem1, ItemStack buyingItem2, ItemStack sellingItem) {

        try {
            EntityVillager entityvillager = ((CraftVillager) villager).getHandle();
            Field recipeListField = EntityVillager.class.getDeclaredField("br");
            recipeListField.setAccessible(true);
            MerchantRecipeList recipeList = (MerchantRecipeList) recipeListField.get(entityvillager);
            recipeList.add(createMerchantRecipe(buyingItem1, buyingItem2, sellingItem));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     *
     * Add a trade, constructor upstair
     *
     */
    public void addTrade(ItemStack buyingItem1, ItemStack sellingItem) {
        addTrade(buyingItem1, null, sellingItem);
    }

    /**
     * Get a NPC
     */
    public Villager getVillager() {
        return villager;
    }

    /**
     * Get NPC TradeList
     */
    public MerchantRecipeList getTrade() {
        try {
            EntityVillager entityvillager = ((CraftVillager) villager).getHandle();
            Field recipeListField = EntityVillager.class.getDeclaredField("br");
            recipeListField.setAccessible(true);
            return (MerchantRecipeList) recipeListField.get(entityvillager);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * create MarchantRecipe
     *
     * @param item1
     * @param item2
     * @param item3
     */
    public static MerchantRecipe createMerchantRecipe(ItemStack item1, ItemStack item2, ItemStack item3) {
        MerchantRecipe recipe = new MerchantRecipe(convertItemStack(item1), convertItemStack(item2),
                convertItemStack(item3));
        try {
            Field maxUsesField = MerchantRecipe.class.getDeclaredField("maxUses");
            maxUsesField.setAccessible(true);
            maxUsesField.set(recipe, 10000);
            Field rewardExpField = MerchantRecipe.class.getDeclaredField("rewardExp");
            rewardExpField.setAccessible(true);
            rewardExpField.set(recipe, false);
        } catch (Exception e) {
        }
        return recipe;
    }

    /*
     *
     * Same
     *
     */
    public static MerchantRecipe createMerchantRecipe(ItemStack item1, ItemStack item2) {
        MerchantRecipe recipe = new MerchantRecipe(convertItemStack(item1), convertItemStack(item2));
        try {
            Field maxUsesField = MerchantRecipe.class.getDeclaredField("maxUses");
            maxUsesField.setAccessible(true);
            maxUsesField.set(recipe, 10000);
            Field rewardExpField = MerchantRecipe.class.getDeclaredField("rewardExp");
            rewardExpField.setAccessible(true);
            rewardExpField.set(recipe, false);
        } catch (Exception e) {
        }
        return recipe;
    }

    /**
     * ConvertItemStack
     *
     * @param item
     */
    private static net.minecraft.server.v1_12_R1.ItemStack convertItemStack(ItemStack item) {
        if (item == null)
            return null;
        return CraftItemStack.asNMSCopy(item);
    }
}
