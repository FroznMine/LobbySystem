package de.froznmine.lobby.user;

import java.util.UUID;

import net.milkbowl.vault.economy.EconomyResponse.ResponseType;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Scoreboard;

import de.froznmine.lobby.LobbySystem;

public class User {
	/**
	 * The users last location. There he will be teleported when a game ends or
	 * something else needs to teleport him back
	 */
	protected Location lastLocation;
	/**
	 * The general state of the user.
	 */
	protected UserState userState;
	/**
	 * The users uuid
	 */
	protected final UUID uuid;

	/**
	 * Creates a new User based on the given player</br> Gets UUID and location
	 * of that player and stores it</br>
	 *
	 * @param player
	 *            The player for which the User should be created
	 */
	public User(final Player player) {
		this.uuid = player.getUniqueId();
		this.lastLocation = player.getLocation();
		this.userState = UserState.LOBBY;
	}

	/**
	 * Get the uuid of this user.
	 *
	 * @return The uuid.
	 */
	public UUID getUUID() {
		return this.uuid;
	}

	/**
	 * Give the user a specific amount of global money
	 *
	 * @param amount
	 *            The amount to give
	 * @return true if deposing was successful, false otherwise
	 */
	public boolean giveGlobalMoney(final int amount) {
		return LobbySystem.ECONOMY.depositPlayer(
				Bukkit.getOfflinePlayer(this.uuid), amount).type == ResponseType.SUCCESS;
	}

	/**
	 * Lets the user leave the game.</br> Teleports the user to his last
	 * location.
	 */
	public void leave() {
		Bukkit.getPlayer(this.uuid).teleport(this.lastLocation);
	}

	/**
	 * Shows the user the passed scoreboard.<br>
	 * If a long is passed that is the time after which the scoreboard
	 * disappears and the previous gets shown again.<br>
	 *
	 * @param scoreboard
	 * @param ticks
	 */
	public void showScoreboard(final Scoreboard scoreboard, final long... ticks) {
		if (!Bukkit.getOfflinePlayer(this.uuid).isOnline())
			return;

		final Player p = Bukkit.getPlayer(this.uuid);
		final Scoreboard old = p.getScoreboard();

		p.setScoreboard(scoreboard);

		if (ticks.length > 0) {
			final BukkitRunnable runnable = new BukkitRunnable() {

				@Override
				public void run() {
					p.setScoreboard(old);
				}
			};

			runnable.runTaskLater(LobbySystem.PLUGIN, ticks[0]);
		}
	}

	/** Teleports the player to the given location.<br>
	 * Should always be used to teleport the player.<br>
	 * Checks if the new location is equal to the player's current location. If it is he won't get teleported.<br>
	 * 
	 * @param loc The location where the player should be teleported.
	 * @return true if teleportation successful, false otherwise
	 */
	public boolean teleport(Location loc) {
		Player p = Bukkit.getPlayer(uuid);
		Location current = p.getLocation();
		
		if (current.equals(loc)) return false;
		
		lastLocation = current;
		
		p.teleport(loc);
		
		return true;
	}
}