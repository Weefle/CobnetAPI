package fr.cobnet.core.database;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bukkit.entity.Player;

import java.util.Map;

/**
 * Créé par Creart le 27/01/2016.
 */
public abstract class Database {
    
    private static Database instance;

    public static Database getInstance() {
        return instance;
    }

    public static void setInstance(Database i) {
        instance = i;
    }

    public Database() {
        Database.setInstance(this);
    }

    /**
     *
     * @param player Player
     * @param field String
     * @return la valeur du paramètre spécifié en base de données
     */
    public abstract Object getSetting(Player player, String field);

    /**
     * Change le paramètre demandé
     * @param player Player
     * @param field String
     * @param value Object (tout type d'objet)
     */
    public abstract void setSetting(Player player, String field, Object value);

    /**
     *
     * @param player Player
     * @param fields String...
     * @return Les paramètres demandés
     */
    public abstract Map<String, Object> getSettings(Player player, String... fields);

    /**
     * Change les paramètres demandés
     * @param player Player
     * @param settings Map<String, Object>
     */
    public abstract void setSettings(Player player, Map<String, Object> settings);

    /**
     * Permet d'obtenir la collection demandée
     * @param collection > Le nom de la MongoCollection
     * @return MongoCollection
     */
    public abstract MongoCollection<?> getCollection(String collection);

    /**
     * Permet de faire une query
     * @param search > BasicDBObject avec tous les paramètres
     * @param collection > DBCollection, la collection où il faut chercher
     * @return MongoCursor > Résultat de la recherche, ne pas oublier de faire {@link MongoCursor#close()}
     */
    public abstract MongoCursor<?> fetch(BasicDBObject search, MongoCollection<?> collection);

}
