package fr.cobnet.api.nms;

import fr.cobnet.api.i18n.I18n;
import fr.cobnet.core.players.CobPlayer;
import net.minecraft.server.v1_12_R1.IChatBaseComponent;
import net.minecraft.server.v1_12_R1.PacketPlayOutTitle;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.Collection;

/**
 * Created by remi on 24/01/2016.
 */
public class TitleBuilder {
    private String title;

    public String getTitle() {
        return title;
    }

    private String subtitle;

    public String getSubtitle() {
        return subtitle;
    }

    private String key;

    public String getKey() {
        return key;
    }

    private String[] args;

    public String[] getArgs() {
        return args;
    }

    private int fadeIn;

    public int getFadeIn() {
        return fadeIn;
    }

    private int stay;

    public int getStay() {
        return stay;
    }

    private int fadeOut;

    public int getFadeOut() {
        return fadeOut;
    }

    public TitleBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public TitleBuilder withSubtitle(String subtitle) {
        this.subtitle = subtitle;
        return this;
    }

    public TitleBuilder withKey(String key) {
        this.key = key;
        return this;
    }

    public TitleBuilder withArgs(String... args) {
        this.args = args;
        return this;
    }

    public TitleBuilder withFadeIn(int fadeIn) {
        this.fadeIn = fadeIn;
        return this;
    }

    public TitleBuilder withStay(int stay) {
        this.stay = stay;
        return this;
    }

    public TitleBuilder withFadeOut(int fadeOut) {
        this.fadeOut = fadeOut;
        return this;
    }

    private IChatBaseComponent buildChatComponent(String msg) {
        return IChatBaseComponent.ChatSerializer.a("{'text':'" + msg + "'}");
    }

    public void sendToCobPlayers(Collection<CobPlayer> cobPlayers) {
        cobPlayers.forEach(this::sendTo);
    }

    public void sendTo(Player player) {
        if(this.title != null) {
            PacketPlayOutTitle titlePacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, buildChatComponent(this.title), this.fadeIn, this.stay,
                    this.fadeOut);
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(titlePacket);
        }

        if(this.subtitle != null) {
            PacketPlayOutTitle subtitlePacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, buildChatComponent(this.subtitle), this.fadeIn, this.stay,
                    this.fadeOut);
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(subtitlePacket);
        }
    }

    public void sendTo(CobPlayer player) {
        player.getPlayer().sendMessage(I18n.tl(player.getLang(), key, args));
    }


    public void sendTo(Collection<? extends Player> players) {
        players.forEach(this::sendTo);
    }

}
