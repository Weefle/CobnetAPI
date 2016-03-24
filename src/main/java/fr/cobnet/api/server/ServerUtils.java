package fr.cobnet.api.server;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;

/**
 * Créé par Creart le 15/02/2016.
 */
public class ServerUtils {

    public static double getServerLagAverage() {
        double recentTps[] = ((CraftServer) Bukkit.getServer()).getHandle().getServer().recentTps;
        double result = 0;

        for (double d : recentTps)
            result += d;

        return result / recentTps.length;
    }

    public static void setOnlineMode(boolean b) {
        ((CraftServer) Bukkit.getServer()).getHandle().getServer().setOnlineMode(b);
    }

}
