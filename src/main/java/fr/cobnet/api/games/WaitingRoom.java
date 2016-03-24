package fr.cobnet.api.games;

import fr.cobnet.api.events.Listener;
import fr.cobnet.api.locations.Cuboid;
import fr.cobnet.api.locations.LocationUtils;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 * Created by creart on 22/02/16.
 */
public class WaitingRoom extends Listener {

    private GamePlugin plugin;
    private Cuboid cuboid;
    private LocationUtils utils;
    private Location lobby;

    public WaitingRoom(GamePlugin plugin, Cuboid cuboid, Location lobby) {
        this.plugin = plugin;
        this.cuboid = cuboid;
        this.utils = LocationUtils.getInstance();
        this.lobby = lobby;
    }

    public void setCuboid(Cuboid cuboid) {
        this.cuboid = cuboid;
    }

    public void setLobby(Location lobby) {
        this.lobby = lobby;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Location from = event.getFrom();
        Location to = event.getTo();

        if (from.getBlockX() == to.getBlockX() && from.getBlockY() == to.getBlockY() && from.getBlockZ() == to.getBlockZ()) return;

        if (plugin.getStatus() == GameStatus.LOBBY) {

            if (utils.isBetween(cuboid.getRealFrom(), cuboid.getRealTo(), to)) return;

            event.getPlayer().teleport(lobby);

            return;
        }

        event.getHandlers().unregister(this);
    }

}
