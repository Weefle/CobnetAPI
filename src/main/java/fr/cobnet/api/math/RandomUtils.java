package fr.cobnet.api.math;

import java.util.Random;

public class RandomUtils {
	
	private static Random random = new Random();
	
	/**
	 * Retourne un integer aléatoire
	 * @return
	 */
	public static int randomInt() {
		return random.nextInt();
	}
	
	/**
	 * Retourne un integer aléatoire avec comme maximum "max"
	 * @param max
	 * @return
	 */
	public static int randomInt(int max) {
		return random.nextInt(max);
	}
	
	/**
	 * Retourne un integer aléatoire entre "min" et "max"
	 * @param min > Minimum
	 * @param max > Maximum
	 * @return
	 */
	public static int randomInt(int min, int max) {
		if(min > max) {
			int tmp = min;
			min = max;
			max = tmp;
		}
		
		return random.nextInt((max - min) + 1 + min);
	}
	
	/**
	 * Retourne un float aléatoire
	 * @return
	 */
	public static float randomFloat() {
		return random.nextFloat();
	}
	
	/**
	 * Retourne un float aléatoire avec comme maximum "max"
	 * @param max > Maximum
	 * @return
	 */
	public static float randomFloat(float max) {
		return random.nextFloat() * max;
	}
	
	/**
	 * Retourne un float aléatoire entre "min" et "max"
	 * @param min > Minimum
	 * @param max > Maximum
	 * @return
	 */
	public static float randomFloat(float min, float max) {
		if(min > max) {
			float tmp = min;
			min = max;
			max = tmp;
		}
		return min + (float)Math.random() * (max - min);
	}
	
	/**
	 * Retourne un booléen aléatoire
	 * @return
	 */
	public static boolean randomBoolean() {
		return random.nextBoolean();
	}
	
	/**
	 * Retourne un double aléatoire
	 * @return
	 */
	public static double randomDouble() {
		return random.nextDouble();
	}
	
	/**
	 * Retourne un double aléatoire avec comme maximum "max"
	 * @param max > Maximum
	 * @return
	 */
	public static double randomDouble(double max) {
		return random.nextDouble() * max;
	}
	
	/**
	 * Retourne un double aléatoire entre "min" et "max"
	 * @param min > Minimum
	 * @param max > Maximum
	 * @return
	 */
	public static double randomDouble(double min, double max) {
		if(min > max) {
			double tmp = min;
			min = max;
			max = tmp;
		}
		return random.nextDouble() * max;
	}
	
}
