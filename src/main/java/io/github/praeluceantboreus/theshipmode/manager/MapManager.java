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
		File worldTarget = new File(plugin.getDataFolder().getParentFile().getParentFile() + "/" + plugin.getConfig().getString("main.maps.temp") + "/" + idTime);
		try
		{
			Files.copy(new File(plugin.getDataFolder().getParentFile().getParentFile() + "/" + plugin.getConfig().getString("main.maps.container") + "/" + id), worldTarget);
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WorldCreator wc = new WorldCreator(plugin.getDataFolder().getParentFile().getParentFile().toURI().relativize(worldTarget.toURI()).toString());
		World world = wc.createWorld();
		ConfigurationSection mapConf = plugin.getConfig().getConfigurationSection("maps." + id);
		TheShipMap map = TheShipMap.deserialize(mapConf);
		worlds.put(world, map);
		return world;
	}
}
