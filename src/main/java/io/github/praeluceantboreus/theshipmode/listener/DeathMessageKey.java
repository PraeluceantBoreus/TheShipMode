package io.github.praeluceantboreus.theshipmode.listener;

public enum DeathMessageKey
{
	DEAD_PLAYER("p"), KILLER("k");

	private String key;

	private DeathMessageKey(String key)
	{
		this.key = key;
	}

	public String getKey()
	{
		return "%" + key;
	}
}
