package fr.cobnet.api.blocks;

import fr.cobnet.api.entity.EntityUtils;
import java.util.List;
import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.IBlockData;
import net.minecraft.server.v1_8_R3.PacketPlayOutBlockAction;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class ChestUtils {

    public static final int VIEW_DISTANCE = Bukkit.getServer().getViewDistance() * 16;

    /**
     * Permet de montrer a un joueur comme quoi un coffre s'ouvre (aucun impact sur le jeu)
     *
     * @param player   > Le joueur qui doit voir l'animation
     * @param location > La location du block (coffre)
     */
    public static void playOpenChestAnimationToPlayer(Player player, Location location) {
        Block block = location.getBlock();
        if (block.getType() != Material.CHEST) return;
        BlockPosition position = new BlockPosition(location.getX(), location.getY(), location.getZ());
        IBlockData data = ((CraftWorld) location.getWorld()).getHandle().getType(position);
        PacketPlayOutBlockAction packet = new PacketPlayOutBlockAction(position, data.getBlock(), 1, 54);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }

    /**
     * Permet de montrer aux joueurs comme quoi un coffre s'ouvre (aucun impact sur le jeu)
     *
     * @param location > La location du block (coffre)
     */
    public static void playOpenChestAnimation(Location location) {
        Block block = location.getBlock();
        if (block.getType() != Material.CHEST) return;
        BlockPosition position = new BlockPosition(block.getX(), block.getY(), block.getZ());
        IBlockData data = ((CraftWorld) block.getWorld()).getHandle().getType(position);
        final PacketPlayOutBlockAction packet = new PacketPlayOutBlockAction(position, data.getBlock(), 1, 54);
		List<Player> players = EntityUtils.getNearbyEntitiesOfType(Player.class, location, VIEW_DISTANCE);
        for (Player player : players)
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }

}