package de.froznmine.lobbysystem.game;

import java.util.List;

import org.bukkit.entity.Player;

import de.froznmine.lobbysystem.Position;

public abstract class IGame extends Position {
	protected List<GameUser> players;
	
	/** Add a player to the game.</br> Implementing class has to handle whether
	 * it can join or not and to what.
	 *
	 * @param p The joining player
	 * @return true if the player could join, false otherwise
	 */
	public boolean addPlayer(Player p) {
		GameUser u = GameUsers.get(p);
		
		Pla
	}

	/** Remove a player from the game.</br> Called when the player leaves the
	 * server, teleports himself, gets teleported or uses /leave</br>
	 * Implementing class needs to do output etc.
	 *
	 * @param p The leaving player
	 */
	public void removePlayer(Player p);
	
	/** Start the game immediatly.<br>
	 * Used when commands start the game or equal events happen.
	 */
	public void start();
	
	/** Get the maximum allowed players in this game.
	 * 
	 * @return The maximum amount of players.
	 */
	public int getMaxPlayers();
	
//	public List<>
}
