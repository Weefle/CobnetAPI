package fr.cobnet.api.misc;

import fr.cobnet.api.i18n.I18n;
import fr.cobnet.api.i18n.Lang;
import fr.cobnet.core.CobnetCore;
import fr.cobnet.core.players.CobPlayer;
import net.minecraft.server.v1_8_R3.Packet;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class BasicFunctions {
    private Chrono chrono = null;
    private CobnetCore plugin;

    public Chrono getChrono() {
        if (chrono == null) {
            chrono = new Chrono();
        }
        return chrono;
    }

    public CobPlayer getCobPlayer(Player player) {
        return CobPlayer.findByPlayer(player);
    }

    public CobnetCore getAPI() {
        return plugin == null ? plugin = CobnetCore.getInstance() : plugin;
    }

    public String tl(Lang lang, String key, String... values) {
        return I18n.tl(lang, key, values);
    }

    protected long now() {
        return System.currentTimeMillis();
    }

    protected double pi() {
        return Math.PI;
    }

    protected double sin(double d) {
        return Math.sin(d);
    }

    protected double asin(double d) {
        return Math.asin(d);
    }

    protected double cos(double d) {
        return Math.cos(d);
    }

    protected double acos(double d) {
        return Math.acos(d);
    }

    protected double tan(double d) {
        return Math.tan(d);
    }

    protected double atan(double d) {
        return Math.atan(d);
    }

    protected void sendPacket(Player player, Packet<?> packet) {
        if (packet == null || player == null || !player.isOnline()) return;

        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }

}
