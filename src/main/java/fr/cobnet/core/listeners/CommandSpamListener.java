package fr.cobnet.core.listeners;

import fr.cobnet.api.events.Listener;
import fr.cobnet.core.players.CobPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandSpamListener extends Listener {

	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent e) {
		
		long current = now();
		
		CobPlayer cob = getCobPlayer(e.getPlayer());
		if(cob == null) return;
		
		long time = current - cob.getNextCommand();
		
		if(time < 1000) {
			e.setCancelled(true);
			e.getPlayer().sendMessage("§cVeuillez éviter de spammer les commandes, merci.");
			return;
		}
		
		cob.setNextCommand(current);
	}
	
}