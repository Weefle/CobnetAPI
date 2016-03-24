package fr.cobnet.api.nbt;

/**
 * Créé par Creart le 02/01/2016.
 */
public class IntTag extends Tag {

    private int value;

    public IntTag(String name, int value) {
        super(name);
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public String toString() {
        String name = getName();
        String sep = "";

        if (name != null && name.length() > 0)  {
            sep = "(\""+name+"\")";
        }

        return "TAG_Int"+sep+": "+value;
    }
}
