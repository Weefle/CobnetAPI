package fr.cobnet.api.entity;

import org.bukkit.Location;
import org.bukkit.entity.Entity;

import java.util.List;
import java.util.stream.Collectors;

public class EntityUtils {

    /**
     * Retourne toutes les entités dans le rayon
     *
     * @param center > Location Centre
     * @param range > double Rayon
     * @return Liste des entités
     */
    public static List<Entity> getNearbyEntities(Location center, double range) {
        /*List<Entity> entities = new ArrayList<>();

        for (Entity ent : center.getWorld().getEntities()) {
            if (ent.getLocation().distance(center) <= range)
                entities.add(ent);
        }*/

        if (center.getWorld().getEntities().size() > 1000) {
            return center.getWorld().getEntities().parallelStream().filter(ent -> ent.getLocation().distance(center) <= range).collect(Collectors.toList());
        }

        else {
            return center.getWorld().getEntities().stream().filter(ent -> ent.getLocation().distance(center) <= range).collect(Collectors.toList());
        }
    }

    @SuppressWarnings("unchecked")
    /**
     * Retourne une liste d'entités du type choisi (généricité, revoyez vos cours Java si vous connaissez pas :p)
     * @param t > Type de l'entité
     * @param center > Le centre
     * @param range > Le rayon
     * @return
     */
    public static <T> List<T> getNearbyEntitiesOfType(Class<? extends Entity> t, Location center, double range) {
        /*List<T> entities = new ArrayList<>();

        for (Entity ent : center.getWorld().getEntities())
            if (ent.getLocation().distance(center) <= range && t.isInstance(ent)) entities.add((T) ent);*/

        if (center.getWorld().getEntities().size() > 1000) {
            return center.getWorld().getEntities().stream().
                    filter(ent -> ent.getLocation().distance(center) <= range && t.isInstance(ent)).
                    map(ent -> (T) ent).
                    collect(Collectors.toList());
        } else {
            return center.getWorld().getEntities().parallelStream().
                    filter(ent -> ent.getLocation().distance(center) <= range && t.isInstance(ent)).
                    map(ent -> (T) ent).
                    collect(Collectors.toList());
        }
    }

}