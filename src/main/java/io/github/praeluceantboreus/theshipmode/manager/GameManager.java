package io.github.praeluceantboreus.theshipmode.manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class GameManager
{
	private HashMap<Player, Player> players;
	private boolean isStarted;

	public GameManager()
	{
		players = new HashMap<>();
	}

	public void startGame()
	{
		for (Player player : players.keySet())
			preparePlayer(player);
	}

	public void preparePlayer(Player player)
	{
		player.setGameMode(GameMode.ADVENTURE);
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
}
