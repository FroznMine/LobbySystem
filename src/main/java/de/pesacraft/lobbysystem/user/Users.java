package de.pesacraft.lobbysystem.user;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
	 * Gets the user with the specified UUID
	 *
	 * @param uuid the UUID of the user
	 * @return the user or null if not found
	 */
	public static User getByUUID(final UUID uuid) {
		for (final User u : Users.users)
			if (u.uuid.compareTo(uuid) == 0)
				return u;
		return null;
	}

	/**
	 * Remove a user from the list.<br>
	 * Should be called when a player is disconnecting and the User object isn't
	 * needed anymore.
	 *
	 * @param user
	 *            The user to remove
	 * @return true if it was in the list and successfully removed
	 */
	public static boolean removeUser(final User user) {
		return Users.users.remove(user);
	}

	/**
	 * Remove a user by its UUID from the list. Should be called when a player
	 * is disconnecting and the User object isn't needed anymore.
	 *
	 * @param uuid
	 *            The users UUID
	 * @return The removed User object if there was one, else null
	 */
	public static User removeUserByUUID(final UUID uuid) {
		for (final User u : Users.users)
			if (u.uuid.compareTo(uuid) == 0) {
				Users.users.remove(u);
				return u;
			}
		return null;
	}
}
