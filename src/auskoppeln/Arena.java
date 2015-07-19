package de.froznmine.lobbysystem.game.arena;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

import de.froznmine.lobbysystem.game.GamePlugin;
import de.froznmine.lobbysystem.game.GameUser;
import de.froznmine.lobbysystem.util.FileBundle;

public abstract class Arena implements ConfigurationSerializable, Serializable {
	private Schematic schematic;
	
	public Arena(GamePlugin plugin, String name) throws IOException {
		File file = new File(plugin.getDataFolder() + "arenas" + File.separator + name + ".are");
		Map<String, ByteArrayInputStream> map = FileBundle.unzipFile(file);
		this.schematic = Schematic.loadSchematic(map.get("schematic.schem"));
		
	}
	
	public Arena(Schematic schematic) {
		this.schematic = schematic;
	}
	
	/** Loads the arena and needed stuff at the specific location.<br>
	 * Also has to load and set chest contents.
	 * 
	 * @param loc The location where the arena should be loaded
	 * @return true if it was loaded successful, false otherwise
	 */
	public boolean load(Location loc) {
		schematic.paste(loc);
		
		return true;
	}
	
	/** Deletes the arena.<br>
	 * Should remove dropped items, and replace everything with air.
	 * 
	 * @return true if the deletion was successful, false otherwise
	 */
	public boolean delete(Location loc) {
		Vector one = loc.toVector();
		Vector two = loc.clone().add(schematic.getWidth(), schematic.getHeight(), schematic.getLength()).toVector();
		
		for (Entity e : loc.getWorld().getEntities()) {
			Vector vec = e.getLocation().toVector();
			
			if (vec.isInAABB(one, two))
				e.remove();
		}
		
		schematic.clear(loc);
		
		return true;
	}
	
	/** Get the maximum amount of players.
	 * 
	 * @return The maximum amount of players
	 */
	public abstract int getMaxPlayers();
	
	/** Get the required amount of players to start the game.
	 * 
	 * @return The required amount of players
	 */
	public abstract int getRequiredPlayers();
	
	/** Teleports the specific player to the next spawn location.<br>
	 * Teams and other aspects have to be handled.
	 * 
	 * @param player The player to teleport
	 * @return true if teleport was successful, false otherwise
	 */
	public abstract boolean teleport(GameUser player);
}
