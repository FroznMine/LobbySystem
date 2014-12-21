package de.froznmine.lobby;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import de.froznmine.lobby.event.InventoryChooseEvent;
import de.froznmine.lobby.event.connection.PlayerConnectEvent;
import de.froznmine.lobby.game.IGame;

public class LobbySystem extends JavaPlugin {
	public static Economy ECONOMY = null;
	private static List<Inventory> inventories;
	public static Logger LOGGER;
	public static LobbySystem PLUGIN;
	private static HashMap<JavaPlugin, Class<? extends IGame>> registeredGames;

	public static void registerGame(final JavaPlugin plugin, final Class<? extends IGame> game) {
		LobbySystem.registeredGames.remove(plugin);
		LobbySystem.registeredGames.put(plugin, game);
	}

	@Override
	public void onDisable() {

	}

	@Override
	public void onEnable() {
		LobbySystem.PLUGIN = this;

		LobbySystem.LOGGER = this.getLogger();

		if (!new File(this.getDataFolder() + "config.yml").exists())
			this.saveDefaultConfig();

		this.setupEconomy();
		this.setupMenu();

		this.registerEvents();
		
		LobbySystem.registeredGames = new HashMap<JavaPlugin, Class<? extends IGame>>();
	}

	private void registerEvents() {
		PluginManager manager = Bukkit.getPluginManager();
		
		manager.registerEvents(new InventoryChooseEvent(), this);
		manager.registerEvents(new PlayerConnectEvent(), this);
	}

	private void setupEconomy() {
		LobbySystem.LOGGER.info("Enabling economy.");
		final RegisteredServiceProvider<Economy> economyProvider = this
				.getServer().getServicesManager()
				.getRegistration(Economy.class);
		if (economyProvider != null) {
			LobbySystem.ECONOMY = economyProvider.getProvider();
			LobbySystem.LOGGER.info("Enabled economy.");
			return;
		}
		LobbySystem.LOGGER.info("Cannot enable economy.");
	}

	private void setupMenu() {
		LobbySystem.inventories.add(Bukkit.createInventory(null, 9));

	}
}
