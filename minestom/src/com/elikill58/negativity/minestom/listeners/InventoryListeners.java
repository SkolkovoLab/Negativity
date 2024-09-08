package com.elikill58.negativity.minestom.listeners;

import com.elikill58.negativity.api.events.EventManager;
import com.elikill58.negativity.api.events.inventory.InventoryAction;
import com.elikill58.negativity.api.events.inventory.InventoryClickEvent;
import com.elikill58.negativity.api.events.inventory.InventoryOpenEvent;
import com.elikill58.negativity.minestom.impl.entity.MinestomEntityManager;
import com.elikill58.negativity.minestom.impl.inventory.MinestomInventory;
import com.elikill58.negativity.minestom.impl.item.MinestomItemStack;
import net.minestom.server.event.Event;
import net.minestom.server.event.EventNode;
import net.minestom.server.event.inventory.InventoryCloseEvent;
import net.minestom.server.inventory.click.ClickType;

public class InventoryListeners {

	public InventoryListeners(EventNode<Event> e) {
		e.addListener(net.minestom.server.event.inventory.InventoryOpenEvent.class, this::onInventoryOpen);
		e.addListener(net.minestom.server.event.inventory.InventoryClickEvent.class, this::onInventoryClick);
	}
	
	public void onInventoryOpen(net.minestom.server.event.inventory.InventoryOpenEvent e) {
		if(e.isCancelled())
			return;
		InventoryOpenEvent event = new InventoryOpenEvent(MinestomEntityManager.getPlayer(e.getPlayer()));
		EventManager.callEvent(event);
		if(event.isCancelled())
			e.setCancelled(event.isCancelled());
	}
	
	public void onInventoryClick(net.minestom.server.event.inventory.InventoryClickEvent e) {
		EventManager.callEvent(new InventoryClickEvent(MinestomEntityManager.getPlayer(e.getPlayer()), getAction(e.getClickType()), e.getSlot(), new MinestomItemStack(e.getClickedItem()), new MinestomInventory(e.getInventory())));
	}
	
	private InventoryAction getAction(ClickType type) {
        return switch (type) {
			case DROP -> InventoryAction.DROP;
			case DOUBLE_CLICK -> InventoryAction.DOUBLE;
			case RIGHT_CLICK -> InventoryAction.RIGHT;
			case LEFT_CLICK -> InventoryAction.LEFT;
			case SHIFT_CLICK -> InventoryAction.RIGHT_SHIFT;
			// InventoryAction.LEFT_SHIFT
			// InventoryAction.CREATIVE
			// InventoryAction.MIDDLE
			default -> InventoryAction.UNKNOWN;
        };
	}
	
	public void onInventoryClose(InventoryCloseEvent e) {
		EventManager.callEvent(new com.elikill58.negativity.api.events.inventory.InventoryCloseEvent(MinestomEntityManager.getPlayer(e.getPlayer()), new MinestomInventory(e.getInventory())));
	}
}
