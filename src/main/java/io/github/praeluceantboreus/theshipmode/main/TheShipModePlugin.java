package io.github.praeluceantboreus.theshipmode.main;

import io.github.praeluceantboreus.theshipitems.main.TheShipItemsPlugin;
import io.github.praeluceantboreus.theshipmode.config.DeathMessage;
import io.github.praeluceantboreus.theshipmode.config.MessageReceiver;
import io.github.praeluceantboreus.theshipmode.listener.DeathListener;
import io.github.praeluceantboreus.theshipmode.listener.DeathMessageKey;
import io.github.praeluceantboreus.theshipmode.listener.MapChooseListener;
import io.github.praeluceantboreus.theshipmode.manager.GameManager;

import java.io.File;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class TheShipModePlugin extends JavaPlugin
{
	private boolean useTSI;
	private TheShipItemsPlugin tsi;

	@Override
	public void onEnable()
	{
		genConfig();
		Plugin tsiPlugin = getServer().getPluginManager().getPlugin("TheShipItems");
		tsi = null;
		if (tsiPlugin instanceof TheShipItemsPlugin)
			tsi = (TheShipItemsPlugin) tsiPlugin;
		useTSI = tsi != null;
		initListeners();
		super.onEnable();
	}

	private void initListeners()
	{
		this.getServer().getPluginManager().registerEvents(new MapChooseListener(), this);
	}

	private void genConfig()
	{
		getConfig().addDefault(DeathMessage.HUNTER_SUCCESS.getKey(MessageReceiver.KILLER), "Du hast dein Opfer " + DeathMessageKey.DEAD_PLAYER.getKey() + " erfolgreich ermordet.");
		getConfig().addDefault(DeathMessage.HUNTER_SUCCESS.getKey(MessageReceiver.VICTIM), DeathMessageKey.KILLER.getKey() + " hat dich gejagt und getötet!");
		getConfig().addDefault(DeathMessage.HUNTER_SUCCESS.getKey(MessageReceiver.OTHERS), DeathMessageKey.KILLER.getKey() + " hat " + DeathMessageKey.DEAD_PLAYER + " gejagt und getötet!");

		getConfig().addDefault(DeathMessage.RANDOM.getKey(MessageReceiver.KILLER), "Du hast " + DeathMessageKey.DEAD_PLAYER.getKey() + " kaltblütig niedergemetzelt!");
		getConfig().addDefault(DeathMessage.RANDOM.getKey(MessageReceiver.VICTIM), DeathMessageKey.KILLER.getKey() + " hat dich kaltblütig niedergemetzelt!");
		getConfig().addDefault(DeathMessage.RANDOM.getKey(MessageReceiver.OTHERS), DeathMessageKey.KILLER.getKey() + " hat " + DeathMessageKey.DEAD_PLAYER + " kaltblütig niedergemetzelt!");

		getConfig().addDefault(DeathMessage.SUICID.getKey(MessageReceiver.KILLER), "Diese Nachricht sollte nicht existieren, bitte melde dies dem Admin!");
		getConfig().addDefault(DeathMessage.SUICID.getKey(MessageReceiver.VICTIM), "Du solltest besser auf dich aufpassen!");
		getConfig().addDefault(DeathMessage.SUICID.getKey(MessageReceiver.OTHERS), DeathMessageKey.DEAD_PLAYER.getKey() + " hat seine Bedürfnisse ignoriert. Schau, dass dir nicht auch sowas passiert!");

		getConfig().addDefault(DeathMessage.VICTIM_KILLED_HUNTER.getKey(MessageReceiver.KILLER), "Du bist von deinem Opfer " + DeathMessageKey.DEAD_PLAYER.getKey() + " ermordet worden!");
		getConfig().addDefault(DeathMessage.VICTIM_KILLED_HUNTER.getKey(MessageReceiver.VICTIM), "Sehr gut, du hast deinen Mörder " + DeathMessageKey.KILLER.getKey() + " getötet");
		getConfig().addDefault(DeathMessage.VICTIM_KILLED_HUNTER.getKey(MessageReceiver.OTHERS), DeathMessageKey.DEAD_PLAYER.getKey() + " ist von " + DeathMessageKey.KILLER.getKey() + " aus Notwehr ermordet worden!");

		getConfig().addDefault("main.maps.container", getDataFolder().toURI().relativize(new File(getDataFolder() + "/maps").toURI()));
		getConfig().addDefault("main.maps.temp", getDataFolder().toURI().relativize(new File(getDataFolder() + "/temp").toURI()));

		getConfig().options().copyDefaults(true);
		saveConfig();
	}
}
