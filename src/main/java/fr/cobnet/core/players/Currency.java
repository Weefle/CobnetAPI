package fr.cobnet.core.players;

/**
 * Créé par Creart le 01/01/2016.
 */
public enum Currency {

    THUNES("Thunes"), SPECIAL_COINS("Special Coins");

    private final String name;

    Currency(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
