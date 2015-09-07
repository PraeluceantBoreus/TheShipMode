package io.github.praeluceantboreus.theshipmode.manager.map;

import io.github.praeluceantboreus.theshipmode.main.TheShipModePlugin;

import java.util.HashSet;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;

public class TheShipMap
{
	private HashSet<Camera> cameras;
	// Also includes inspectors!
	private HashSet<Container> containers;
	private Location jail;
	private World map;
	private TheShipModePlugin plugin;

	public TheShipMap(TheShipModePlugin plugin, World map)
	{
		this.plugin = plugin;
		this.map = map;

		cameras = new HashSet<>();
		containers = new HashSet<>();

		for (Object cam : plugin.getConfig().getList("maps." + map.getName() + ".cameras"))
			if (cam instanceof ConfigurationSection)
				cameras.add(Camera.deserialize((ConfigurationSection) cam));
		for (Object cam : plugin.getConfig().getList("maps." + map.getName() + ".inspectors"))
			if (cam instanceof ConfigurationSection)
				cameras.add(Inspector.deserialize((ConfigurationSection) cam));
		jail = Location.deserialize(plugin.getConfig().getConfigurationSection("maps." + map.getName() + ".jail").getValues(true));
	}
}
