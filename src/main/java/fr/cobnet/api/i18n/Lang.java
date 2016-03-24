package fr.cobnet.api.i18n;

import java.util.HashMap;

/**
 * Created by Rémi on 14/01/2016.
 */
public enum Lang {
    FRENCH(0, "Français", "FR"),
    ENGLISH(1, "Anglais", "EN");

    private int id;
    private String name;
    private String code;
    private HashMap<String, String> words;

    Lang(int id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.words = new HashMap<String, String>();
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getCode() {
        return this.code;
    }

    public HashMap<String, String> getWords() {
        return this.words;
    }
}
