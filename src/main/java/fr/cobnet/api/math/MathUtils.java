package fr.cobnet.api.math;

import java.util.ArrayList;
import java.util.List;

public class MathUtils {

	/**
	 * Vrai si la valeur est compris entre la valeur minimale et la valeur maximale
	 * @param value > Valeur à tester
	 * @param min > Valeur minimale
	 * @param max > Valeur maximale
	 * @return Boolean
	 */
	public static boolean isInRange(double value, double min, double max) {
		return value > min && value < max;
	}
	
	/**
	 * Retourne la liste des diviseurs
	 * @param number > Nombre 
	 * @return > List des entiers
	 */
	public static List<Integer> getDividers(int number) {
		List<Integer> ret = new ArrayList<>();
		
		for (int i = 1; i <= number; i++) {
			if(number % i == 0) ret.add(i);
		}
		
		return ret;
	}

	/**
	 * Retourne la valeur initiale s'il est comprise entre les deux valeurs, dans les autres cas :
	 * - si elle est supérieure à la maximale, retourne la valeur maximale ;
	 * - si elle est inférieure à la minimale, retourne la valeur minimale.
	 * @param value > double Valeur
	 * @param min > double Valeur minimale
	 * @param max > double Valeur Maximale
	 * @return double > Clamp
	 */
	public static double clamp(double value, double min, double max)
	{
		return Math.min(min, Math.min(max, value));
	}
	
	/**
	 * Retourne le plus grand commun diviseur
	 * @param first
	 * @param second
	 * @return
	 */
	public static int pgcd(int first, int second) {
		int a = 0, b = 0;
	    if (first > second) {
	       a = first;
	       b = second;
	    } else {
	       a = second;
	       b = first;
	    }

	    int r = a%b;
	    if (r == 0)
	        return b;
	    else 
	        return pgcd(b, r);
	}
	
}
