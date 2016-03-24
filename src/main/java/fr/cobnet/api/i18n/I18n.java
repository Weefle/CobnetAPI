package fr.cobnet.api.i18n;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.InputStream;
import java.text.MessageFormat;

/**
 * Created by Rémi on 14/01/2016.
 */
public class I18n {

    /**
     * Permet d'ajouter le support d'une langue à un plugin.
     * @param plugin Plugin
     * @param lang Lang
     */
    public static void supportTranslate(Plugin plugin, Lang lang) {
        InputStream langIn = plugin.getResource("lang."+ lang.getCode().toLowerCase() +".yml");
        if(langIn == null) {
            plugin.getLogger().warning("Le fichier lang."+ lang.getCode().toLowerCase() +".yml n'existe pas !");
            return;
        }
        FileConfiguration configuration = YamlConfiguration.loadConfiguration(langIn);
        for(String l : configuration.getRoot().getKeys(true)) {
            lang.getWords().put(l, configuration.getString(l));
        }
    }

    /**
     * Retourne la valeur, si la clef n'est pas trouvé elle renvoie la clef.
     * @param key String
     * @param values String...
     * @return String
     */
    public static String tl(Lang lang, String key, String... values) {
        if(lang.getWords().containsKey(key)) {
            String text = lang.getWords().get(key);
            MessageFormat messageFormat = new MessageFormat(text);
            text = text.replaceAll("\\{(\\D*?)\\}", "\\[$1\\]");
            return messageFormat.format(values);
        }
        return key;
    }
}
