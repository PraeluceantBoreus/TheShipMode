package io.github.praeluceantboreus.theshipmode.config;

public enum DeathMessage
{
	HUNTER_SUCCESS, VICTIM_KILLED_HUNTER, RANDOM, SUICID;

	public String getKey(MessageReceiver receiver)
	{
		return "lang.death." + receiver + super.toString().toLowerCase();
	}
}
