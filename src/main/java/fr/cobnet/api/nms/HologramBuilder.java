package fr.cobnet.api.nms;

import fr.cobnet.api.i18n.I18n;
import fr.cobnet.api.i18n.Lang;
import fr.cobnet.core.CobnetCore;
import fr.cobnet.core.players.CobPlayer;
import net.minecraft.server.v1_12_R1.EntityArmorStand;
import net.minecraft.server.v1_12_R1.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_12_R1.PacketPlayOutSpawnEntityLiving;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class HologramBuilder {

    private static final CobnetCore INSTANCE = CobnetCore.getInstance();

    private String message;

    public String getMessage() {
        return message;
    }

    private int stay;

    public int getStay() {
        return stay;
    }

    private Location location;

    public Location getLocation() {
        return location;
    }

    /**
     * Si vous faites {@link #sendTo(CobPlayer, String...)} ou {@link #sendToCobPlayers(Collection, String...)}, vous vous devez d'utiliser une clef i18n
     * @param message > String
     * @return HologramBuilder
     */
    public HologramBuilder withMessage(String message) {
        this.message = message;
        return this;
    }

    /**
     * Mettez le @param stay à 0, si vous voulez que l'hologramme reste indéfiniment (à éviter)
     * @param stay > Le temps que va rester l'hologramme
     * @return HologramBuilder
     */
    public HologramBuilder withStay(int stay) {
        this.stay = stay;
        return this;
    }

    /**
     * Permet de set la Location à laquelle va apparaître l'hologramme
     * @param location > Location choisie
     * @return HologramBuilder
     */
    public HologramBuilder withLocation(Location location) {
        this.location = location;
        return this;
    }

    /**
     * Permet d'envoyer l'hologramme à un joueur
     * @param player > le joueur en question
     */
    public void sendTo(Player player) {
        EntityArmorStand stand = getArmorStand(false, null);

        PacketPlayOutSpawnEntityLiving packet = new PacketPlayOutSpawnEntityLiving(stand);

        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);

        if (stay != 0) {
            INSTANCE.getServer().getScheduler().runTaskLater(INSTANCE, () -> {
                PacketPlayOutEntityDestroy destroy = new PacketPlayOutEntityDestroy(stand.getId());
                ((CraftPlayer) player).getHandle().playerConnection.sendPacket(destroy);
            }, stay);
        }
    }

    /**
     * Permet d'envoyer l'hologramme à une collection de joueurs (vous pouvez faire {@link Bukkit#getOnlinePlayers()} pour envoyer à tout le monde)
     * @param players > Les joueurs en question
     */
    public void sendToPlayers(Collection<? extends Player> players) {
        EntityArmorStand stand = getArmorStand(false, null);

        PacketPlayOutSpawnEntityLiving packet = new PacketPlayOutSpawnEntityLiving(stand);

        for (Player player : players)
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);

        if (stay != 0) {
            INSTANCE.getServer().getScheduler().runTaskLater(INSTANCE, () -> {
                PacketPlayOutEntityDestroy destroy = new PacketPlayOutEntityDestroy(stand.getId());
                for (Player player : players)
                    ((CraftPlayer) player).getHandle().playerConnection.sendPacket(destroy);
            }, stay);
        }
    }

    /**
     * Permet d'envoyer à un CobPlayer et donc, se servir des clefs i18n
     * @param player > Le joueur en question
     * @param values > Les valeurs à remplacer dans l'i18n
     */
    public void sendTo(CobPlayer player, String... values) {
        EntityArmorStand stand = getArmorStand(true, player.getLang());
        PacketPlayOutSpawnEntityLiving packet = new PacketPlayOutSpawnEntityLiving(stand);
        Player real = player.getPlayer();
        ((CraftPlayer) real).getHandle().playerConnection.sendPacket(packet);

        if (stay != 0) {
            INSTANCE.getServer().getScheduler().runTaskLater(INSTANCE, () -> {
                PacketPlayOutEntityDestroy destroy = new PacketPlayOutEntityDestroy(stand.getId());
                ((CraftPlayer) real).getHandle().playerConnection.sendPacket(destroy);
            }, stay);
        }
    }

    /**
     * Permet d'envoyer à tous les joueurs de la collection un Hologramme traduit
     * @param players > Les joueurs en question
     * @param values > Les valeurs à remplacer dans la traduction i18n
     */
    public void sendToCobPlayers(Collection<CobPlayer> players, String... values) {
        List<EntityArmorStand> armorStands = new ArrayList<>(players.size());
        for (CobPlayer player : players) {
            EntityArmorStand stand = getArmorStand(true, player.getLang(), values);
            PacketPlayOutSpawnEntityLiving packet = new PacketPlayOutSpawnEntityLiving(stand);

            Player real = player.getPlayer();
            ((CraftPlayer) real).getHandle().playerConnection.sendPacket(packet);
            armorStands.add(stand);
        }

        if (stay != 0) {
            INSTANCE.getServer().getScheduler().runTaskLater(INSTANCE, () -> {
                for (EntityArmorStand stand : armorStands) {
                    PacketPlayOutEntityDestroy destroy = new PacketPlayOutEntityDestroy(stand.getId());

                    for (CobPlayer player : players)
                        ((CraftPlayer) player.getPlayer()).getHandle().playerConnection.sendPacket(destroy);
                }
            }, stay);
        }
    }

    private EntityArmorStand getArmorStand(boolean translation, Lang lang, String... values) {
        EntityArmorStand stand = new EntityArmorStand(((CraftWorld) location.getWorld()).getHandle());
        stand.setLocation(location.getX(), location.getY(), location.getZ(), 0, 0);
        stand.setCustomName(translation ? I18n.tl(lang, message, values) : message);
        stand.setCustomNameVisible(true);
        stand.setNoGravity(true);
        stand.setInvisible(true);
        return stand;
    }

}
