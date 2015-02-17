package de.froznmine.lobbysystem.game.event;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import de.froznmine.lobbysystem.game.IGame;

/**
 * A event which is called when the specific game is over.
 * It's time to teleport the players to a match recap location,
 * where rewards are rewarder.
 * 
 * @author FroznMine
 *
 */
public class GameOverEvent extends Event{
	private static final HandlerList handlers = new HandlerList();
	private final IGame game;
	
	public GameOverEvent(IGame game) {
		this.game = game;
	}
	
	public IGame getGame() {
		return game;
	}
	
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
	public static HandlerList getHandlerList() {
		return handlers;
	}
}
