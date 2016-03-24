package fr.cobnet.api.nbt;

/**
 * Créé par Creart le 02/01/2016.
 */
public abstract class Tag {

    /**
     * Le nom du tag
     */
    private final String name;

    /**
     * Constructeur par défaut
     * @param name
     */
    public Tag(String name) {
        this.name = name;
    }

    /**
     *
     * @return Le nom du tag
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return La valeur du tag
     */
    public abstract Object getValue();

}
