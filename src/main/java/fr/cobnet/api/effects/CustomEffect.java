package fr.cobnet.api.effects;

import fr.cobnet.api.misc.BasicFunctions;
import fr.cobnet.core.CobnetCore;
import org.bukkit.Bukkit;
import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @TODO helix qui reduit
 */
public class CustomEffect extends BasicFunctions {

	/**
	 * @param p Player
	 * @param loc Location
	 * @param effect ParticleEffect
     * @param time int
     */
	public static void rotationOfFourDifferentParticles(final Player p, final Location loc, final ParticleEffect effect,
			final int time) {
		new BukkitRunnable() {
			double t = 0;

			@Override
			public void run() {
				t += Math.PI / time;
				if (t >= 10) {
					cancel();
					return;
				}
				for (double phi = 0; phi <= 2 * Math.PI; phi += Math.PI / 2) {
					double x = 0.3 * (4 * Math.PI - t) * Math.sin(t + phi);
					double y = 0.2 * t;
					double z = 0.3 * (4 * Math.PI - t) * Math.cos(t + phi);
					loc.add(x, y, z);
					for (Player pls : Bukkit.getOnlinePlayers()) {
						effect.display(0, 0, 0, 0, 1, loc, pls);
					}
					loc.subtract(x, y, z);
				}

			}

		}.runTaskTimer(CobnetCore.getInstance(), 1, 1);

	}

	public static void circle(final Player player, final ParticleEffect effect, final float radius) {
		Bukkit.getScheduler().runTaskTimer(CobnetCore.getInstance(), () -> {
			for(float a = 0; a < 50; a += 0.05f) {
				double x = radius * Math.cos(a * 2 * Math.PI);
				double z = radius * Math.sin(a * 2 * Math.PI);
				effect.display(0f, 0f, 0f, 0.00001f, 1, player.getLocation().add(x, 2.15, z), player);
			}
		}, 0L, 2L);
	}

	public static void helix(final Player player, final ParticleEffect effect, final int height, final float radius) {
		Bukkit.getScheduler().runTaskTimer(CobnetCore.getInstance(), () -> {
			for(float y = 0; y < height; y += 0.05f) {
				double x = radius * Math.cos(y * 2);
				double z = radius * Math.sin(y * 2);
				effect.display(0f, 0f, 0f, 0.00001f, 1, player.getLocation().add(x, y, z), player);
			}
		}, 0L, 2L);
	}

	public static void corner(final Player player, final ParticleEffect effect, final int height, final float radius) {
		Bukkit.getScheduler().runTaskTimer(CobnetCore.getInstance(), () -> {
			for(float y = 0; y < height; y += 0.05f) {
				if(y < height / 3) {
					continue;
				}
				double x = radius * Math.cos(y * 2);
				double z = radius * Math.sin(y * 2);
				effect.display(0f, 0f, 0f, 0.00001f, 1, player.getLocation().add(x / y * 2, y / 2 - (height / 5), z / y * 2), player);
			}
		}, 0L, 2L);
	}

	/**
	 * @param loc Location
	 * @param effect1 ParticleEffect
	 * @param effect2 ParticleEffect
     */
	public static void sinusoidWave(Location loc, ParticleEffect effect1, ParticleEffect effect2) {
		new BukkitRunnable() {
			double t = Math.PI / 2;

			@Override
			public void run() {
				t += 0.1 * Math.PI;
				if (t >= 20) {
					cancel();
					return;
				}

				for (double theta = 0; theta <= 2 * Math.PI; theta += Math.PI / 16) {
					double x = t * Math.cos(theta);
					double y = 2 * Math.exp(-0.01 * t) * Math.sin(t) + 0.5;
					double z = t * Math.sin(theta);

					loc.add(x, y, z);
					effect1.display(0, 0, 0, 0, 1, loc, 100);
					loc.subtract(x, y, z);

					theta += Math.PI / 64;

					x = t * Math.cos(theta);
					y = Math.exp(-0.1 * t) * Math.sin(t) + 0.5;
					z = t * Math.sin(theta);

					loc.add(x, y, z);
					effect2.display(0, 0, 0, 0, 1, loc, 100);
					loc.subtract(x, y, z);
				}

			}
		}.runTaskTimer(CobnetCore.getInstance(), 1, 2);
	}

	/**
	 * @param p Player
	 * @param effect ParticleEffect
     * @param i in
     */
	public static void directionnalFireShootsWhoSetOnFire(final Player p, final ParticleEffect effect, final int i) {

		new BukkitRunnable() {

			org.bukkit.util.Vector dir = p.getLocation().getDirection();
			Location loc = p.getEyeLocation();
			double t = 0;

			@Override
			public void run() {
				t += 1;
				double x = dir.getX() * t;
				double y = dir.getY() * t;
				double z = dir.getZ() * t;
				loc.add(x, y, z);
				for (Player pls : Bukkit.getOnlinePlayers()) {
					effect.display(0, 0, 0, 0, 1, loc, pls);
				}

				if (t >= 7) {
					this.cancel();
				}

				for (Entity en : loc.getChunk().getEntities()) {
					if (en != p) {
						if (en.getType() == EntityType.PLAYER) {
							Player p2 = (Player) en;
							if (p2.getLocation().distance(loc) < 1.0) {
								p2.playEffect(EntityEffect.HURT);
								p2.setFireTicks(20 * i);
								this.cancel();
							}
							if (p2.getEyeLocation().distance(loc) < 1.0) {
								en.playEffect(EntityEffect.HURT);
								p2.setFireTicks(20 * i);
								this.cancel();
							}
						}
					}
				}

				if (loc.getBlock().getType().isSolid()) {
					this.cancel();
				}
				loc.subtract(x, y, z);
			}
		}.runTaskTimer(CobnetCore.getInstance(), 1, 1);
	}

}
