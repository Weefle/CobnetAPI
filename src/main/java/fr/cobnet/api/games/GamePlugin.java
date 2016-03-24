package fr.cobnet.api.games;

import com.google.common.base.Preconditions;
import fr.cobnet.api.events.Listener;
import fr.cobnet.api.i18n.I18n;
import fr.cobnet.api.locations.Cuboid;
import fr.cobnet.core.players.CobPlayer;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by remi on 24/01/2016.
 */
public class GamePlugin extends JavaPlugin {
    private static GamePlugin instance;

    public static GamePlugin getInstance() {
        return instance;
    }

    private String minigameName;
    private GameStatus status;
    private GameCooldown gameCooldown;
    private boolean authorizeSpectators;
    private BasicGameListener basicGameListener;
    private long gameStartTime;
    private boolean canPlayersReconnect;
    private String prefix, errorPrefix, successPrefix;
    private WaitingRoom waitingRoom;

    public GamePlugin(String minigameName) {
        instance = this;

        this.minigameName = minigameName;
        this.authorizeSpectators = true;
        this.status = GameStatus.LOBBY;
    }

    @Override
    public void onEnable() {
        this.basicGameListener = new BasicGameListener(this);
        this.gameCooldown = new GameCooldown(this, null);
        Listener.register(this, this.basicGameListener);
    }

    public String getPrefix() {
        return prefix == null ? prefix = "§6"+ this.minigameName +" §7» " : prefix;
    }

    public String getErrorPrefix() {
        return errorPrefix == null ? errorPrefix = this.getPrefix() +"§c" : errorPrefix;
    }

    public String getSuccessPrefix() {
        return successPrefix == null ? successPrefix = this.getPrefix() +"§a" : successPrefix;
    }

    public boolean canStart() {
        return true;
    }

    public boolean canReconnect(Player player) {
        return true;
    }

    public void broadcast(String key, String... values) {
        Preconditions.checkNotNull(key);

        CobPlayer.all().stream().forEach(player -> player.getPlayer().sendMessage(I18n.tl(player.getLang(), key, values)));
    }

    public void setWaitingRoom(Cuboid cuboid, Location lobby) {
        if (waitingRoom == null)
            waitingRoom = new WaitingRoom(this, cuboid, lobby);
    }

    public String getMinigameName() {
        return minigameName;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    public GameCooldown getGameCooldown() {
        return gameCooldown;
    }

    public boolean isAuthorizeSpectators() {
        return authorizeSpectators;
    }

    public void setAuthorizeSpectators(boolean authorizeSpectators) {
        this.authorizeSpectators = authorizeSpectators;
    }

    public BasicGameListener getBasicGameListener() {
        return basicGameListener;
    }

    public long getGameStartTime() {
        return gameStartTime;
    }

    void setGameStartTime(long gameStartTime) {
        this.gameStartTime = gameStartTime;
    }

    public boolean isCanPlayersReconnect() {
        return canPlayersReconnect;
    }

    public void setCanPlayersReconnect(boolean canPlayersReconnect) {
        this.canPlayersReconnect = canPlayersReconnect;
    }

}
