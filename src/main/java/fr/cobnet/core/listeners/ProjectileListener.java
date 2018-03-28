package fr.cobnet.core.listeners;

import fr.cobnet.api.events.ArrowHitBlockEvent;
import fr.cobnet.api.events.ArrowHitEntityEvent;
import fr.cobnet.api.events.Listener;
import fr.cobnet.core.CobnetCore;
import net.minecraft.server.v1_12_R1.EntityArrow;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftArrow;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileHitEvent;

import java.lang.reflect.Field;

public class ProjectileListener extends Listener {

    @EventHandler
    public void onArrowHitBlock(ProjectileHitEvent e) {
        if(e.getEntityType() == EntityType.ARROW) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(CobnetCore.getInstance(), () -> {
                try {
                    EntityArrow arrow = ((CraftArrow) e.getEntity()).getHandle();

                    Field fieldX = arrow.getClass().getDeclaredField("d");
                    Field fieldY = arrow.getClass().getDeclaredField("e");
                    Field fieldZ = arrow.getClass().getDeclaredField("f");

                    fieldX.setAccessible(true);
                    fieldY.setAccessible(true);
                    fieldZ.setAccessible(true);

                    int x = fieldX.getInt(arrow);
                    int y = fieldY.getInt(arrow);
                    int z = fieldZ.getInt(arrow);

                    if(y != -1) {
                        Block block = e.getEntity().getWorld().getBlockAt(x, y, z);
                        Bukkit.getPluginManager().callEvent(new ArrowHitBlockEvent(block, (Arrow) e.getEntity()));
                    }
                } catch(Exception exc) {
                    exc.printStackTrace();
                }
            });
        }
    }

    @EventHandler
    public void onArrowHitEntity(EntityDamageByEntityEvent e) {
        if(e.getCause() == EntityDamageEvent.DamageCause.PROJECTILE) {
            Entity proj = e.getDamager();
            if(proj.getType() == EntityType.ARROW) {
                Arrow arr = (Arrow) e.getDamager();
                if(arr.getShooter() instanceof Entity) {
                    Entity shooter = (Entity) arr.getShooter();
                    Bukkit.getPluginManager().callEvent(new ArrowHitEntityEvent(shooter, e.getEntity(), arr));
                }
            }
        }
    }
}
