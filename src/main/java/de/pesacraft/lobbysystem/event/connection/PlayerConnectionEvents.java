package de.pesacraft.lobbysystem.event.connection;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import de.pesacraft.lobbysystem.user.User;
import de.pesacraft.lobbysystem.user.Users;

public class PlayerConnectionEvents implements Listener {

	/**
	 * Handles players joins.<br>
	 * Adds the player to the users list and loading his statistics.
	 */
	@EventHandler
	public void onPlayerConnect(final PlayerJoinEvent event) {
		Users.addUser(new User(event.getPlayer()));
	}

	/**
	 * Handler leaving players.<br>
	 * Removes the player from the online users list, stores his statistics and
	 * restores memory used by it.
	 */
	@EventHandler
	public void onPlayerDisconnect(final PlayerQuitEvent event) {
		Users.removeUserByUUID(event.getPlayer().getUniqueId());
	}

}
