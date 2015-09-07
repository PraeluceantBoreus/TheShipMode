package io.github.praeluceantboreus.theshipmode.manager.map;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.EntityType;

public class Camera implements ConfigurationSerializable
{
	private boolean isEntity;
	private EntityType entityType;
	private Material materialType;
	private Location location;
	private double radius;

	public Camera(boolean isEntity, EntityType entityType, Material materialType, Location location, double radius)
	{
		super();
		this.isEntity = isEntity;
		this.entityType = entityType;
		this.materialType = materialType;
		this.location = location;
		this.radius = radius;
	}

	@Override
	public Map<String, Object> serialize()
	{
		HashMap<String, Object> ret = new HashMap<>();
		ret.put("isEntity", isEntity);
		if (isEntity)
			ret.put("type", entityType);
		else
			ret.put("type", materialType);
		ret.put("location", location);
		ret.put("radius", radius);
		return ret;
	}

	public static Camera deserialize(ConfigurationSection cs)
	{
		boolean isEntity = cs.getBoolean("isEntity");
		EntityType entityType = null;
		Material materialType = null;
		if (isEntity)
			entityType = EntityType.valueOf(cs.getString("type"));
		else
			materialType = Material.valueOf(cs.getString("type"));
		Location location = Location.deserialize(cs.getConfigurationSection("location").getValues(true));
		double radius = cs.getDouble("radius");
		return new Camera(isEntity, entityType, materialType, location, radius);
	}

	public boolean isEntity()
	{
		return isEntity;
	}

	public EntityType getEntityType()
	{
		return entityType;
	}

	public Material getMaterialType()
	{
		return materialType;
	}

	public Location getLocation()
	{
		return location.clone();
	}

	public double getRadius()
	{
		return radius;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Camera other = (Camera) obj;
		if (location == null)
		{
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		return true;
	}
}
