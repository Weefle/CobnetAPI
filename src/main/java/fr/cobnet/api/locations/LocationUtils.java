package fr.cobnet.api.locations;

import org.bukkit.Location;

public class LocationUtils {
	
	private static LocationUtils instance;
	
	/**
	 * J'avais besoin d'un singleton ^^ - Creart
	 * @return
	 */
	public static LocationUtils getInstance() {
		return instance == null ? instance = new LocationUtils() : instance;
	}
	
	private LocationUtils() {}
	
	public boolean isBetween(Location from, Location to, Location where) {
		boolean x1 = from.getX() < where.getX();
		boolean y1 = from.getY() < where.getY();
		boolean z1 = from.getZ() < where.getZ();
		
		boolean x2 = to.getX() > where.getX();
		boolean y2 = to.getY() > where.getY();
		boolean z2 = to.getZ() > where.getZ();
		
		return x1 && y1 && z1 && x2 && y2 && z2;
	}
	
	public static boolean isBetweenTwoLocations(Location from, Location to, Location where) {
		boolean x1 = from.getX() < where.getX();
		boolean y1 = from.getY() < where.getY();
		boolean z1 = from.getZ() < where.getZ();
		
		boolean x2 = to.getX() > where.getX();
		boolean y2 = to.getY() > where.getY();
		boolean z2 = to.getZ() > where.getZ();
		
		return x1 && y1 && z1 && x2 && y2 && z2;
	}

}
