package de.froznmine.lobby;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import de.froznmine.lobby.event.InventoryChooseEvent;
import de.froznmine.lobby.game.IGame;

public class LobbySystem extends JavaPlugin {
	public static Logger LOGGER;
	public static Economy ECONOMY = null;
	private static List<Inventory> inventories;
	private static HashMap<JavaPlugin, IGame> registeredGames;
	
	public void onEnable() { 
		LOGGER = this.getLogger();
		
		if (!new File(this.getDataFolder() + "config.yml").exists()) this.saveDefaultConfig();
		
		setupEconomy();
		setupMenu();
		
		registerEvents();
		registeredGames = new HashMap<JavaPlugin, IGame>();	
	}
	
	private void setupMenu() {
		inventories.add(Bukkit.createInventory(null, 9));
		
	}

	private void registerEvents() {
		Bukkit.getPluginManager().registerEvents(new InventoryChooseEvent(), this);
	}

	private void setupEconomy() {
		LOGGER.info("Enabling economy.");
		RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(Economy.class);
		if (economyProvider != null) {
			ECONOMY = economyProvider.getProvider();
			LOGGER.info("Enabled economy.");
			return;
		}
		LOGGER.info("Cannot enable economy.");
	}
	
	
	public void onDisable() { 
		
	}
	
	public static void registerGame(JavaPlugin plugin, IGame game) {
		registeredGames.remove(plugin);
		registeredGames.put(plugin, game);
	}
}
