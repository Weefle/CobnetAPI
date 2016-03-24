package fr.cobnet.api.events;

import fr.cobnet.core.players.CobPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerDataLoadEvent extends Event {
	
	private static final HandlerList handlers = new HandlerList();
	private Player player;

	public Player getPlayer() {
		return player;
	}

	private CobPlayer cobPlayer;

	public CobPlayer getCobPlayer() {
		return cobPlayer;
	}

	public PlayerDataLoadEvent(CobPlayer cobPlayer) {
		this.player = cobPlayer.getPlayer();
		this.cobPlayer = cobPlayer;
	}
	
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}