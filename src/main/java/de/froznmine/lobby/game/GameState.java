package de.froznmine.lobby.game;

/** GameState is an enum to display the games state.
 * 
 * @author FroznMine
 */
public enum GameState {
	/** Games in LOBBY phase should allow players to join to play.</br>
	 */
	LOBBY,
	/** In WARMUP players can't join.</br>
	 * It should be used for level creation, teleportation, giving stuff etc. 
	 */
	WARMUP,
	/** INGAME players can join as spectators</br>
	 * Use this when players are actually playing
	 */
	INGAME,
	/** At GAMEEND nobody can join.</br>
	 * Should be used for giving rewards, removing players, level, etc.
	 */
	GAMEEND;
}
