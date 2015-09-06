package io.github.praeluceantboreus.theshipmode.main;

import io.github.praeluceantboreus.theshipitems.main.TheShipItemsPlugin;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class TheShipModePlugin extends JavaPlugin
{
	private boolean useTSI;
	private TheShipItemsPlugin tsi;

	@Override
	public void onEnable()
	{
		Plugin tsiPlugin = getServer().getPluginManager().getPlugin("TheShipItems");
		tsi = null;
		if (tsiPlugin instanceof TheShipItemsPlugin)
			tsi = (TheShipItemsPlugin) tsiPlugin;
		useTSI = tsi != null;
		super.onEnable();
	}
}
