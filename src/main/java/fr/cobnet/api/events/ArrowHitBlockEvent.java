package fr.cobnet.api.events;

import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockEvent;

public class ArrowHitBlockEvent extends BlockEvent {
	private static final HandlerList handlers = new HandlerList();

	private Arrow arrow;

	public Arrow getArrow() {
		return arrow;
	}

	public ArrowHitBlockEvent(Block block, Arrow arrow) {
		super(block);
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