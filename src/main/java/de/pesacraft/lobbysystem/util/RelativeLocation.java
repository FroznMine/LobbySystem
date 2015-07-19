package de.pesacraft.lobbysystem.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

public class RelativeLocation implements ConfigurationSerializable, Serializable {
	private static final long serialVersionUID = -8284652863433482357L;
	private int x;
	private int y;
	private int z;
	
	public RelativeLocation(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public RelativeLocation(Map<String, Object> map) {
		this.x = (Integer) map.get("x");
		this.y = (Integer) map.get("y");
		this.z = (Integer) map.get("z");
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getZ() {
		return z;
	}

	public Location toAbsolute(Location base) {
		return base.add(x, y, z);
	}
	
	@Override
	public Map<String, Object> serialize() {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("x", x);
		map.put("y", y);
		map.put("z", z);
		
		return map;
	}
}
