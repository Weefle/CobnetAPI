package fr.cobnet.api.lang;

import java.util.ArrayList;
import java.util.List;

/**
 * Créé par Creart le 03/01/2016.
 */
public class MoreArrays {

    public static <T> List<T> asList(T[] ts) {
        List<T> list = new ArrayList<>();
        for (T t : ts)
            list.add(t);
        return list;
    }

}
