package fr.cobnet.api.events;

import fr.cobnet.api.npc.Npc;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by remi on 24/01/2016.
 */
public class NpcDamagedEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    private Player player;

    public Player getPlayer() {
        return player;
    }

    private Npc victim;

    public Npc getVictim() {
        return victim;
    }

    public NpcDamagedEvent(Player player, Npc victim) {
        this.player = player;
        this.victim = victim;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
