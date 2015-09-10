package io.github.praeluceantboreus.theshipmode.listener;

import io.github.praeluceantboreus.theshipmode.main.TheShipModePlugin;
import io.github.praeluceantboreus.theshipmode.manager.MapManager;

import java.util.ArrayList;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;

public class MapChooseListener implements Listener
{
	private TheShipModePlugin plugin;
	private MapManager mapmanager;

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
			ice.setCancelled(true);
			Inventory selection = ice.getView().getTopInventory();
			ArrayList<?> slots = (ArrayList<?>) ice.getWhoClicked().getMetadata("maps").get(0).value();
			String mapId = slots.get(ice.getSlot()).toString();
		}
	}

	@EventHandler
	public void onWorldClick(InventoryClickEvent ice)
	{
		if (ice.getWhoClicked().hasMetadata("listworlds"))
		{
			ice.setCancelled(true);
			String worldId = ice.getCurrentItem().getItemMeta().getDisplayName();
			World world = plugin.getServer().getWorld(worldId);
			mapmanager.movePlayerToMap((Player) ice.getWhoClicked(), world);
		}
	}
}
