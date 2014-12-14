package de.froznmine.lobby.game;

import org.bukkit.entity.Player;

public interface IGame {
	/** Add a player to the game.</br>
	 * Implementing class has to handle whether it can join or not and to what.
	 * 
	 * @param p The joining player
	 * @return true if the player could join, false otherwise
	 */
	public boolean addPlayer(Player p);
	/** Remove a player from the game.</br>
	 * Called when the player leaves the server, teleports himself, gets teleported or uses /leave</br>
	 * Implementing class needs to do output etc.
	 * 
	 * @param p The leaving player
	 */
	public void removePlayer(Player p);
}
