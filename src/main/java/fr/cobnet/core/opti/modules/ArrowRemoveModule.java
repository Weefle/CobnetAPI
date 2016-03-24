package fr.cobnet.core.opti.modules;

import fr.cobnet.api.events.ArrowHitBlockEvent;
import fr.cobnet.api.events.Listener;
import fr.cobnet.core.CobnetCore;
import fr.cobnet.core.opti.OptimisationModule;
import org.bukkit.Bukkit;
import org.bukkit.entity.Arrow;
import org.bukkit.event.EventHandler;
import org.bukkit.scheduler.BukkitScheduler;

public class ArrowRemoveModule extends OptimisationModule {

    private static int defaultRemoveTime = 60;

    private int removeTime;

    public ArrowRemoveModule(String name) {
        super(name);
        removeTime = defaultRemoveTime;
    }

    protected CobnetCore plugin;
    protected BukkitScheduler scheduler;

    @Override
    public void enable() {
        super.enable();

        plugin = CobnetCore.getInstance();
        scheduler = Bukkit.getScheduler();

        Listener.register(plugin, this);
    }

    public int getRemoveTime() {
        return removeTime;
    }

    public void setRemoveTime(int time) {
        removeTime = time;
    }

    @EventHandler
    public void onArrowHitBlock(ArrowHitBlockEvent event) {
        if (isEnabled()) {
            Arrow arrow = event.getArrow();
            scheduler.runTaskLater(plugin, () -> {
                if (arrow.isValid() && !arrow.isDead()) {
                    arrow.remove();
                }
            }, removeTime);
        } else {
            event.getHandlers().unregister(this);
        }
    }

}
