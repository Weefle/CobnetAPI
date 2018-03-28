package fr.cobnet.core.players;

import fr.cobnet.api.players.TeamUtils;
import fr.cobnet.core.CobnetCore;
import java.util.Map;
import net.minecraft.server.v1_12_R1.PacketPlayOutScoreboardTeam;
import net.minecraft.server.v1_12_R1.Scoreboard;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

class GradeTask implements Runnable {

    private int task;
    private boolean use;
    //private Scoreboard scoreboard;
    private Map<Integer, Team> teams;
    private Scoreboard scoreboard;

    public GradeTask() {
        /*teams = Maps.newLinkedHashMap();
        scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        for (Rank rank : Rank.values()) {
            Team t = scoreboard.registerNewTeam(rank.name().toLowerCase());
            t.setPrefix(rank.getFrenchPrefix());
            teams.put(rank.getId(), t);
        }*/
        setScoreboard(new Scoreboard());

        task = Bukkit.getScheduler().scheduleSyncRepeatingTask(CobnetCore.getInstance(), this, 0, 20);
        use = true;
    }

    @Override
    public void run() {
        /* Si on veut s'en servir */
        if (use) {
            /* Pour tous les joueurs */
            /*for (CobPlayer player : CobPlayer.all()) {
				/* On check s'il a son grade de chargé */
            /**
             @TODO if(!player.isDisplayRankLoaded())
             {
             player.getPlayer().setScoreboard(scoreboard);
             Team team = teams.get(player.getDisplayRank().getId());
             TeamUtils.addPlayerToTeam(player.getPlayer(), team);
             player.setDisplayRankLoaded(true);
             }

             }*/


            CobPlayer.all().stream().forEach(player -> {

                if (!player.hasDisplayRankLoaded()) {
                    @SuppressWarnings("unused")
					PacketPlayOutScoreboardTeam team = new PacketPlayOutScoreboardTeam();

                }

            });
        } else {
			/* On stop la task si on s'en sert pas */
            stop();
			/* On enlève tous les grades */
            for (Player player : Bukkit.getOnlinePlayers()) {
                TeamUtils.removePlayerFromTeam(player);
            }
            use = false;
        }
    }

    private void stop() {
        Bukkit.getScheduler().cancelTask(task);
    }

	public Scoreboard getScoreboard() {
		return scoreboard;
	}

	public void setScoreboard(Scoreboard scoreboard) {
		this.scoreboard = scoreboard;
	}

	public Map<Integer, Team> getTeams() {
		return teams;
	}

	public void setTeams(Map<Integer, Team> teams) {
		this.teams = teams;
	}

}
