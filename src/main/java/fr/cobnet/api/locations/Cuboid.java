package fr.cobnet.api.locations;

import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Oscar
 *
 */
public final class Cuboid {
	
	private String worldName;
	private Location from, to;
	private Location realFrom, realTo;
	
	public Cuboid(Location from, Location to) {
		Validate.notNull(from);
		Validate.notNull(to);
		Validate.isTrue(from.getWorld().getName().equals(to.getWorld().getName()));
		
		this.worldName = from.getWorld().getName();
		this.from = from;
		this.to = to;
		this.realLocs();
	}
	
	/** GETTERS **/
	
	public Location getFrom() {
		return this.from;
	}
	
	public Location getTo() {
		return this.to;
	}
	
	public Location getRealFrom() {
		return this.realFrom;
	}
	
	public Location getRealTo() {
		return this.realTo;
	}
	
	public String getWorldName() {
		return this.worldName;
	}
	
	public World getWorld() {
		return Bukkit.getWorld(this.worldName);
	}
	
	public List<Block> getBlocks(boolean air) {
		List<Block> res = new ArrayList<>();
		
		for(int x = this.realFrom.getBlockX(); x < this.realTo.getBlockX();x++)
			for(int y = this.realFrom.getBlockY(); y < this.realTo.getBlockY(); y++)
				for(int z = this.realFrom.getBlockZ(); z < this.realTo.getBlockZ(); z++) {
					Block b = this.realFrom.getWorld().getBlockAt(x,y,z);
					if((b == null || b.getType() == Material.AIR) && !air) continue;
					res.add(b);
				}
		
		return res;
	}
	
	/** SETTERS **/
	
	/** PRIVATE **/
	
	private void realLocs() {
		this.realFrom = new Location(this.from.getWorld(), 0, 0, 0);
		this.realTo = new Location(this.from.getWorld(), 0, 0, 0);
		
		this.realFrom.setX(Math.min(this.from.getX(), this.to.getX()));
		this.realFrom.setY(Math.min(this.from.getY(), this.to.getY()));
		this.realFrom.setZ(Math.min(this.from.getZ(), this.to.getZ()));
		
		this.realTo.setX(Math.max(this.from.getX(), this.to.getX()));
		this.realTo.setY(Math.max(this.from.getY(), this.to.getY()));
		this.realTo.setZ(Math.max(this.from.getZ(), this.to.getZ()));
	}
	
}
