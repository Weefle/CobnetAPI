package fr.cobnet.api.locations;

import fr.cobnet.api.blocks.BlockUtils;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class ZoneUtils {

    /**
     * Retourne tous les blocks d'une zone sans les blocks d'air
     *
     * @param loc > Lieu de départ
     * @param radius > Rayon
     * @param air > S'il faut ajouter les blocks d'air
     * @return Set des blocks dans le rayon
     */
    public static Set<Block> cube(Location loc, double radius, boolean air) {
        Set<Block> blocks = new HashSet<>();

        for (double x = -radius; x <= radius; x++) {
            for (double y = -radius; y <= radius; y++) {
                for (double z = -radius; z <= radius; z++) {
                    Block block = loc.clone().add(x, y, z).getBlock();
                    if (block.getType() == Material.AIR && !air)
                        continue;
                    blocks.add(block);
                }
            }
        }

        return blocks;
    }

    /**
     * Retourne tous les blocks d'une zone entre le centre et le rayon spécifié (sous forme de sphère) (même les blocks d'air)
     *
     * @param loc    > le lieu de départ
     * @param radius > le rayon
     * @param air > S'il faut ajouter les blocks d'air
     * @return > un Set de blocks entre le centre et le rayon spécifié
     */
    public static Set<Block> radius(Location loc, double radius, boolean air) {
        Set<Block> blocks = new HashSet<>();

        for (double x = -radius; x <= radius; x++)
            for (double y = -radius; y <= radius; y++)
                for (double z = -radius; z <= radius; z++) {
                    Location l = loc.clone().add(x, y, z);
                    if (l.distance(loc) > radius || (loc.getBlock().getType() == Material.AIR && !air))
                        continue;
                    blocks.add(l.getBlock());
                }
        return blocks;
    }

    /**
     * Retourne une liste de Blocks les plus hauts sur le rayon et le lieu spécifié
     *
     * @param l      > la Location centre
     * @param radius > le rayon
     * @return > une List de Blocks entre le centre et le rayon spécifié
     */
    public static List<Block> getHighestBlocksOnRadius(Location l, int radius) {
        List<Block> blocks = new ArrayList<>();
        Set<Block> allBlocks = new HashSet<>();

        for (double x = -radius; x <= radius; x++) {
            for (double z = -radius; z <= radius; z++) {
                allBlocks.add(l.clone().add(x, 0, z).getBlock());
            }
        }

        for (Block b : allBlocks) {
            blocks.add(BlockUtils.getHighestBlock(b.getLocation()));
        }

        List<Block> finalBlocks = new ArrayList<>();

        for (Block b : blocks) {
            if (!finalBlocks.contains(b)) {
                finalBlocks.add(b);
            }
        }

        return finalBlocks;
    }

    /**
     * Retourne les blocks proches du joueur dans le rayon choisi
     * @param player > Joueur
     * @param radius > Rayon
     * @return Liste des blocks
     */
    public static Set<Block> getBlocksCloseTo(Player player, int radius) {
        Set<Block> result = new HashSet<>();
        int pX = player.getLocation().getBlockX();
        int pY = player.getLocation().getBlockY();
        int pZ = player.getLocation().getBlockZ();

        for (int x = pX - radius; x <= pX + radius; x++) {
            for (int y = pY - radius; y <= pY + radius; y++) {
                for (int z = pZ - radius; z <= pZ + radius; z++) {
                    double dist = (pX - x) * (pX - x) + (pY - y) * (pY - y)
                            + (pZ - z) * (pZ - z);
                    if (dist < radius * radius)
                        result.add(player.getWorld().getBlockAt(x, y, z));
                }
            }
        }
        return result;
    }

}