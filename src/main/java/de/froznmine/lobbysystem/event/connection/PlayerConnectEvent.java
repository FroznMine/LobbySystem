package de.froznmine.lobbysystem.event.connection;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import de.froznmine.lobbysystem.user.User;
import de.froznmine.lobbysystem.user.Users;

public class PlayerConnectEvent implements Listener {
	@EventHandler
	/** Handles players joins.<br>
	 * Adds the player to the users list and loading his statistics.
	 * 
	 */
	public static void onPlayerConnect(final PlayerJoinEvent event) {
		Users.addUser(new User(event.getPlayer()));
	}
}
