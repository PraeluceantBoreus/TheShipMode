package io.github.praeluceantboreus.theshipmode.listener;

import io.github.praeluceantboreus.theshipmode.main.TheShipModePlugin;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;

public class MapChooseListener implements Listener
{
	private TheShipModePlugin plugin;
	
	@EventHandler
	public void onMapList(InventoryOpenEvent ioe)
	{
		if(ioe.getPlayer().hasMetadata("listmaps"))
		{
			ioe.getPlayer().removeMetadata("listmaps", plugin);
		}
	}
}
