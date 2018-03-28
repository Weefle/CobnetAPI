package fr.cobnet.api.nms;

import fr.cobnet.api.i18n.I18n;
import fr.cobnet.core.players.CobPlayer;
import net.minecraft.server.v1_12_R1.IChatBaseComponent;
import net.minecraft.server.v1_12_R1.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_12_R1.PlayerConnection;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.util.Collection;

/**
 * Created by remi on 24/01/2016.
 */
public class TablistBuilder {
    private String header;
    private String footer;
    private String headerKey;
    private String footerKey;

    public String getHeader() {
        return header;
    }

    public String getFooter() {
        return footer;
    }

    public String getHeaderKey() {
        return headerKey;
    }

    public String getFooterKey() {
        return footerKey;
    }

    public TablistBuilder withHeaderKey(String key) {
        this.headerKey = key;
        return this;
    }

    public TablistBuilder withFooterKey(String key) {
        this.footerKey = key;
        return this;
    }

    public TablistBuilder withHeader(String header) {
        this.header = header;
        return this;
    }

    public TablistBuilder withFooter(String footer) {
        this.footer = footer;
        return this;
    }

    private IChatBaseComponent buildChatComponent(String msg) {
        return IChatBaseComponent.ChatSerializer.a("{'text':'" + msg + "'}");
    }

    public void sendToPlayers(Collection<Player> players) {
        players.forEach(this::sendTo);
    }

    public void sendToCobPlayers(Collection<CobPlayer> players) {
        players.forEach(this::sendTo);
    }

    public void sendTo(Player player) {
        packet(player, header, footer);
    }

    public void sendTo(CobPlayer player) {
        packet(player.getPlayer(), I18n.tl(player.getLang(), footerKey), I18n.tl(player.getLang(), headerKey));
    }

    private void packet(Player player, String header, String footer) {
        PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
        PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();

        try {
            if (header != null) {
                Field hfield = packet.getClass().getDeclaredField("a");
                hfield.setAccessible(true);
                hfield.set(packet, buildChatComponent(header));
                hfield.setAccessible(false);
            }
            if (footer != null) {
                Field ffield = packet.getClass().getDeclaredField("b");
                ffield.setAccessible(true);
                ffield.set(packet, buildChatComponent(footer));
                ffield.setAccessible(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        connection.sendPacket(packet);
    }

}
