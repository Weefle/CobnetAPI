package fr.cobnet.api.players;

import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

@SuppressWarnings("deprecation")
public class TeamUtils {

	/**
	 * Retourne vrai, si les deux joueurs sont dans la m�me �quipe
	 * @param p1 > Joueur 1
	 * @param p2 > Joueur 2
	 * @return
	 */
	public static boolean areInSameTeam(Player p1, Player p2) {
		Scoreboard board1 = p1.getScoreboard();
		Scoreboard board2 = p2.getScoreboard();
		if(board1 != null && board2 != null && board1 == board2) {
			Team t1 = board1.getPlayerTeam(p1);
			Team t2 = board2.getPlayerTeam(p2);
			return isTheSameTeam(t1, t2);
		}
		return false;
	}
	
	/**
	 * Retourne vrai, si les deux teams sont, en fait, une seule
	 * @param teamA > Team A
	 * @param teamB > Team B
	 * @return
	 */
	public static boolean isTheSameTeam(Team teamA, Team teamB) {
		if(teamA == null || teamB == null) return false;
		return teamA.getName().equals(teamB.getName());
	}
	
	/**
	 * Retourne vrai, si le joueur a une team
	 * @param player
	 * @return
	 */
	public static boolean hasTeam(Player player) {
		Scoreboard scoreboard = player.getScoreboard();
		if(scoreboard == null) return false;
		return scoreboard.getPlayerTeam(player) != null;
	}
	
	/**
	 * Retourne l'équipe du joueur (null s'il n'en a pas)
	 * @param player
	 * @return
	 */
	public static Team getPlayerTeam(Player player) {
		if(!hasTeam(player)) return null;
		return player.getScoreboard().getPlayerTeam(player);
	}
	
	/**
	 * Retire le joueur de son équipe
	 * @param player
	 */
	public static void removePlayerFromTeam(Player player) {
		if(hasTeam(player)) getPlayerTeam(player).removePlayer(player);
	}
	
	/**
	 * Ajoute un joueur à l'équipe
	 * @param p
	 * @param t
	 */
	public static void addPlayerToTeam(Player p, Team t) {
		t.addPlayer(p);
	}
	
}