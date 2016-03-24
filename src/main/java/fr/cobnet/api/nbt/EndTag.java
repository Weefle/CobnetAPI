package fr.cobnet.api.nbt;

/**
 * Créé par Creart le 02/01/2016.
 */
public class EndTag extends Tag {

    public EndTag() {
        super("");
    }

    @Override
    public Object getValue() {
        return null;
    }

    @Override
    public String toString() {
        return "TAG_End";
    }

}
