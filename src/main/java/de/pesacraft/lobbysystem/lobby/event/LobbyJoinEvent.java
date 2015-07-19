package de.pesacraft.lobbysystem.lobby.event;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import de.pesacraft.lobbysystem.lobby.ILobby;
import de.pesacraft.lobbysystem.user.User;

public class LobbyJoinEvent extends Event implements Cancellable {
	private static final HandlerList handlers = new HandlerList();

	public static HandlerList getHandlerList() {
		return LobbyJoinEvent.handlers;
	}

	private boolean cancelled;
	private final ILobby lobby;

	private final User user;

	public LobbyJoinEvent(final ILobby lobby, final User user) {
		this.lobby = lobby;
		this.user = user;
		this.cancelled = false;
	}

	public User getUser() {
		return this.user;
	}

	@Override
	public HandlerList getHandlers() {
		return LobbyJoinEvent.handlers;
	}

	public ILobby getLobby() {
		return this.lobby;
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
