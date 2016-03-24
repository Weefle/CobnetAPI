package fr.cobnet.api.players;

import fr.cobnet.api.entity.EntityUtils;
import net.minecraft.server.v1_8_R3.AxisAlignedBB;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SightUtils {

	/**
	 * Retourne le joueur le plus proche dans l'angle de vue et dans le rayon
	 * (retourne null si il n'y a aucun joueur)
	 * 
	 * @param p
	 *            > le joueur centre
	 * @param range
	 *            > le rayon
	 * @return
	 */
	public static Player getNearestPlayerInSight(Player p, int range) {
		List<Player> players = EntityUtils.getNearbyEntitiesOfType(Player.class, p.getLocation(), 50);
		
		if(players == null || players.isEmpty()) {
			return null;
		}

		List<Block> blocksInSight = p.getLineOfSight((Set<Material>) null,
				range);
		for(Block block : blocksInSight) {
			if (block.getType() != Material.AIR && block.getType() != Material.GRASS && block.getType() != Material.LONG_GRASS) {
				break;
			}

			Location low = block.getLocation();
			Location high = low.clone().add(1, 1, 1);
			AxisAlignedBB blockBoundingBox = AxisAlignedBB.a(low.getX(),
					low.getY(), low.getZ(), high.getX(), high.getY(),
					high.getZ());

			for (Player potentialEnnemy : players) {
				if(potentialEnnemy.getLocation().distance(p.getEyeLocation()) <= range	&& ((CraftEntity) potentialEnnemy).getHandle().getBoundingBox().b(blockBoundingBox)) {
					if(!potentialEnnemy.equals(p)) return potentialEnnemy;
				}
			}
		}
		return null;
	}
	
	/**
	 * Retourne le block le plus proche dans le rayon et la direction du joueur
	 * (retourne null si il n'y a aucun block (ou seulement des blocks d'air, de grass...)
	 * 
	 * @param ent
	 *            > le joueur centre
	 * @param range
	 *            > le rayon
	 * @return
	 */
	public static Block getNearestBlockInSight(LivingEntity ent, int range, Collection<Material> ignoredBlocks) {
		Set<Material> ignored = new HashSet<Material>(ignoredBlocks);
		List<Block> blocksInSight = ent.getLineOfSight(ignored, range);
		for(Block block : blocksInSight) {
			// System.out.println("checking : " + block);
			boolean isOk = true;
			for(Material m : ignoredBlocks) {
				// System.out.println("ignored material : " + m);
				if(m == block.getType()) {
					isOk = false;
				}
			}
			if(isOk) {
				// System.out.println(block + "ok");
				return block;
			}
		}
		return null;
	}
	
}
