package de.froznmine.lobbysystem.game;

import org.bukkit.plugin.java.JavaPlugin;

import de.froznmine.lobbysystem.lobby.ILobby;

public abstract class GamePlugin extends JavaPlugin {
	protected final String name;
	protected final String abbreviation;
	protected ILobby lobby;
	
	protected GamePlugin(String name, String abbreviation) {
		this.name = name;
		this.abbreviation = abbreviation;
	}
	
	public final String getGameName() {
		return this.name;
	}
	
	public final String getAbbreviation() {
		return this.abbreviation;
	}
	
	public abstract void newGame();
	
	public final ILobby getLobby() {
		return this.lobby;
	}
}
