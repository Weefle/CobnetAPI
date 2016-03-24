package fr.cobnet.core.players;

import fr.cobnet.core.CobnetCore;
import org.bukkit.entity.Player;

/**
 * Créé par Creart le 03/01/2016.
 */
public class ScoreboardAnimationScheduler implements Runnable {

    private static final CobnetCore API = CobnetCore.getInstance();

    private CobPlayer cobPlayer;
    private int id;

    public ScoreboardAnimationScheduler(CobPlayer cobPlayer) {
        this.cobPlayer = cobPlayer;

        id = API.getServer().getScheduler().scheduleSyncRepeatingTask(API, this, 0, 20*10);
    }

    @Override
    public void run() {
        Player player = cobPlayer.getPlayer();

        if (player == null || !player.isOnline()) {
            API.getServer().getScheduler().cancelTask(id);
            return;
        }

        new ScoreboardAnimationSender(cobPlayer, cobPlayer.getPlayer()).runTaskTimer(API, 0, 1);
    }

}
