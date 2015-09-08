package io.github.praeluceantboreus.theshipmode.manager;

import io.github.praeluceantboreus.theshipmode.main.TheShipModePlugin;
import io.github.praeluceantboreus.theshipmode.manager.map.TheShipMap;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.Callable;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.LazyMetadataValue;

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

	public void listMaps(Player player)
	{
		ConfigurationSection maps = plugin.getConfig().getConfigurationSection("maps");
		int amount = maps.getValues(false).size();
		if (amount % 9 != 0)
			amount = (((int) (amount / 9)) + 1) * 9;
		Inventory mapList = Bukkit.createInventory(null, amount);
		mapList.setMaxStackSize(1);
		final ArrayList<String> slotIds = new ArrayList<>();
		for (String mapId : maps.getStringList(""))
		{
			ConfigurationSection cs = maps.getConfigurationSection(mapId);
			ItemStack icon = new ItemStack(Material.valueOf(cs.getString("icon")));
			ItemMeta meta = Bukkit.getItemFactory().getItemMeta(icon.getType());
			meta.setDisplayName(cs.getString("name"));
			meta.setLore(Arrays.asList(new String[] { cs.getString("description") }));
			icon.setItemMeta(meta);
			mapList.addItem(icon);
			slotIds.add(mapId);
		}
		player.setMetadata("listmaps", new LazyMetadataValue(plugin, null));
		player.setMetadata("maps", new LazyMetadataValue(plugin, new Callable<Object>()
		{

			@Override
			public ArrayList<String> call() throws Exception
			{
				return slotIds;
			}
		}));
		player.openInventory(mapList);
	}
}
