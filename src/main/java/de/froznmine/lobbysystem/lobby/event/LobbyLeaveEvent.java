package de.froznmine.lobbysystem.lobby.event;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import de.froznmine.lobbysystem.game.GameUser;
import de.froznmine.lobbysystem.lobby.ILobby;

public class LobbyLeaveEvent extends Event implements Cancellable {
	private static final HandlerList handlers = new HandlerList();
	
	private final ILobby lobby;
	private final GameUser user;
	private final LeaveReason reason;
	private boolean cancelled;
	
	public LobbyLeaveEvent(ILobby lobby, GameUser user, LeaveReason reason) {
		this.lobby = lobby;
		this.user = user;
		this.reason = reason;
		this.cancelled = false;
	}
	
	public ILobby getLobby() {
		return this.lobby;
	}
	
	public GameUser getGameUser() {
		return this.user;
	}
	
	public LeaveReason getReason() {
		return this.reason;
	}
	
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
	
	public enum LeaveReason {
		DISCONNECT,
		OTHER;
	}
}
