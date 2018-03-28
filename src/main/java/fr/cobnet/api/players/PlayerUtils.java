package fr.cobnet.api.players;

import com.google.common.annotations.Beta;
import com.mojang.authlib.GameProfile;
import fr.cobnet.api.lang.ReflectionUtils;
import fr.cobnet.core.CobnetCore;
import fr.cobnet.core.players.ProfileLoader;
import net.minecraft.server.v1_12_R1.EntityPlayer;
import net.minecraft.server.v1_12_R1.PacketPlayOutResourcePackSend;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

public class PlayerUtils {

    /**
     * Permet de définir si le joueur a une collision avec les entités
     *
     * @param player
     * @param b
     */
    public static void setEntityCollision(Player player, Boolean b) {
        ((CraftPlayer) player).getHandle().collides = b;
    }


    /**
     * Permet de changer le pseudonyme d'un joueur
     *
     * @param player     > Le joueur en question
     * @param pseudonyme > Le nouveau pseudonyme
     */
    @Beta
    public static void changePseudo(Player player, String pseudonyme) {
        Validate.notNull(player);
        Validate.notNull(pseudonyme);
        Validate.isTrue(pseudonyme.length() > 0 && pseudonyme.length() < 16);

        EntityPlayer ent = ((CraftPlayer) player).getHandle();
        GameProfile profile = ent.getProfile();
        try {
            ReflectionUtils.setValue(profile, true, "name", pseudonyme);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean applyProfile(Player player, ProfileLoader skinProfile) {
        Validate.notNull(player);
        Validate.notNull(skinProfile);

        EntityPlayer ent = ((CraftPlayer) player).getHandle();
        GameProfile profile = skinProfile.loadProfile();

        if (profile == null) {
            System.err.println("Couldn't load skin or for some other reason, couldn't load custom profile");
            return false;
        }

        try {
            ReflectionUtils.setValue(ent, true, "profile", profile);
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Permet de mettre des flèches dans le corps d'un joueur (au max 127 - byte)
     *
     * @param player
     * @param amount
     */
    public static void setArrowInBody(Player player, byte amount) {
        try {
			((CraftPlayer) player).getHandle().getDataWatcher().wait(9, amount);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    /**
     * Permet d'obtenir le ping du joueur
     *
     * @param player
     * @return
     */
    public static int getPing(Player player) {
        return ((CraftPlayer) player).getHandle().ping;
    }

    /**
     * Reset totalement le joueur
     *
     * @param player
     */
    @SuppressWarnings("deprecation")
	public static void razPlayer(Player player) {
        player.setMaxHealth(20);
        player.setFoodLevel(20);
        player.setSaturation(20);
        player.setExp(0);
        player.setLevel(0);
        player.setFallDistance(0);
        player.setFireTicks(0);
        clearFullInventory(player);
        resetPlayer(player);
    }

    /**
     * Vide totalement l'inventaire du joueur
     *
     * @param player
     */
    @SuppressWarnings("deprecation")
	public static void clearFullInventory(Player player) {
        player.closeInventory();
        player.getInventory().clear();
        player.setItemInHand(new ItemStack(Material.AIR));
        player.getInventory().setHelmet(null);
        player.getInventory().setChestplate(null);
        player.getInventory().setLeggings(null);
        player.getInventory().setBoots(null);
    }

    /**
     * Permet de reset qqes caractéristiques du joueur
     *
     * @param player
     */
    @SuppressWarnings("deprecation")
	public static void resetPlayer(Player player) {
        player.setMaxHealth(20);
        player.setHealth(20);
        player.setFoodLevel(20);
        player.setSaturation(20);
        removePotionEffects(player);
    }

    /**
     * Permet d'enlever les effets de potion du joueur
     *
     * @param player
     */
    public static void removePotionEffects(Player player) {
        for (PotionEffect effect : player.getActivePotionEffects())
            player.removePotionEffect(effect.getType());
    }

    /**
     * Permet de reset l'inventaire du joueur (sans l'armure)
     *
     * @param player
     */
    @SuppressWarnings("deprecation")
	public static void clearInventory(Player player) {
        player.closeInventory();
        player.getInventory().clear();
        player.setItemInHand(new ItemStack(Material.AIR));
    }

    /**
     * Permet de reset la durabilité de l'armure du joueur
     *
     * @param player
     */
    public static void resetArmorDurability(Player player) {
        for (ItemStack itemStack : player.getInventory().getArmorContents()) {
            if (itemStack != null && itemStack.getType() != Material.AIR) itemStack.setDurability((short) -1);
        }
    }

    /**
     * Permet de forcer le téléchargement du resource pack
     *
     * @param player
     */
    @Beta
    public static void forceResourcePack(Player player) {
        PacketPlayOutResourcePackSend packet = new PacketPlayOutResourcePackSend();
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }

    /**
     * Permet de forcer le respawn du joueur après un certain délai
     *
     * @param player
     * @param ticks
     */
    public static void forceRespawnLater(Player player, long ticks) {
        Bukkit.getScheduler().runTaskLater(CobnetCore.getInstance(), new Runnable() {
            @Override
            public void run() {
                forceRespawn(player);
            }
        }, ticks);
    }

    /**
     * Permet de forcer instantanément le respawn du joueur
     *
     * @param player
     */
    public static void forceRespawn(Player player) {
        if (player == null || !player.isDead() || !player.isOnline()) return;
        player.spigot().respawn();
    }

    /**
     * Permet d'avoir le nombre de joueurs en ligne, mise à part les Spectateurs
     *
     * @return
     */
    public static long getRealOnlinePlayers() {
        return Bukkit.getOnlinePlayers().stream().filter(p -> p.getGameMode() != GameMode.SPECTATOR).count();
    }

}