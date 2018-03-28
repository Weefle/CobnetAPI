package fr.cobnet.core.listeners;

import fr.cobnet.api.events.Listener;
import fr.cobnet.core.CobnetCore;
import net.minecraft.server.v1_12_R1.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_12_R1.PacketPlayOutPlayerInfo.EnumPlayerInfoAction;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

public class InvisblePlayerFixListener extends Listener {

    private CobnetCore plugin;

    public InvisblePlayerFixListener(CobnetCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            PacketPlayOutPlayerInfo packet = new PacketPlayOutPlayerInfo(EnumPlayerInfoAction.ADD_PLAYER, ((CraftPlayer) player).getHandle());
            if (player.isValid() && player.isOnline()) {
                Bukkit.getOnlinePlayers().stream().forEach(online -> sendPacket(online, packet));
            }
        }, 10);
    }

}
