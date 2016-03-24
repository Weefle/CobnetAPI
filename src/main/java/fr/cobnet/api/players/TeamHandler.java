package fr.cobnet.api.players;

import com.google.common.collect.Sets;
import fr.cobnet.core.CobnetCore;
import fr.cobnet.api.events.PartyJoinServerEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

/** @TODO */
public class TeamHandler {
	
	private static TeamHandler instance;
	
	public static TeamHandler getInstance() {
		return instance == null ? instance = new TeamHandler() : instance;
	}
	
	private boolean created;
	private int teamCount;
	private int teamSize;
	private Scoreboard scoreboard;
	private Set<Team> teams;
	
	private TeamHandler() {
		scoreboard = null;
		teamCount = 6;
		teamSize = 5;
		teams = Sets.newHashSet();
	}
	
	public void enable() {
		if(created) return;
		byte loops = 0;
		for (TeamColour team : TeamColour.values()) {
			loops++;
			if (loops >= teamCount) {
				Team t = scoreboard.registerNewTeam(team.getTeamName());
				t.setPrefix(t.getPrefix());
				teams.add(t);
			}
		}
		
		Bukkit.getPluginManager().registerEvents(new Listener() {
			@SuppressWarnings("deprecation")
			@EventHandler
			public void onPartyJoin(PartyJoinServerEvent event) {
				/** @TODO */
				if (true) {
					event.getHandlers().unregister(this);
					return;
				}
				
				Iterator<Team> iterator = teams.iterator();
				Team team = iterator.next();
				for (Player player : event.getPlayers()) {
					if (team.getPlayers().size() >= teamSize) {
						if(!iterator.hasNext()) return;
						team = iterator.next();
					}
					team.addPlayer(player);
				}
				
			}
		}, CobnetCore.getInstance());
		
		created = true;
	}
	
	public Set<Team> getTeams() {
		return Collections.unmodifiableSet(teams);
	}
	
	public void setTeamCount(int teams) {
		teamCount = teams;
	}

	public int getTeamCount() {
		return this.teamCount;
	}
	
	public void setTeamSize(int size) {
		teamSize = size;
	}
	
}
