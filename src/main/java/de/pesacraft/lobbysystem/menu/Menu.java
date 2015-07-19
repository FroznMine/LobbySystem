package de.pesacraft.lobbysystem.menu;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.configuration.serialization.ConfigurationSerializable;

public class Menu implements Serializable, ConfigurationSerializable {
	private char[][] inventory;

	private char shortcut;
	private String title;

	@Override
	public Map<String, Object> serialize() {
		final Map<String, Object> map = new HashMap<String, Object>();

		return map;
	}

}
