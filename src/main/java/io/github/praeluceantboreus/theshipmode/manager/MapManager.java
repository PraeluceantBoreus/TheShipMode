package io.github.praeluceantboreus.theshipmode.manager;

import io.github.praeluceantboreus.theshipmode.main.TheShipModePlugin;
import io.github.praeluceantboreus.theshipmode.manager.map.TheShipMap;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.configuration.ConfigurationSection;

import com.google.common.io.Files;

public class MapManager
{
	private TheShipModePlugin plugin;
	private HashMap<World, TheShipMap> worlds;

	public World loadMap(String id)
	{
		long idTime = System.currentTimeMillis();
		try
		{
			Files.copy(new File(plugin.getDataFolder().getParentFile().getParentFile() + "/" + plugin.getConfig().getString("main.maps.container") + "/" + id), new File(plugin.getDataFolder().getParentFile().getParentFile() + "/" + plugin.getConfig().getString("main.maps.temp") + "/" + id));
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WorldCreator wc = new WorldCreator(idTime + "");
		ConfigurationSection mapConf = plugin.getConfig().getConfigurationSection("maps." + id);
		TheShipMap map = TheShipMap.deserialize(mapConf);
		return null;
	}
}
