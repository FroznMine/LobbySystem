package de.froznmine.lobbysystem.game.event;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import de.froznmine.lobbysystem.game.IGame;

/**
 * A event which is called when the specific game starts.
 * It's time to teleport the players into the arena and start
 * a countdown.
 * Players should be given their items.
 * 
 * Game doesn't starts when event is cancelled.
 * 
 * @author FroznMine
 *
 */
public class GameStartEvent extends Event implements Cancellable {
	private static final HandlerList handlers = new HandlerList();
	private final IGame game;
	private boolean cancelled;
	
	public GameStartEvent(IGame game) {
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

	@Override
	public boolean isCancelled() {
		return cancelled;
	}

	@Override
	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}

}
