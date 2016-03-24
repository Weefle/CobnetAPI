package fr.cobnet.core.players;

import fr.cobnet.core.CobnetCore;
import org.bukkit.Bukkit;

public final class PlayerTasks {

    private PlayerTasks() {
    }

    private static boolean createdGradeTask;
    private static int gradeTaskId;

    public static void enableGradeTask() {
        if (!createdGradeTask) {
            gradeTaskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(CobnetCore.getInstance(), new GradeTask(), 0, 20);
            createdGradeTask = true;
        }
    }

    public static void disableGradeTask() {
        if (createdGradeTask) {
            Bukkit.getScheduler().cancelTask(gradeTaskId);
            createdGradeTask = false;
        }
    }

}
