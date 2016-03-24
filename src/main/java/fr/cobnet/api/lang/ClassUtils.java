package fr.cobnet.api.lang;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Créé par Creart le 17/02/2016.
 */
public class ClassUtils {

    private static final Logger LOGGER = Logger.getLogger(ClassUtils.class.getSimpleName());

    public static boolean loadClass(String className) {
        try {
            Class.forName(className);
            return true;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Couldn't find class: \"" + className + "\"");
            return false;
        }
    }

}
