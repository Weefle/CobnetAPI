package fr.cobnet.api.nbt;

/**
 * Créé par Creart le 02/01/2016.
 */
public class FloatTag extends Tag {

    private final float value;

    public FloatTag(String name, float value) {
        super(name);
        this.value = value;
    }

    @Override
    public Float getValue() {
        return value;
    }

    @Override
    public String toString() {
        String name = getName();
        String append = "";
        if(name != null && !name.equals("")) {
            append = "(\"" + this.getName() + "\")";
        }
        return "TAG_Float" + append + ": " + value;
    }

}
