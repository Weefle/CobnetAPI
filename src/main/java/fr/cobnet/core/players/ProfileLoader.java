package fr.cobnet.core.players;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedInputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.UUID;
import java.util.logging.Level;

/**
 * Créé par Creart le 03/01/2016.
 * Thanks to lenis0012
 * (edited by Creart)
 */
public class ProfileLoader {

    private final String uuid, name, skinOwner;

    public ProfileLoader(String uuid, String name) {
        this(uuid, name, name);
    }

    public ProfileLoader(String uuid, String name, String skinOwner) {
        Validate.notNull(uuid);
        Validate.notNull(name);
        Validate.notNull(skinOwner);
        Validate.isTrue(name.length() > 0 && name.length() < 16);

        this.uuid = uuid;
        this.name = name;
        this.skinOwner = skinOwner;
    }

    public GameProfile loadProfile() {
        UUID uid = uuid == null ? parseUUID(getUUID(name)) : parseUUID(uuid);
        GameProfile profile = new GameProfile(uid, name);
        addProperties(profile);
        return profile;
    }

    private UUID parseUUID(String uuidStr) {
        String[] uuidComponents = new String[]{uuidStr.substring(0, 8),
                uuidStr.substring(8, 12), uuidStr.substring(12, 16),
                uuidStr.substring(16, 20),
                uuidStr.substring(20, uuidStr.length())
        };

        StringBuilder builder = new StringBuilder();
        for (String component : uuidComponents)
            builder.append(component).append('-');

        builder.setLength(builder.length() - 1);
        return UUID.fromString(builder.toString());
    }

    @SuppressWarnings("deprecation")
    private String getUUID(String name) {
        return Bukkit.getOfflinePlayer(name).getUniqueId().toString().replaceAll("-", "");
    }

    private void addProperties(GameProfile profile) {
        String uuid = getUUID(skinOwner);

        URLConnection connection = null;
        try {
            URL url = new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + uuid + "?unsigned=false");
            connection = url.openConnection();
            connection.setUseCaches(false);
            connection.setDefaultUseCaches(false);
            connection.addRequestProperty("User-Agent", "Mozilla/5.0");
            connection.addRequestProperty("Cache-Control", "no-cache, no-store, must-revalidate");
            connection.addRequestProperty("Pragma", "no-cache");

            String json = new Scanner(new BufferedInputStream(connection.getInputStream()), "UTF-8").useDelimiter("\\A").next();
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(json);
            JSONArray props = (JSONArray) ((JSONObject) obj).get("properties");
            for (int i = 0; i < props.size(); i++) {
                try {
                    JSONObject property = (JSONObject) props.get(i);
                    String name = (String) property.get("name");
                    String value = (String) property.get("value");
                    String signature = property.containsKey("signature") ? (String) property.get("signature") : null;
                    if (signature != null) {
                        profile.getProperties().put(name, new Property(name, value, signature));
                    } else {
                        profile.getProperties().put(name, new Property(value, name));
                    }
                } catch (Exception e) {
                    Bukkit.getLogger().log(Level.WARNING, "Failed to apply auth property", e);
                }
            }
        } catch (Exception e) {
            System.err.println("Couldn't load skin");
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.getInputStream().close();
                    connection.getOutputStream().close();
                }
            } catch(Exception e) {
                ;
            }
        }
    }

}
