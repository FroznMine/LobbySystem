package de.froznmine.lobbysystem.lobby;

import java.util.Collection;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import de.froznmine.lobbysystem.Position;
import de.froznmine.lobbysystem.game.GameUser;
import de.froznmine.lobbysystem.lobby.event.LobbyJoinEvent;
import de.froznmine.lobbysystem.lobby.event.LobbyLeaveEvent;
import de.froznmine.lobbysystem.lobby.event.LobbyLeaveEvent.LeaveReason;

public abstract class ILobby extends Position {
	protected Map<GameUser, Vote> votes;
	protected Location tpLocation;
	
	public Collection<GameUser> getPlayers() {
		return this.votes.keySet();
	}
	
	public boolean join(GameUser u) {
		LobbyJoinEvent event = new LobbyJoinEvent(this, u);
		
		Bukkit.getPluginManager().callEvent(event);
		
		if (event.isCancelled()) return false;
		
		votes.put(u, null);
		
		u.teleport(tpLocation);
		
		// TODO inv change usw
		return true;
	}
	
	public boolean leave(GameUser u) {
		LobbyLeaveEvent event = new LobbyLeaveEvent(this, u, null); // TODO getReason
		
		Bukkit.getPluginManager().callEvent(event);
		
		if (event.isCancelled() && event.getReason() != LeaveReason.DISCONNECT) return false;
		
		votes.remove(u);
		
		u.leave();
		return true;
	}
}
