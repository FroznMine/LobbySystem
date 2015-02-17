package de.froznmine.lobbysystem.game;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

public class GameUsers {
	/**
	 * A list containing all currently online users, which played a game this session.
	 */
	private static final List<GameUser> users = new ArrayList<GameUser>();
	
	/**
	 * Adds a user to the list of users.<br>
	 * Should be called when a player is connecting.<br>
	 * <br>
	 * If there already exists a user with the same UUID, it gets deleted.
	 *
	 * @param player The user to add
	 */
	public static void addUser(final GameUser player) {
		for (final GameUser u : GameUsers.users)
			if (u.getUUID().compareTo(player.getUUID()) == 0)
				GameUsers.users.remove(u);

		GameUsers.users.add(player);
	}

	/**
	 * Remove a user from the list.<br>
	 * Should be called when a player is disconnecting and the User object isn't
	 * needed anymore.
	 *
	 * @param player The player to remove
	 * @return The removed player
	 */
	public static GameUser removeUser(final GameUser player) {
		return GameUsers.users.remove(GameUsers.users.indexOf(player));
	}

	public static GameUser get(Player p) {
		for (GameUser user : users)
			if (user.getUUID().compareTo(p.getUniqueId()) == 0) return user;
		
		GameUser user = new GameUser(p);
		
		users.add(user);
		
		return user;
	}
}
