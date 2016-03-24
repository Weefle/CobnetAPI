package fr.cobnet.api.lang;

import fr.cobnet.api.math.RandomUtils;
import java.util.Collection;
import org.apache.commons.lang.Validate;

public class StringUtils {

    private static final String POSSIBLE = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    /**
     * Retourne un String aléatoire de la taille choisie
     *
     * @param size
     * @return
     */
    public static String randomString(int size) {
        Validate.isTrue(size > 0, "La taille du String aléatoire doit être supérieure à 0 !");
        String ret = "";
        for (int i = 0; i < size; i++) {
            ret += POSSIBLE.charAt(RandomUtils.randomInt(POSSIBLE.length()));
        }
        return ret;
    }

    /**
     * Retourne vrai si le String contient un caractère « digital »
     *
     * @param text
     * @return
     */
    public static boolean hasDigit(String text) {
        for (char c : text.toCharArray()) {
            if (Character.isDigit(c)) return true;
        }
        return false;
    }

    /**
     * Retourne vrai si le String est un nombre, faux s'il est supérieur à Long.MAX_VALUE ou n'est pas un String
     *
     * @param s > Le String
     * @return > Boolean
     */
    public static boolean isNumber(String s) {
        try {
            Long.parseLong(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String join(Collection<? extends Object> collection, String joiner) {
        int i = 0;
        StringBuilder builder = new StringBuilder();

        for (Object object : collection) {
            builder.append(object.toString());
            i++;
            if (i < collection.size())
                builder.append(joiner);
        }

        return builder.toString().trim();
    }

}