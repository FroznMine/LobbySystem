package de.froznmine.lobbysystem.game;

import org.bukkit.entity.Player;

import de.froznmine.lobbysystem.user.User;

/**
 * A GamePlayer is created for every player in a game.</br> It should be used to
 * give the player rewards, get his role in current game etc.
 *
 * @author FroznMine
 */
public class GameUser extends User {
	private PlayerRole role;
	
	/**
	 * Creates a new GamePlayer based on the given player</br> Gets UUID and
	 * location of that player and stores it</br>
	 *
	 * @param player The player for which the GameUser should be created
	 */
	public GameUser(final Player player) {
		super(player);
		this.role = PlayerRole.PLAYER;
	}
	
	/**
	 * Create a GameUser based on a given User object
	 * 
	 * @param u the user object which should be expanded
	 */
	public GameUser(User u) {
		super(u);
		this.role = PlayerRole.PLAYER;
	}

	/**
	 * Create a copy of a given GameUser
	 * 
	 * @param u The GameUser to clone
	 */
	public GameUser(GameUser u) {
		super(u);
		this.role = u.role;
	}

	public PlayerRole getRole() {
		return this.role;
	}
	
	public void setRole(PlayerRole role) {
		this.role = role;
	}
	
	public enum PlayerRole {
		PLAYER,
		SPECTATOR;
	}
}
