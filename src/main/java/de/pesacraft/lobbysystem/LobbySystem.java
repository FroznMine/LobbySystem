package de.pesacraft.lobbysystem;

import java.io.File;
import java.util.List;
import java.util.logging.Logger;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import de.pesacraft.lobbysystem.event.connection.PlayerConnectionEvents;
import de.pesacraft.lobbysystem.lobby.ILobby;
import de.pesacraft.lobbysystem.user.Users;

public class LobbySystem extends JavaPlugin {
	public static Economy ECONOMY = null;
	// TODO auskoppeln
	//private static List<Inventory> inventories;
	public static Logger LOGGER;
	public static Position MAIN_LOBBY;
	public static LobbySystem PLUGIN;

	// private static List<GamePlugin> gameTypes;

	@Override
	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
		return true;
	}

	/**
	 * Register a game plugin that should be usable.
	 *
	 * @param gamePlugin
	 *            the game plugin
	 */
	// public static void registerGame(final GamePlugin gamePlugin) {
	// gameTypes.remove(gamePlugin);
	// gameTypes.add(gamePlugin);
	// }

	
	@Override
	public void onEnable() {
		LobbySystem.PLUGIN = this;

		LobbySystem.LOGGER = this.getLogger();

		
		if (!new File(this.getDataFolder() + "config.yml").exists())
			this.saveDefaultConfig();

		this.setupDatabase();
		this.setupEconomy();
		this.setupMenu();

		this.registerEvents();

		// LobbySystem.gameTypes = new ArrayList<GamePlugin>();
	}
	
	@Override
	public void onDisable() {

	}

	private void registerEvents() {
		final PluginManager manager = Bukkit.getPluginManager();

		// manager.registerEvents(new InventoryChooseEvent(), this);
		manager.registerEvents(new PlayerConnectionEvents(), this);
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
		//LobbySystem..inventories.add(Bukkit.createInventory(null, 9));

	}
	
	private void setupDatabase() {
		// TODO Auto-generated method stub
		
	}

}
