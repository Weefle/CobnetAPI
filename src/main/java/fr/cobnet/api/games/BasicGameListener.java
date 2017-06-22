package fr.cobnet.api.games;

import fr.cobnet.api.events.Listener;
import fr.cobnet.api.events.PlayerReconnectEvent;
import fr.cobnet.api.i18n.I18n;
import fr.cobnet.api.i18n.Lang;
import fr.cobnet.api.nms.TablistBuilder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerListPingEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by remi on 24/01/2016.
 */
public class BasicGameListener extends Listener {
    private GamePlugin plugin;
    protected List<String> reconnectPlayers;

    public BasicGameListener(GamePlugin plugin) {
        this.plugin = plugin;
        this.reconnectPlayers = new ArrayList<>();
    }

    /*
    Haute priorité, parce que le data load sera appelé avant le join désormais => besoin de données tout de suite
    ignoreCancelled = true, car si l'event est annulé par le back-end, on laisse tomber
     */
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onLogin(PlayerLoginEvent e) {
        Player player = e.getPlayer();

        if(plugin.getStatus() != GameStatus.LOBBY && !plugin.isAuthorizeSpectators() && !player.isOp()) {
            if(reconnectPlayers.contains(player.getName())) {
                if(plugin.canReconnect(player)) {
                    Bukkit.getPluginManager().callEvent(new PlayerReconnectEvent(getCobPlayer(player)));
                }

                else {
                    e.disallow(PlayerLoginEvent.Result.KICK_OTHER, I18n.tl(getCobPlayer(player).getLang(), "cant_recon"));
                }
            }
            else {
                e.disallow(PlayerLoginEvent.Result.KICK_OTHER, I18n.tl(getCobPlayer(player).getLang(), "nospec"));
            }
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        e.setJoinMessage("");
        getAPI().broadcast("game.join");
        Lang lang = getCobPlayer(e.getPlayer()).getLang();
        new TablistBuilder().withHeader(I18n.tl(lang, "tab_header")).withFooter(I18n.tl(lang, "tab_footer")).sendTo(e.getPlayer());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        //e.setQuitMessage(plugin.getFrenchPrefix() + player.getName() +" vient de quitter la partie §e(");
        e.setQuitMessage("");
        getAPI().broadcast("game.quit", plugin.getPrefix());

        if (plugin.canReconnect(player)) {
            reconnectPlayers.add(player.getName());
        }
    }

    @EventHandler
    public void onServerPing(ServerListPingEvent event) {
        event.setMaxPlayers(50);
        StringBuilder builder = new StringBuilder("§cServeur de test §6Cobnet\n");
        builder.append("\n");

        switch (plugin.getStatus()) {
            case LOBBY:
                builder.append("§aLa partie n'a pas encore commencé, venez vite !");
                break;
            case PLAYING:
                if (plugin.isAuthorizeSpectators()) {
                    builder.append("§ePartie en cours | §aVous pouvez la rejoindre en tant que spectateur");
                } else {
                    builder.append("§ePartie en cours | §cVous ne pouvez la rejoindre en tant que spectateur");
                }
                break;
            case FINISHED:
                builder.append("§aLa partie vient de se terminer !");
                break;
        }

        event.setMotd(builder.toString());
    }

}
