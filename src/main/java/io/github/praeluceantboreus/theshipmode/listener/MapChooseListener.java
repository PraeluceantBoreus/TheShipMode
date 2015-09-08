package io.github.praeluceantboreus.theshipmode.listener;

import java.util.ArrayList;

import io.github.praeluceantboreus.theshipmode.main.TheShipModePlugin;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;

public class MapChooseListener implements Listener
{
	private TheShipModePlugin plugin;

	@EventHandler
	public void onMapList(InventoryOpenEvent ioe)
	{
		if (ioe.getPlayer().hasMetadata("listmaps"))
		{
			ioe.getPlayer().removeMetadata("listmaps", plugin);
		}
	}

	@EventHandler
	public void onMapClick(InventoryClickEvent ice)
	{
		if (ice.getWhoClicked().hasMetadata("listmaps"))
		{
			Inventory selection = ice.getView().getTopInventory();
			ice.setCancelled(true);
			ArrayList<?> slots = (ArrayList<?>) ice.getWhoClicked().getMetadata("maps").get(0).value();
			String mapId = slots.get(ice.getSlot()).toString();
		}
	}
}
