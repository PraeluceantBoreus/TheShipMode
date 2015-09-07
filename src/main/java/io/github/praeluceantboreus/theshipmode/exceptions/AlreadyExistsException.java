package io.github.praeluceantboreus.theshipmode.exceptions;

import org.bukkit.entity.Player;

public class AlreadyExistsException extends Exception
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Player player;
	private int seconds;

	public AlreadyExistsException(Player player, int seconds)
	{
		super();
		this.player = player;
		this.seconds = seconds;
	}

	public Player getPlayer()
	{
		return player;
	}

	public int getSeconds()
	{
		return seconds;
	}
}
