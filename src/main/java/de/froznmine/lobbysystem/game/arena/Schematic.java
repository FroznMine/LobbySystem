package de.froznmine.lobbysystem.game.arena;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.jnbt.ByteArrayTag;
import org.jnbt.CompoundTag;
import org.jnbt.NBTInputStream;
import org.jnbt.ShortTag;
import org.jnbt.StringTag;
import org.jnbt.Tag;

public class Schematic {
	private byte[] blocks;
	private byte[] data;
	private short width;
	private short lenght;
	private short height;

	public Schematic(byte[] blocks, byte[] data, short width, short lenght,	short height) {
		this.blocks = blocks;
		this.data = data;
		this.width = width;
		this.lenght = lenght;
		this.height = height;
	}

	public byte[] getBlocks() {
		return blocks;
	}

	public byte[] getData() {
		return data;
	}

	public short getWidth() {
		return width;
	}

	public short getLenght() {
		return lenght;
	}

	public short getHeight() {
		return height;
	}
	
	@SuppressWarnings("deprecation")
	public List<Location> paste(Location loc, Material... specialMaterials) {
		List<Location> specialFound = new ArrayList<Location>();
		
		byte[] blocks = this.getBlocks();
		byte[] blockData = this.getData();
		
		short length = this.getLenght();
		short width = this.getWidth();
		short height = this.getHeight();
			
		for (int x = 0; x < width; ++x) {
			for (int y = 0; y < height; ++y) {
				for (int z = 0; z < length; ++z) {
					int index = y * width * length + z * width + x;
					Block block = new Location(loc.getWorld(), x + loc.getX(), y + loc.getY(), z + loc.getZ()).getBlock();	
					
					if (blocks[index] == 0)
						block.setType(Material.AIR);
					else {
						switch (blocks[index]) {
						case -110:
							block.setType(Material.TRAPPED_CHEST);
							break;
						case -95:
							block.setType(Material.LEAVES_2);
							break;
						case -126:
							block.setType(Material.ENDER_CHEST);
							break;
						case -94:
							block.setType(Material.LOG_2);
							break;
						case -81:
							block.setType(Material.DOUBLE_PLANT);
							break;
						default:
							block.setTypeId(blocks[index]);
							break;
						}
						
						if (blockData[index] != 0)
							block.setData(blockData[index], true);
						
						// Lookup if block could have special meaning
						for (Material m :specialMaterials)
							if (block.getType() == m) {
								specialFound.add(block.getLocation());
								break;
							}
					}
				}
			}
		}
		
		return specialFound;
	}	
	
	public static Schematic loadSchematic(File input) throws IOException {
		return loadSchematic(new FileInputStream(input));
	}
	
	public static Schematic loadSchematic(InputStream input) throws IOException {
		NBTInputStream nbtStream = new NBTInputStream(new GZIPInputStream(input));
		
		CompoundTag schematicTag = (CompoundTag) nbtStream.readTag();
		if (!schematicTag.getName().equals("Schematic")) {
			nbtStream.close();
			throw new IllegalArgumentException("Tag \"Schematic\" does not exist or is not first");
		}
		
		Map<String, Tag> schematic = schematicTag.getValue();
		if (!schematic.containsKey("Blocks")) {
			nbtStream.close();
			throw new IllegalArgumentException("Schematic file is missing a \"Blocks\" tag");
		}
		
		short width = getChildTag(schematic, "Width", ShortTag.class).getValue();
		short length = getChildTag(schematic, "Length", ShortTag.class).getValue();
		short height = getChildTag(schematic, "Height", ShortTag.class).getValue();
		
		String materials = getChildTag(schematic, "Materials", StringTag.class).getValue();
		
		if (!materials.equals("Alpha")) {
			nbtStream.close();
			throw new IllegalArgumentException("Schematic file is not an Alpha schematic");
		}	
		
		byte[] blocks = getChildTag(schematic, "Blocks", ByteArrayTag.class).getValue();
		byte[] blockData = getChildTag(schematic, "Data", ByteArrayTag.class).getValue();
				
		nbtStream.close();
			
		return new Schematic(blocks, blockData, width, length, height);
	}

	private static <T extends Tag> T getChildTag(Map<String, Tag> items, String key, Class<T> expected) throws IllegalArgumentException {
		if (!items.containsKey(key))
			throw new IllegalArgumentException("Schematic file is missing a \"" + key + "\" tag");
		
		Tag tag = items.get(key);
		if (!expected.isInstance(tag))
			throw new IllegalArgumentException(key + " tag is not of tag type " + expected.getName());
		
		return expected.cast(tag);
	}
}
