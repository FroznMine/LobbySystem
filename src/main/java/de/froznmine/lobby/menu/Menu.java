package de.froznmine.lobby.menu;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.configuration.serialization.ConfigurationSerializable;

public class Menu implements Serializable, ConfigurationSerializable {
	private String title;
	
	private char shortcut;
	private char[][] inventory;
	
	@Override
	public Map<String, Object> serialize() {
		Map<String, Object> map = new HashMap<String, Object>();
		
		return map;
	}

}
