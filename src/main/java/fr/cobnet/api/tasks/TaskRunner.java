package fr.cobnet.api.tasks;

import fr.cobnet.api.misc.BasicFunctions;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

/**
 * Created by remi on 24/01/2016.
 */
public abstract class TaskRunner extends BasicFunctions implements Runnable {
    private int taskId = -1;

    public int getTaskId() {
        return taskId;
    }

    public void cancel() {
        Bukkit.getScheduler().cancelTask(this.taskId);
    }

    public BukkitTask runTaskLater(Plugin plugin, long delay) {
        BukkitTask task = Bukkit.getScheduler().runTaskLater(plugin, this, delay);
        this.taskId = task.getTaskId();
        return task;
    }

    public BukkitTask runTaskTimer(Plugin plugin, long start, long time) {
        BukkitTask task = Bukkit.getScheduler().runTaskTimer(plugin, this, start, time);
        this.taskId = task.getTaskId();
        return task;
    }
}
