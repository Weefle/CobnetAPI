package fr.cobnet.core.anticheat;

import java.util.HashMap;
import java.util.Map;

/**
 * Créé par Creart le 16/02/2016.
 */
public abstract class AntiCheatModule {

    private static Map<String, AntiCheatModule> modules = new HashMap<>();

    public static void declare(AntiCheatModule module) {
        if ( module == null || modules.containsKey(module.getName()) ) return;

        modules.put(module.getName(), module);
    }

    public static AntiCheatModule get(String str) {
        return modules.get(str);
    }

    /**
     * Permet de désactiver tous les modules de l'AntiCheat
     *
     * Je le déconseille fortement, car cela peut facilement nuir au gameplay des joueurs
     */
    @Deprecated
    public static void disableAll() {
        modules.values().stream().forEach(AntiCheatModule::disable);
    }

    public AntiCheatModule(String name) {
        this.name = name;
        this.enabled = true;
    }

    public void disable() {
        enabled = false;
    }

    private final String name;
    private boolean enabled;

    public String getName() {
        return name;
    }

    public boolean isEnabled() {
        return enabled;
    }

}
