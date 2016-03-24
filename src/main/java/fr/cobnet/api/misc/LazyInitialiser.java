package fr.cobnet.api.misc;

/**
 * Créé par Creart le 25/01/2016.
 * Lazy initialiser (écrit à l'anglaise :p)
 */
public abstract class LazyInitialiser<T> {

    private volatile T value;

    public T get() {
        if (value == null) {
            synchronized (this) {
                value = initialise();
            }
        }

        return value;
    }

    public abstract T initialise();

}
