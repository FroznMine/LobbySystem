package de.froznmine.lobbysystem.user;

import java.util.ArrayList;
import java.util.List;

public class Users {
	/**
	 * A list containing all currently online users.
	 */
	private static final List<User> users = new ArrayList<User>();
	
	/**
	 * Adds a user to the list of users.<br>
	 * Should be called when a player is connecting.<br>
	 * <br>
	 * If there already exists a user with the same UUID, it gets deleten.
	 *
	 * @param user
	 *            The user to add
	 */
	public static void addUser(final User user) {
		for (final User u : Users.users)
			if (u.getUUID().compareTo(user.getUUID()) == 0)
				Users.users.remove(u);

		Users.users.add(user);
	}

	/**
	 * Remove a user from the list.<br>
	 * Should be called when a player is disconnecting and the User object isn't
	 * needed anymore.
	 *
	 * @param user
	 *            The user to remove
	 * @return The removed user
	 */
	public static User removeUser(final User user) {
		return Users.users.remove(Users.users.indexOf(user));
	}
}
