package io.github.praeluceantboreus.theshipmode.listener;

import io.github.praeluceantboreus.theshipmode.manager.MapManager;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class SignListener implements Listener
{
	private MapManager mapmanager;

	@EventHandler
	public void onSignClick(PlayerInteractEvent pie)
	{
		if (pie.getAction().equals(Action.RIGHT_CLICK_BLOCK) && (pie.getClickedBlock().getType().equals(Material.SIGN) || pie.getClickedBlock().getType().equals(Material.SIGN_POST)))
		{
			Sign sign = (Sign) pie.getClickedBlock().getState();
			boolean searchForPlayer = false;
			Player player = null;
			for (String line : sign.getLines())
			{
				if (line.equalsIgnoreCase("openmaps"))
					searchForPlayer = true;
				if (searchForPlayer)
					player = Bukkit.getPlayer(line);
			}
			if (player != null)
				mapmanager.listMaps(player);
		}
	}
}
