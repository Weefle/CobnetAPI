package fr.cobnet.api.events;

import fr.cobnet.core.players.CobPlayer;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by remi on 24/01/2016.
 */
public class PlayerReconnectEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    private CobPlayer player;

    public CobPlayer getPlayer() {
        return player;
    }

    public PlayerReconnectEvent(CobPlayer player) {
        this.player = player;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
