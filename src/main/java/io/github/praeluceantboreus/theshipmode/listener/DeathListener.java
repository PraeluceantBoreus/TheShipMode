package io.github.praeluceantboreus.theshipmode.listener;

import io.github.praeluceantboreus.theshipmode.config.DeathMessage;
import io.github.praeluceantboreus.theshipmode.config.MessageReceiver;
import io.github.praeluceantboreus.theshipmode.main.TheShipModePlugin;
import io.github.praeluceantboreus.theshipmode.manager.GameManager;

import java.util.Set;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener
{
	private GameManager manager;
	private TheShipModePlugin plugin;

	private DeathListener(GameManager manager, TheShipModePlugin plugin)
	{
		super();
		this.manager = manager;
		this.plugin = plugin;
	}

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent pde)
	{
		Player victim = pde.getEntity();
		if (!manager.isInGame(victim))
			return;
		Player killer = victim.getKiller();
		pde.setDeathMessage(null);
		if (killer == null)
			broadcast(DeathMessage.SUICID, victim, null);
		else
		{
			if (manager.isHunterFrom(killer, victim))
				broadcast(DeathMessage.HUNTER_SUCCESS, victim, killer);
			else
			{
				if (manager.isHunterFrom(killer, victim))
					broadcast(DeathMessage.VICTIM_KILLED_HUNTER, killer, victim);
				else
					broadcast(DeathMessage.RANDOM, victim, killer);
			}
		}
	}

	public void broadcast(DeathMessage msg, Player victim, Player hunter)
	{
		Set<Player> players = manager.getPlayers();
		players.remove(victim);
		players.remove(hunter);
		for (Player player : players)
			player.sendMessage(prepareMessage(victim, hunter, MessageReceiver.OTHERS, msg));
		victim.sendMessage(prepareMessage(victim, hunter, MessageReceiver.VICTIM, msg));
		if (hunter != null)
			hunter.sendMessage(prepareMessage(victim, hunter, MessageReceiver.KILLER, msg));
	}

	public String prepareMessage(Player victim, Player hunter, MessageReceiver rec, DeathMessage msg)
	{
		return plugin.getConfig().getString(msg.getKey(rec)).replace(DeathMessageKey.DEAD_PLAYER.getKey(), victim.getName()).replace(DeathMessageKey.KILLER.getKey(), (hunter == null) ? "unknown player" : hunter.getName());
	}
}
