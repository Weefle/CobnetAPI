package fr.cobnet.api.math;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

public class VelocityUtils {

	/**
	 * Retourne un vecteur de projection d'un point A � un point B
	 * @param from
	 * @param to
	 * @return
	 */
	public static Vector getProjectionFromAToB(Location from, Location to) {
		return to.toVector().subtract(from.toVector());
	}
	
	/**
	 * Retourne un vecteur de bump (projection)
	 * @param ent
	 * @param from
	 * @param power
	 * @return
	 */
	public static Vector getBumpVector(Entity ent, Location from, double power) {
		Vector bump = ent.getLocation().toVector().subtract(from.toVector()).normalize();
		bump.multiply(power);
		return bump;
	}
	
	/**
	 * Retourne un vecteur de pull (attraction)
	 * @param from
	 * @param to
	 * @param power
	 * @return
	 */
	public static Vector getPullVector(Location from, Location to, double power) {
		Vector pull = to.toVector().subtract(from.toVector()).normalize();
		pull.multiply(power);
		return pull;
	}
	
	/**
	 * Retourne un vecteur de pull (attraction)
	 * @param entity
	 * @param to
	 * @param power
	 * @return
	 */
	public static Vector getPullVector(Entity entity, Location to, double power) {
		return getPullVector(entity.getLocation(), to, power);
	}
	
	/**
	 * Permet de projetter une entité
	 * @param ent
	 * @param from
	 * @param power
	 */
	public static void bumpEntity(Entity ent, Location from, double power) {
		ent.setVelocity(getBumpVector(ent, from, power));
	}
	
	/**
	 * Permet de projetter une entité
	 * @param entity
	 * @param from
	 * @param power
	 * @param fixedY > Fixed Y
	 */
	public static void bumpEntity(Entity entity, Location from, double power, double fixedY) {
		Vector vector = getBumpVector(entity, from, power);
		vector.setY(fixedY);
		entity.setVelocity(vector);
	}
	
	/**
	 * Permet d'attirer l'entité
	 * @param ent
	 * @param from
	 * @param power
	 * @param fixedY
	 */
	public static void pullEntity(Entity ent, Location from, double power, double fixedY) {
		Vector vector = getPullVector(ent, from, power);
		vector.setY(fixedY);
		ent.setVelocity(vector);
	}
	
	/**
	 * Retourne le vrai pitch (mc code "bug")
	 * @param pitch > Pitch, angle vertical
	 * @return
	 */
	public static double realPitch(double pitch) {
		return ((pitch + 90) * Math.PI) / 180;
	}
	
	/**
	 * Retourne le vrai yaw (mc code "bug")
	 * @param yaw > Yaw, angle horizontal
	 * @return
	 */
	public static double realYaw(double yaw) {
		return ((yaw + 90) * Math.PI) / 180;
	}
	
}