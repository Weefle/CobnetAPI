package fr.cobnet.core.opti.modules;

import fr.cobnet.core.CobnetCore;
import fr.cobnet.core.opti.OptimisationModule;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.scheduler.BukkitRunnable;

public class DropSuppresserModule extends OptimisationModule {

    private static int defaultFrequenceTime = 60 * 5;

    private boolean alert;
    private int frequenceTime;
    private int timeLeft;

    public DropSuppresserModule(String name) {
        super(name);

        frequenceTime = defaultFrequenceTime;
        timeLeft = frequenceTime;
    }

    public boolean doAlert() {
        return alert;
    }

    public int getFrequenceTime() {
        return frequenceTime;
    }

    @Override
    public void enable() {
        super.enable();
        new BukkitRunnable() {

            @Override
            public void run() {
                if (isEnabled()) {
                    timeLeft--;
                    if (timeLeft <= 0) {
                        @SuppressWarnings("unused")
						int removed = 0;
                        for (World world : Bukkit.getWorlds()) {
                            for (Entity ent : world.getEntities()) {
                                if (ent.getType() == EntityType.DROPPED_ITEM || (ent.getType() == EntityType.ARROW && ((Arrow) ent).isOnGround())) {
                                    ent.remove();
                                    removed++;
                                }
                            }
                        }
                        return;
                    }
                } else {
                    timeLeft = frequenceTime;
                    cancel();
                }
            }

        }.runTaskTimerAsynchronously(CobnetCore.getInstance(), 20, 20);
    }

}
