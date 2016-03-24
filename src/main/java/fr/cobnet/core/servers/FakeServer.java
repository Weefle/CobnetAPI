package fr.cobnet.core.servers;

import fr.cobnet.api.events.PlayerDataLoadEvent;
import fr.cobnet.api.i18n.Lang;
import fr.cobnet.core.CobnetCore;
import fr.cobnet.core.players.CobPlayer;
import fr.cobnet.core.players.Rank;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class FakeServer extends GameServer {
    private File datastore = new File(getDataStore(), "datastore/");
    private File cobplayerStore = new File(datastore.getAbsolutePath(), "cobplayers.yml");
    private FileConfiguration config;

    public void enable() {
        register(CobnetCore.getInstance(), this);
        datastore.mkdirs();
        config = YamlConfiguration.loadConfiguration(cobplayerStore);
        Bukkit.getOnlinePlayers().forEach(this::getCobPlayer);

        GameServer.setInstance(this);
    }

    public void disable() {
        CobPlayer.all().forEach(this::storeCobPlayer);
    }

    public void storeCobPlayer(CobPlayer player) {
        config.set("players."+ player.getUniqueId().toString() +".coins", player.getCoins());
        config.set("players."+ player.getUniqueId().toString() +".rank", player.getRealRank().getId());
        config.set("players."+ player.getUniqueId().toString() +".displayRank", player.getDisplayRank().getId());
        config.set("players."+ player.getUniqueId().toString() +".lang", player.getLang().name());
        try {
            if(!cobplayerStore.exists()) {
                Files.createFile(Paths.get(cobplayerStore.getAbsolutePath()));
            }
            config.save(cobplayerStore.getAbsolutePath());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public CobPlayer getCobPlayer(Player player) {
        FileConfiguration config = YamlConfiguration.loadConfiguration(cobplayerStore);
        if(config.get("players."+ player.getUniqueId().toString()) == null) {
            CobPlayer cobPlayer = new CobPlayer(player);
            storeCobPlayer(cobPlayer);
            return cobPlayer;
        }
        int coins = config.getInt("players."+ player.getUniqueId().toString() +".coins");
        Rank rank = Rank.getById(config.getInt("players."+ player.getUniqueId().toString() +".rank"));
        Rank displayRank = Rank.getById(config.getInt("players."+ player.getUniqueId().toString() +".displayRank"));
        Lang lang = Lang.valueOf(config.getString("players."+ player.getUniqueId().toString() +".lang"));
        return new CobPlayer(player, coins, rank, displayRank, lang);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onJoin(PlayerLoginEvent e) {
        Player player = e.getPlayer();
        Bukkit.getPluginManager().callEvent(new PlayerDataLoadEvent(getCobPlayer(player)));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        storeCobPlayer(CobPlayer.findByPlayer(player));
    }

    private File getDataStore() {
        File dataFolder = CobnetCore.getInstance().getDataFolder();
        String[] path = dataFolder.getAbsolutePath().split("/");
        String newPath = "";
        for(int i = 0; i < path.length - 2; i++) {
            newPath = newPath +"/"+ path[i];
        }
        return new File(newPath);
    }

    @Override
    public UUID getPlayersParty(UUID player) {
        return null;
    }

    @Override
    public boolean hasParty(UUID player) {
        return false;
    }

    @Override
    public Set<UUID> getPlayersInParty(UUID party) {
        return new HashSet<>();
    }

    @Override
    public Set<UUID> getPlayersInPartyByPlayer(UUID player) {
        return new HashSet<>();
    }

    @Override
    public boolean isLeaderOfParty(UUID player) {
        return false;
    }

    @Override
    public UUID getPartyLeader(UUID party) {
        return null;
    }

    @Override
    public UUID getPlayersPartyLeader(UUID player) {
        return null;
    }

    @Override
    public List<UUID> getFriends(UUID player) {
        return new ArrayList<>();
    }

    @Override
    public boolean areFriends(UUID first, UUID other) {
        return false;
    }

    @Override
    public List<UUID> getOnlineFriends(UUID player) {
        return new ArrayList<>();
    }

    @Override
    public Set<Set<UUID>> getOnlineParties() {
        return new HashSet<>();
    }
}
