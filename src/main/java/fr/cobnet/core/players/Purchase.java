package fr.cobnet.core.players;

/**
 * Cette classe permet d'avoir le level d'un achat, l'id de l'achat, le nom du système (jeu, lobby, etc.)
 * -> Il faut que vous enregistriez chaque id de kit avec un enum
 */
public final class Purchase {

    private String systemName;
    private int boughtId;
    private int level;

    public Purchase(String systemName, int boughtId, int level) {
        this.systemName = systemName;
        this.boughtId = boughtId;
        this.level = level;
    }

    public String getSystemName() {
        return this.systemName;
    }

    public int getBoughtId() {
        return this.boughtId;
    }

    public int getLevel() {
        return this.level;
    }

}
