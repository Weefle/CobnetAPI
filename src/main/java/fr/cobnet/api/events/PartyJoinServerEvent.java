package fr.cobnet.api.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.Set;

public class PartyJoinServerEvent extends Event {
	
	private static final HandlerList handlers = new HandlerList();

	private final Set<Player> players;

	public Set<Player> getPlayers() {
		return players;
	}

	public PartyJoinServerEvent(Set<Player> players) {
		this.players = players;
	}
	
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}
