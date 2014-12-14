package de.froznmine.lobby.game;

import java.util.UUID;

import net.milkbowl.vault.economy.EconomyResponse.ResponseType;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import de.froznmine.lobby.LobbySystem;

/** A GamePlayer is created for every player in a game.</br>
 * It should be used to give the player rewards, get his role in current game etc.
 * 
 * @author FroznMine
 */
public class GamePlayer {
	/** The players uuid
	 */
	private final UUID uuid;
	/** The players last location. There he will be teleported on game end
	 */
	private final Location lastLocation;
	
	/** Creates a new GamePlayer based on the given player</br>
	 * Gets UUID and location of that player and stores it</br>
	 * 
	 * @param player The player for which the GamePlayer should be created
	 */
	public GamePlayer(Player player) {
		this.uuid = player.getUniqueId();
		this.lastLocation = player.getLocation();
	}
	
	/** Give the player a global money reward
	 * 
	 * @param money The amount to give
	 * @return true if deposing was successful, false otherwise 
	 */
	public boolean giveGlobalReward(int money) {
		return LobbySystem.ECONOMY.depositPlayer(Bukkit.getOfflinePlayer(uuid), money).type == ResponseType.SUCCESS;
	}
	
	/** Let the player leave the game.</br>
	 * Teleports the player to its last location.
	 */
	public void leave() {
		Bukkit.getPlayer(uuid).teleport(lastLocation);
	}
}
