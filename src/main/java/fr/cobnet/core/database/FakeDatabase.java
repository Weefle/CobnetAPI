package fr.cobnet.core.database;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import fr.cobnet.api.events.Listener;
import fr.cobnet.core.CobnetCore;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Créé par Creart le 27/01/2016.
 */
public class FakeDatabase extends Database {

    private Map<UUID, Map<String, Object>> settings;

    public FakeDatabase() {
        super();
        settings = new HashMap<>();
        Listener.register(CobnetCore.getInstance(), new Listener() {
            @EventHandler
            public void onQuit(PlayerQuitEvent event) {
                if (Bukkit.getOnlinePlayers().size() > 50)
                    settings.remove(event.getPlayer().getUniqueId());
            }
        });
    }

    @Override
    public Object getSetting(Player player, String field) {
        return settings.get(player.getUniqueId()).get(field);
    }

    @Override
    public void setSetting(Player player, String field, Object value) {
        if (!settings.containsKey(player.getUniqueId())) {
            Map<String, Object> f = new HashMap<>();
            f.put(field, value);
            settings.put(player.getUniqueId(), f);
        } else
            settings.get(player.getUniqueId()).put(field, value);
    }

    @Override
    public Map<String, Object> getSettings(Player player, String... fields) {

        Map<String, Object> ret = new HashMap<>();

        if (settings.containsKey(player.getUniqueId())) {
            Map<String, Object> sett = settings.get(player.getUniqueId());
            for (String str : fields) {
                ret.put(str, sett.get(str));
            }
        }

        return ret;
    }

    @Override
    public void setSettings(Player player, Map<String, Object> sett) {
        if (!settings.containsKey(player.getUniqueId())) {
            settings.put(player.getUniqueId(), sett);
        } else {
            settings.get(player.getUniqueId()).putAll(sett);
        }
    }

    @Override
    public MongoCollection getCollection(String collection) {
        return null;
    }

    @Override
    public MongoCursor fetch(BasicDBObject search, MongoCollection collection) {
        return null;
    }

}
