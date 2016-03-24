package fr.cobnet.api.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * @TODO
 * Created by remi on 24/01/2016.
 */
public class NpcDespawnEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
