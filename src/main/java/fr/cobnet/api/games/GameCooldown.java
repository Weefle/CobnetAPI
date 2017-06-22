package fr.cobnet.api.games;

import fr.cobnet.api.nms.TitleBuilder;
import fr.cobnet.api.players.SoundUtils;
import fr.cobnet.api.tasks.TaskRunner;
import fr.cobnet.core.CobnetCore;
import org.bukkit.Bukkit;
import org.bukkit.Sound;

/**
 * Created by remi on 24/01/2016.
 */
public class GameCooldown implements Runnable {
    private GamePlugin plugin;
    private TaskRunner runner;

    public TaskRunner getRunner() {
        return runner;
    }

    public void setRunner(TaskRunner runner) {
        this.runner = runner;
    }

    private int untilStart = 90;

    public int getUntilStart() {
        return untilStart;
    }

    public void setUntilStart(int untilStart) {
        this.untilStart = untilStart;
    }

    private int taskId;

    public int getTaskId() {
        return taskId;
    }

    public GameCooldown(GamePlugin plugin, TaskRunner runner) {
        this.plugin = plugin;
        this.runner = runner;
        this.taskId = Bukkit.getScheduler().runTaskTimer(plugin, this, 0L, 20L).getTaskId();
    }

    @Override
    public void run() {
        if(plugin.canStart()) {
            if(untilStart <= 0) {
                Bukkit.getScheduler().cancelTask(this.taskId);
                this.plugin.setStatus(GameStatus.PLAYING);
                TitleBuilder title = new TitleBuilder()
                        .withSubtitle("game.start");
                Bukkit.getOnlinePlayers().forEach(title::sendTo);

                if(this.runner != null)
                    this.runner.run();
                else
                    plugin.getLogger().warning("Aucune TaskRunner n'a été définie dans le GameCooldown.");

                plugin.setGameStartTime(System.currentTimeMillis());
                return;
            }

            if ((untilStart % 10 == 0 && untilStart < 31) || untilStart % 30 == 0) {
                CobnetCore.getInstance().broadcast("game.countdown1", untilStart + "");
                SoundUtils.playSoundForAll(Sound.SUCCESSFUL_HIT, 10, untilStart/2);
            }

            else if(this.untilStart <= 5) {
                TitleBuilder title = new TitleBuilder()
                        .withTitle((untilStart <= 3 ? "§c" : "§e") + untilStart)
                        .withSubtitle("game.countdown2");
                title.sendTo(Bukkit.getOnlinePlayers());
                SoundUtils.playSoundForAll(Sound.CLICK, 10, untilStart);
            }

            untilStart--;
        }
        else {
            untilStart = 90;
        }
    }
}
