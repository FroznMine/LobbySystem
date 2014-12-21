package de.froznmine.lobby.event.connection;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import de.froznmine.lobby.user.User;
import de.froznmine.lobby.user.Users;

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
