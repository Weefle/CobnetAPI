package fr.cobnet.api.gui;

import fr.cobnet.api.events.Listener;
import fr.cobnet.core.CobnetCore;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.stream.Stream;

/**
 * Created by creart on 22/02/16.
 */
public class GuiListener extends Listener {

    static
    {
        Listener.register(CobnetCore.getInstance(), new GuiListener());
    }

    @SuppressWarnings("deprecation")
	@EventHandler
    public void onItemClick(PlayerInteractEvent event) {
        if (event.getPlayer().getItemInHand() == null || event.getPlayer().getItemInHand().getType() == Material.AIR) return;
        if (InventoryHandle.getRawList().size() > 500) {
            react(InventoryHandle.getRawList().parallelStream(), event);
        } else {
            if (InventoryHandle.getRawList().isEmpty()) return;
            react(InventoryHandle.getRawList().stream(), event);
        }
    }

    @EventHandler
    public void onClickItem(InventoryClickEvent event) {
        if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR) return;

        if (InventoryHandle.getRawList().size() > 500) {
            click(InventoryHandle.getRawList().parallelStream(), event);
        } else {
            if (InventoryHandle.getRawList().isEmpty()) return;
            click(InventoryHandle.getRawList().stream(), event);
        }
    }

    @SuppressWarnings("deprecation")
	private void react(Stream<InventoryHandle> stream, PlayerInteractEvent event) {
        stream.filter(inventory -> inventory.isValidOpener(event.getPlayer().getItemInHand())).forEach(inventoryHandle -> {
            if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
                inventoryHandle.onLeftClickItem(event.getPlayer());
            } else {
                inventoryHandle.onRightClickItem(event.getPlayer());
            }
        });
    }

    private void click(Stream<InventoryHandle> stream, InventoryClickEvent event) {
        stream.filter(inventory -> inventory.getRawItems().containsKey(event.getSlot()) && inventory.getRawItems().containsValue(event.getCurrentItem()))
                .forEach(inventory ->
                        inventory.onItemClick((Player)event.getWhoClicked(),
                                event.getCurrentItem(),
                                event.isShiftClick(),
                                event.isRightClick(),
                                event.isLeftClick(),
                                event.getAction()));
    }

}
