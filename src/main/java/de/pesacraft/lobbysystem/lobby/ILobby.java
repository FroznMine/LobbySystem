package de.pesacraft.lobbysystem.lobby;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.inventory.ItemStack;

import de.pesacraft.lobbysystem.Position;
import de.pesacraft.lobbysystem.lobby.event.LobbyJoinEvent;
import de.pesacraft.lobbysystem.lobby.event.LobbyLeaveEvent;
import de.pesacraft.lobbysystem.lobby.event.LobbyLeaveEvent.LeaveReason;

public abstract class ILobby extends Position implements ConfigurationSerializable, Serializable {
	protected Location tpLocation;
	
	public ILobby(final Map<String, Object> map) {
		super(PositionType.LOBBY, (String) map.get("name"));
		
		this.tpLocation = (Location) map.get("location");
	}

	public ILobby(final String positionName, final Location tpLocation, final ItemStack... items) {
		super(PositionType.LOBBY, positionName);

		this.tpLocation = tpLocation;
	}

}
