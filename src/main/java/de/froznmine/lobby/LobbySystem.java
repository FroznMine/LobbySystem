package de.froznmine.lobby;

import java.util.HashMap;
import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

import de.froznmine.cannonfight.game.Game;

public class LobbySystem extends JavaPlugin {
	public static Logger LOGGER;
	private HashMap<JavaPlugin, Class> registeredGames;
	
	public void onEnable() { 
		LOGGER = this.getLogger();
		 
	}
	
	public void onDisable() { 
		
	}
	
	public static void registerGame(JavaPlugin plugin, Class<?> gameClass) {
		
	}
}
