package de.pesacraft.lobbysystem;

public abstract class Position {
	public enum PositionType {
		GAME, LOBBY, OTHER;
	}

	protected String positionName;

	protected PositionType type;

	public Position(final PositionType type, final String positionName) {
		this.type = type;
		this.positionName = positionName;
	}

	/**
	 * Get the String that should be shown when a player plays/is in this
	 * position for example
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
	public String getPosition() {
		return this.positionName;
	}

	public PositionType getType() {
		return this.type;
	}
}
