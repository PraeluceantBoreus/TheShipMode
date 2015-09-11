package io.github.praeluceantboreus.theshipmode.manager.map;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;

public final class TheShipMap
{
	private HashSet<Camera> cameras;
	// Also includes inspectors!
	private HashSet<Container> containers;
	private Location jail;
	private ArrayList<Location> spawns;

	private TheShipMap()
	{

	}

	@SuppressWarnings("unchecked")
	public static TheShipMap deserialize(ConfigurationSection cs)
	{
		HashSet<Camera> cameras = new HashSet<>();
		HashSet<Container> containers = new HashSet<>();
		ArrayList<Location> spawns = new ArrayList<>();
		for (Object cam : cs.getList("cameras"))
			if (cam instanceof ConfigurationSection)
				cameras.add(Camera.deserialize((ConfigurationSection) cam));
		for (Object cam : cs.getList("inspectors"))
			if (cam instanceof ConfigurationSection)
				cameras.add(Inspector.deserialize((ConfigurationSection) cam));
		Location jail = Location.deserialize(cs.getConfigurationSection("jail").getValues(true));
		for (Map<?, ?> loc : cs.getMapList("spawnpoints"))
			spawns.add(Location.deserialize((Map<String, Object>) loc));
		TheShipMap ret = new TheShipMap();
		ret.cameras = cameras;
		ret.containers = containers;
		ret.jail = jail;
		ret.spawns = spawns;
		return ret;
	}

	@SuppressWarnings("unchecked")
	public HashSet<Camera> getCameras()
	{
		return (HashSet<Camera>) cameras.clone();
	}

	@SuppressWarnings("unchecked")
	public HashSet<Container> getContainers()
	{
		return (HashSet<Container>) containers.clone();
	}

	public Location getJail()
	{
		return jail.clone();
	}
}
