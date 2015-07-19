package de.pesacraft.lobbysystem.game;

import org.bukkit.plugin.java.JavaPlugin;

import de.pesacraft.lobbysystem.lobby.ILobby;

public abstract class GamePlugin extends JavaPlugin {
	protected final String abbreviation;
	protected ILobby lobby;
	protected final String name;

	protected GamePlugin(final String name, final String abbreviation) {
		super();
		this.name = name;
		this.abbreviation = abbreviation;
	}

	public final String getAbbreviation() {
		return this.abbreviation;
	}

	public final String getGameName() {
		return this.name;
	}

	public final ILobby getLobby() {
		return this.lobby;
	}

	public abstract void newGame();
}
