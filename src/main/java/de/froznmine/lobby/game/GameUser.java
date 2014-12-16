package de.froznmine.lobby.game;

import org.bukkit.entity.Player;

import de.froznmine.lobby.user.User;

/**
 * A GamePlayer is created for every player in a game.</br> It should be used to
 * give the player rewards, get his role in current game etc.
 *
 * @author FroznMine
 */
public class GameUser extends User {
	/**
	 * Creates a new GamePlayer based on the given player</br> Gets UUID and
	 * location of that player and stores it</br>
	 *
	 * @param player
	 *            The player for which the GameUser should be created
	 */
	public GameUser(final Player player) {
		super(player);
	}
}
