package fr.cobnet.api.npc;

import com.mojang.authlib.GameProfile;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.PlayerInteractManager;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;

import java.util.UUID;

/**
 * Created by remi on 24/01/2016.
 */
public class NpcBuilder {
    private UUID uuid = UUID.randomUUID();

    public UUID getUniqueId() {
        return uuid;
    }

    private String name = UUID.randomUUID().toString();

    public String getName() {
        return name;
    }

    private String displayName = name;

    public String getDisplayName() {
        return displayName;
    }

    public NpcBuilder withUniqueId(UUID uuid) {
        this.uuid = uuid;
        return this;
    }

    public NpcBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public NpcBuilder withDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public Npc create(World world) {
        EntityPlayer npc = new EntityPlayer(
                ((CraftServer) Bukkit.getServer()).getServer(),
                ((CraftWorld) world).getHandle(),
                new GameProfile(this.uuid, this.displayName),
                new PlayerInteractManager(((CraftWorld) world).getHandle()));

        return new Npc(this, npc);
    }
}
