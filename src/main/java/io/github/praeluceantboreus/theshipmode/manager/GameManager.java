package io.github.praeluceantboreus.theshipmode.manager;

import io.github.praeluceantboreus.theshipmode.listener.DeathListener;
import io.github.praeluceantboreus.theshipmode.main.TheShipModePlugin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class GameManager
{
	private HashMap<Player, Player> players;
	// <Hunter, Victim>
	private boolean isStarted;
	private TheShipModePlugin plugin;

	public GameManager()
	{
		players = new HashMap<>();
		Bukkit.getPluginManager().registerEvents(new DeathListener(null, plugin), plugin);
	}

	public void startGame()
	{
		for (Player player : players.keySet())
			preparePlayer(player);
	}

	public void preparePlayer(Player player)
	{
		player.setGameMode(GameMode.ADVENTURE);
		player.getInventory().setMaxStackSize(1);
	}

	public void shufflePlayers()
	{
		ArrayList<Player> playerList = new ArrayList<>(players.values());
		extendedShuffle(playerList.get(0), playerList);
	}

	private void extendedShuffle(Player player, ArrayList<Player> playerList)
	{
		do
		{
			Collections.shuffle(playerList);
		} while (playerList.get(0) == player);
		Player victim = playerList.get(0);
		players.put(player, victim);
		playerList.remove(victim);
		extendedShuffle(victim, playerList);
	}

	public boolean isStarted()
	{
		return isStarted;
	}

	public boolean isHunterFrom(Player hunter, Player victim)
	{
		return players.get(hunter) == victim;
	}

	public boolean isInGame(Player player)
	{
		return players.containsKey(player);
	}

	public void setOutOfRound(Player player)
	{
		players.put(player, null);
	}

	public Set<Player> getPlayers()
	{
		return new HashSet<>(players.keySet());
	}
}
