package fr.cobnet.api.misc;

import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.Location;

/**
 * Created by remi on 24/01/2016.
 * @TODO
 */
public class SerializerUtils {
    /**
     * Permet d'obtenir une chaîne de caractère (String) qui contient les données de la location
     * Peut être désérialisé (on peut réobtenir une location) grâce à la méthode {@link #deserializeLocation(String)}
     * @param location
     * @return
     */
    public static String serializeLocation(Location location) {
        Validate.notNull(location, "La location ne peut être null");

        StringBuilder builder = new StringBuilder();
        builder.append(location.getWorld().getName()).append(";").append(location.getBlockX()).append(";").append(location.getBlockY()).append(";").append(location.getBlockZ());
        return builder.toString().trim();
    }

    /**
     * Permet d'obtenir une location à partir d'une sérialisation faite par la méthode {@link #serializeLocation(Location)}
     * @param string
     * @return
     */
    public static Location deserializeLocation(String string) {
        Validate.notNull(string);
        String[] bits = string.split(";");
        Validate.isTrue(bits.length > 3, "La sérialisation de la location est incorrecte");

        return new Location(Bukkit.getWorld(bits[0]), Integer.parseInt(bits[1]), Integer.parseInt(bits[2]), Integer.parseInt(bits[3]));
    }

    /**
     *
     * @param ticks > Nombre de ticks à convertir
     * @return Les ticks en millisecondes
     */
    public static long toMillisFromTicks(int ticks) {
        return ticks*50;
    }

    /**
     *
     * @param millis > Nombre de millisecondes à convertir
     * @return Les millisecondes en ticks
     */
    public static long toTicksFromMillis(long millis) {
        return millis/50;
    }
}
