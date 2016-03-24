package fr.cobnet.api.schematics;

import fr.cobnet.api.nbt.*;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.zip.GZIPInputStream;

/**
 * Créé par Creart le 02/01/2016.
 */
public class SchematicUtils {

    /**
     * Permet de paste une schematic
     * @param world > Le monde
     * @param loc > La location
     * @param schematic > Le schematic, chargée par {@link #loadSchematic(File)}
     */
    public static void pasteSchematic(World world, Location loc, Schematic schematic) {
        byte[] blocks = schematic.getBlocks();
        byte[] blockData = schematic.getData();

        short length = schematic.getLength();
        short width = schematic.getWidth();
        short height = schematic.getHeight();

        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                for (int z = 0; z < length; ++z) {
                    int index = y * width * length + z * width + x;
                    Block block = new Location(world, x + loc.getX(), y + loc.getY(), z + loc.getZ()).getBlock();
                    block.setTypeIdAndData(blocks[index], blockData[index], true);
                }
            }
        }
    }

    /**
     * Permet de charger une schematic, peut être paste grâce à {@link #pasteSchematic(World, Location, Schematic)}
     * @param file > Le fichier source
     * @return
     * @throws IOException
     */
    public static Schematic loadSchematic(File file) throws IOException {
        FileInputStream input = new FileInputStream(file);
        NBTInputStream nbtInput = new NBTInputStream(new BufferedInputStream(new GZIPInputStream(input)));

        CompoundTag schematic = (CompoundTag) nbtInput.readTag();
        if (!schematic.getName().equals("Schematic")) {
            throw new IllegalArgumentException("Tag \"Schematic\" does not exist or is not first");
        }

        Map<String, Tag> s = schematic.getValue();
        if (!s.containsKey("Blocks")) {
            throw new IllegalArgumentException("Schematic file is missing a \"Blocks\" tag");
        }

        short width = getChildTag(s, "Width", ShortTag.class).getValue();
        short length = getChildTag(s, "Length", ShortTag.class).getValue();
        short height = getChildTag(s, "Height", ShortTag.class).getValue();

        String materials = getChildTag(s, "Materials", StringTag.class).getValue();
        if (!materials.equals("Alpha")) {
            throw new IllegalArgumentException("Schematic file is not an Alpha schematic");
        }

        byte[] blocks = getChildTag(s, "Blocks", ByteArrayTag.class).getValue();
        byte[] blockData = getChildTag(s, "Data", ByteArrayTag.class).getValue();

        return new Schematic(blocks, blockData, width, length, height);
    }

    private static <T extends Tag> T getChildTag(Map<String, Tag> items, String key, Class<T> expected) throws IllegalArgumentException {
        if (!items.containsKey(key)) {
            throw new IllegalArgumentException("Schematic file is missing a \"" + key + "\" tag");
        }
        Tag tag = items.get(key);
        if (!expected.isInstance(tag)) {
            throw new IllegalArgumentException(key + " tag is not of tag type " + expected.getName());
        }
        return expected.cast(tag);
    }

}
