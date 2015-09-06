package io.github.praeluceantboreus.theshipmode.config;

public enum MessageReceiver
{
	VICTIM, KILLER, OTHERS;

	@Override
	public String toString()
	{
		return super.toString().toLowerCase();
	}
}
