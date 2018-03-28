package fr.cobnet.api.npc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.minecraft.server.v1_12_R1.EntityPlayer;
import net.minecraft.server.v1_12_R1.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_12_R1.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.v1_12_R1.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_12_R1.PlayerConnection;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

/**
 * Created by remi on 24/01/2016.
 */
public class Npc {
    private static Map<String, Npc> npcs = new HashMap<>();
    private UUID uuid;
    private String name;
    private EntityPlayer npc;
    public List<UUID> injected;

    public UUID getUniqueId() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public EntityPlayer getNpc() {
        return npc;
    }

    public List<UUID> getInjected() {
        return injected;
    }

    public Npc(NpcBuilder builder, EntityPlayer npc) {
        this.uuid = builder.getUniqueId();
        this.name = builder.getName();
        this.npc = npc;
        this.injected = new ArrayList<>();
        this.npc.setCustomName(builder.getDisplayName());

        npcs.put(this.name, this);
    }

    public Player getBukkitEntity() {
        return this.npc.getBukkitEntity();
    }

    public void hide() {
        this.npc.setInvisible(true);
        this.npc.setCustomNameVisible(false);
    }

    public void setLocation(Location location) {
        this.npc.getBukkitEntity().getLocation().setWorld(location.getWorld());
        this.npc.setLocation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
    }

    public void inject(Player player) {
        PacketPlayOutPlayerInfo packet = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, this.npc);
        PacketPlayOutNamedEntitySpawn spawn = new PacketPlayOutNamedEntitySpawn(this.npc);

        PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
        connection.sendPacket(packet);
        connection.sendPacket(spawn);

        injected.add(player.getUniqueId());
    }

    public void remove() {
        npc.getBukkitEntity().remove();
        PacketPlayOutPlayerInfo packet = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, npc);
        PacketPlayOutEntityDestroy destroy = new PacketPlayOutEntityDestroy(npc.getId());

        injected.forEach(uuid -> {
            Player player = Bukkit.getPlayer(uuid);
            if (player != null && player.isOnline()) {
                PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
                connection.sendPacket(packet);
                connection.sendPacket(destroy);
            }
        });

        injected.clear();
    }

    public static Collection<Npc> all() {
        return npcs.values();
    }

    public static Npc findByName(String name) {
        return npcs.get(name);
    }

    public static void clear() {
        npcs.clear();
        npcs = new HashMap<>();
    }

    public static void remove(String name) {
        Npc npc = npcs.remove(name);
        npc.remove();
    }

}
