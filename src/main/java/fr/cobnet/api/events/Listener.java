package fr.cobnet.api.events;

import fr.cobnet.api.misc.BasicFunctions;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public abstract class Listener extends BasicFunctions implements org.bukkit.event.Listener {

    public static void register(Plugin plugin, org.bukkit.event.Listener... listeners) {
        for(org.bukkit.event.Listener listener : listeners) {
            Bukkit.getPluginManager().registerEvents(listener, plugin);
        }
    }
}