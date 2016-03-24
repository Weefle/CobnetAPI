package fr.cobnet.api.events;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;

public class ArrowHitEntityEvent extends EntityEvent {
	private static final HandlerList handlers = new HandlerList();

    private Arrow arrow;

    public Arrow getArrow() {
        return arrow;
    }

	private Entity shooter;

    public Entity getShooter() {
        return shooter;
    }

    public ArrowHitEntityEvent(Entity shooter, Entity entity, Arrow arrow) {
		super(entity);
		this.shooter = shooter;
	    this.arrow = arrow;
    }

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}