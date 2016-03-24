package fr.cobnet.api.blocks;

import fr.cobnet.core.CobnetCore;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class BlockUtils {
	
	private static Map<Long, Chunk> loadedChunks = new HashMap<>();
	private static final long CHUNK_LOAD_TIME = 30000L;
	
	static
	{
		Bukkit.getScheduler().scheduleSyncRepeatingTask(CobnetCore.getInstance(), () -> {
				Iterator<Map.Entry<Long, Chunk>> iterator = loadedChunks.entrySet().iterator();
				while(iterator.hasNext()) {
					Map.Entry<Long, Chunk> chunk = iterator.next();
					if(chunk.getKey() >= System.currentTimeMillis()) {
						chunk.getValue().unload();
						iterator.remove();
					}
				}
		}, 0, 20);
	}
	
	/**
	 * Permet d'obtenir un block et de charger le chunk temporairement, s'il ne l'est pas.
	 * @param location
	 * @return
	 */
	public static Block getBlockAt(Location location) {
		Chunk chunk = location.getChunk();
		if(!chunk.isLoaded()) {
			chunk.load();
			loadedChunks.put(System.currentTimeMillis() + CHUNK_LOAD_TIME, chunk);
		}
		return location.getBlock();
	}
	
	/**
	 * Permet de récupérer le dernier block qui n'est pas de type AIR en partant de y = 0
	 * @param l > le lieu
	 * @return > le block
	 */
	public static Block getLastBlockNoAir(Location l) {
		Block previousBlock = null;
		Location customLocation = l.clone();
		for(int y = 0; y < 255; y++) {
			customLocation.setY(y);
			if(previousBlock == null) {
				previousBlock = customLocation.getBlock();
			}else{
				if(customLocation.getBlock().getType() == Material.AIR) {
					return previousBlock;
				}else{
					previousBlock = customLocation.getBlock();
				}
			}
		}
		return null;
	}
	
	/**
	 * Permet de récupérer le block le plus haut d'un lieu (alternative à getHighestBlock de Bukkit par défaut)
	 * @param l > le lieu
	 * @return > le block le plus haut
	 */
	public static Block getHighestBlock(Location l) {
		Location customLocation = l.clone();
		for(int y = 255; y > 0; y--) {
			customLocation.setY(y);
			if(customLocation.getBlock().getType() != Material.AIR) {
				return customLocation.getBlock();
			}
		}
		return null;
	}

}
