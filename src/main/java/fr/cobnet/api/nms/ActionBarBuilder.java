package fr.cobnet.api.nms;

import fr.cobnet.core.CobnetCore;
import fr.cobnet.core.players.CobPlayer;
import net.minecraft.server.v1_12_R1.IChatBaseComponent;
import net.minecraft.server.v1_12_R1.PacketPlayOutChat;
import net.minecraft.server.v1_12_R1.PlayerConnection;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.Collection;

/**
 * Created by remi on 24/01/2016.
 */
public class ActionBarBuilder {
    private String message;

    public String getMessage() {
        return message;
    }

    private int stay;

    public int getStay() {
        return stay;
    }

    public ActionBarBuilder(String message) {
        this.message = message;
    }

    public ActionBarBuilder withStay(int stay) {
        this.stay = stay;
        return this;
    }

    private IChatBaseComponent buildChatComponent(String msg) {
        return IChatBaseComponent.ChatSerializer.a("{'text':'" + msg + "'}");
    }

    public void sendTo(Collection<Player> players) {
        players.forEach(this::sendTo);
    }

    public void sendTo(Player player) {
        PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
        PacketPlayOutChat packet = new PacketPlayOutChat(buildChatComponent(this.message));
        connection.sendPacket(packet);
        if(this.stay != 0) {
            Bukkit.getScheduler().runTaskLater(CobnetCore.getInstance(), () -> {
                PacketPlayOutChat clear = new PacketPlayOutChat(buildChatComponent(""));
                connection.sendPacket(clear);
            }, this.stay * 20L);
        }
    }

    public void sendTo(CobPlayer player) {
        /*@TODO: i18n support!*/
    }

}
