package fr.cobnet.api.misc;

import java.util.regex.Pattern;

/**
 * Créé par Creart le 22/01/2016.
 */
public class RegexUtils {

    private static final Pattern UUID_MATCHER, PSEUDO_MATCHER;

    static
    {
        UUID_MATCHER = Pattern.compile("[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}");
        PSEUDO_MATCHER = Pattern.compile("/^[A-Za-z0-9_-]$/");
    }

    public static boolean isPseudonyme(String str) {
        return str != null && str.length() > 0 && PSEUDO_MATCHER.matcher(str).matches();
    }

    public static boolean isUniqueId(String str) {
        return str != null && str.length() > 0 && UUID_MATCHER.matcher(str).matches();
    }

}
