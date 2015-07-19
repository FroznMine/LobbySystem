package de.pesacraft.lobbysystem.lobby.event;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import de.pesacraft.lobbysystem.lobby.ILobby;
import de.pesacraft.lobbysystem.user.User;

public class LobbyLeaveEvent extends Event implements Cancellable {
	public enum LeaveReason {
		DISCONNECT, OTHER;
	}

	private static final HandlerList handlers = new HandlerList();

	public static HandlerList getHandlerList() {
		return LobbyLeaveEvent.handlers;
	}

	private boolean cancelled;
	private final ILobby lobby;

	private final LeaveReason reason;

	private final User user;

	public LobbyLeaveEvent(final ILobby lobby, final User user, final LeaveReason reason) {
		this.lobby = lobby;
		this.user = user;
		this.reason = reason;
		this.cancelled = false;
	}

	public User getUser() {
		return this.user;
	}

	@Override
	public HandlerList getHandlers() {
		return LobbyLeaveEvent.handlers;
	}

	public ILobby getLobby() {
		return this.lobby;
	}

	public LeaveReason getReason() {
		return this.reason;
	}

	@Override
	public boolean isCancelled() {
		return this.cancelled;
	}

	@Override
	public void setCancelled(final boolean cancelled) {
		this.cancelled = cancelled;
	}
}
