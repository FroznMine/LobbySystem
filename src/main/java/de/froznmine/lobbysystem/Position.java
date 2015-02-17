package de.froznmine.lobbysystem;

public abstract class Position {
	protected PositionType type;
	/**
	 * Get the String that should be shown
	 * when a player plays/is in this position
	 * for example
	 * <p>
	 * "Player currently plays &ltposition&gt."
	 * </p>
	 * could get to
	 * <p>
	 * "Player currently plays AwesomeGame."
	 * </p>
	 * depending on what is returned by this method
	 * 
	 * @return The String representing the 'position' alias game-/lobby-name.
	 */
	public abstract String getPosition();
	
	public PositionType getType() {
		return this.type;
	}
	
	public enum PositionType {
		GAME,
		LOBBY,
		OTHER;
	}
}
