package de.froznmine.lobbysystem;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import de.froznmine.lobbysystem.event.InventoryChooseEvent;
import de.froznmine.lobbysystem.event.connection.PlayerConnectEvent;
import de.froznmine.lobbysystem.game.GamePlugin;

public class LobbySystem extends JavaPlugin {
	public static Position MAIN_LOBBY;
	public static Economy ECONOMY = null;
	private static List<Inventory> inventories;
	public static Logger LOGGER;
	public static LobbySystem PLUGIN;
	private static List<GamePlugin> gameTypes;

	/**
	 * Register a game plugin that should be usable.
	 * 
	 * @param gamePlugin the game plugin
	 */
	public static void registerGame(final GamePlugin gamePlugin) {
		gameTypes.remove(gamePlugin);
		gameTypes.add(gamePlugin);
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
		
		LobbySystem.gameTypes = new ArrayList<GamePlugin>();
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
