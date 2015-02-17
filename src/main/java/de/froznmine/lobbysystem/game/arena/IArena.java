package de.froznmine.lobbysystem.game.arena;

import java.io.Serializable;

import org.bukkit.Location;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import de.froznmine.lobbysystem.Position;
import de.froznmine.lobbysystem.game.GameUser;

public interface IArena extends ConfigurationSerializable, Serializable, Position {
	
	/** Loads the arena and needed stuff at the specific location.<br>
	 * Also has to load and set chest contents.
	 * 
	 * @param loc The location where the arena should be loaded
	 * @return true if it was loaded successful, false otherwise
	 */
	public boolean load(Location loc);
	
	/** Deletes the arena.<br>
	 * Should remove dropped items, and replace everything with air.
	 * 
	 * @return true if the deletion was successful, false otherwise
	 */
	public boolean delete();
	
	/** Get the maximum amount of players.
	 * 
	 * @return The maximum amount of players
	 */
	public int getMaxPlayers();
	
	/** Get the required amount of players to start the game.
	 * 
	 * @return The required amount of players
	 */
	public int getRequiredPlayers();
	
	/** Teleports the specific player to the next spawn location.<br>
	 * Teams and other aspects have to be handled.
	 * 
	 * @param player The player to teleport
	 * @return true if teleport was successful, false otherwise
	 */
	public boolean teleport(GameUser player);
}