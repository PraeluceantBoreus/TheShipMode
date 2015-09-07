package io.github.praeluceantboreus.theshipmode.manager.map;

import io.github.praeluceantboreus.theshipmode.exceptions.AlreadyExistsException;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class Inspector extends Camera
{
	private double multiplicator;
	private int money;
	private HashMap<Player, Long> playersStart;
	private HashMap<Player, Integer> playersSeconds;

	public Inspector(boolean isEntity, EntityType entityType, Material materialType, Location location, double radius, int money, double multiplicator)
	{
		super(isEntity, entityType, materialType, location, radius);
		this.money = money;
		this.multiplicator = multiplicator;
		playersStart = new HashMap<>();
		playersSeconds = new HashMap<>();
	}

	public Inspector(Camera camera, int money, double multiplicator)
	{
		this(camera.isEntity(), camera.getEntityType(), camera.getMaterialType(), camera.getLocation(), camera.getRadius(), money, multiplicator);
	}

	public int getMoney()
	{
		return money;
	}

	public double getMultiplicator()
	{
		return multiplicator;
	}

	public int getMoney(int seconds)
	{
		return (int) (getMoney() * seconds * Math.pow(multiplicator, seconds) * (1 + ((getRadius() > 5) ? 1 : 1.0 / getRadius())));
	}

	@Override
	public Map<String, Object> serialize()
	{
		Map<String, Object> ret = super.serialize();
		ret.put("money", money);
		ret.put("multiplicator", multiplicator);
		return ret;
	}

	public static Inspector deserialize(ConfigurationSection cs)
	{
		int money = cs.getInt("money");
		double multiplicator = cs.getDouble("multiplicator");
		return new Inspector(Camera.deserialize(cs), money, multiplicator);
	}

	public void registerPlayer(Player player, int seconds) throws AlreadyExistsException
	{
		if (playersSeconds.containsKey(player))
			throw new AlreadyExistsException(player, (int) (playersSeconds.get(player) - ((System.currentTimeMillis() - playersStart.get(player)) / 1000)));
		playersSeconds.put(player, seconds);
		playersStart.put(player, System.currentTimeMillis());
	}
}
