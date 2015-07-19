package de.pesacraft.lobbysystem.util;

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
	private static <T extends Tag> T getChildTag(final Map<String, Tag> items,
			final String key, final Class<T> expected)
			throws IllegalArgumentException {
		if (!items.containsKey(key))
			throw new IllegalArgumentException("Schematic file is missing a \""
					+ key + "\" tag");

		final Tag tag = items.get(key);
		if (!expected.isInstance(tag))
			throw new IllegalArgumentException(key + " tag is not of tag type "
					+ expected.getName());

		return expected.cast(tag);
	}

	public static Schematic loadSchematic(final File input) throws IOException {
		return Schematic.loadSchematic(new FileInputStream(input));
	}

	public static Schematic loadSchematic(final InputStream input)
			throws IOException {
		final NBTInputStream nbtStream = new NBTInputStream(
				new GZIPInputStream(input));

		final CompoundTag schematicTag = (CompoundTag) nbtStream.readTag();
		if (!schematicTag.getName().equals("Schematic")) {
			nbtStream.close();
			throw new IllegalArgumentException(
					"Tag \"Schematic\" does not exist or is not first");
		}

		final Map<String, Tag> schematic = schematicTag.getValue();
		if (!schematic.containsKey("Blocks")) {
			nbtStream.close();
			throw new IllegalArgumentException(
					"Schematic file is missing a \"Blocks\" tag");
		}

		final short width = Schematic.getChildTag(schematic, "Width",
				ShortTag.class).getValue();
		final short length = Schematic.getChildTag(schematic, "Length",
				ShortTag.class).getValue();
		final short height = Schematic.getChildTag(schematic, "Height",
				ShortTag.class).getValue();

		final String materials = Schematic.getChildTag(schematic, "Materials",
				StringTag.class).getValue();

		if (!materials.equals("Alpha")) {
			nbtStream.close();
			throw new IllegalArgumentException(
					"Schematic file is not an Alpha schematic");
		}

		final byte[] blocks = Schematic.getChildTag(schematic, "Blocks",
				ByteArrayTag.class).getValue();
		final byte[] blockData = Schematic.getChildTag(schematic, "Data",
				ByteArrayTag.class).getValue();

		nbtStream.close();

		return new Schematic(blocks, blockData, width, length, height);
	}

	private final byte[] blocks;
	private final byte[] data;

	private final short height;

	private final short length;

	private final short width;

	public Schematic(final byte[] blocks, final byte[] data, final short width,
			final short length, final short height) {
		this.blocks = blocks;
		this.data = data;
		this.width = width;
		this.length = length;
		this.height = height;
	}

	public boolean clear(final Location loc) {
		for (int x = 0; x < this.width; x++)
			for (int y = 0; y < this.height; y++)
				for (int z = 0; z < this.length; z++) {
					final Block b = new Location(loc.getWorld(),
							loc.getX() + x, loc.getY() + y, loc.getZ() + z)
							.getBlock();

					b.getDrops().clear();

					b.setType(Material.AIR);
				}

		return true;
	}

	public byte[] getBlocks() {
		return this.blocks;
	}

	public byte[] getData() {
		return this.data;
	}

	public short getHeight() {
		return this.height;
	}

	public short getLength() {
		return this.length;
	}

	public short getWidth() {
		return this.width;
	}

	@SuppressWarnings("deprecation")
	public List<Location> paste(final Location loc,
			final Material... specialMaterials) {
		final List<Location> specialFound = new ArrayList<Location>();

		final byte[] blocks = this.getBlocks();
		final byte[] blockData = this.getData();

		final short length = this.getLength();
		final short width = this.getWidth();
		final short height = this.getHeight();

		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++)
				for (int z = 0; z < length; z++) {
					final int index = y * width * length + z * width + x;
					final Block block = new Location(loc.getWorld(), x
							+ loc.getX(), y + loc.getY(), z + loc.getZ())
							.getBlock();

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
						for (final Material m : specialMaterials)
							if (block.getType() == m) {
								specialFound.add(block.getLocation());
								break;
							}
					}
				}

		return specialFound;
	}
}
