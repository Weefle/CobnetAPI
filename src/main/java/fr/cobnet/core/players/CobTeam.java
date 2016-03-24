package fr.cobnet.core.players;

import fr.cobnet.api.i18n.Lang;
import fr.cobnet.api.lang.ReflectionUtils;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.server.v1_8_R3.PacketPlayOutScoreboardTeam;
import net.minecraft.server.v1_8_R3.ScoreboardTeam;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

/**
 * Créé par Creart le 05/02/2016.
 */
public class CobTeam {

    public static void refreshAll() {
        teams.values().stream().forEach(CobTeam::refresh);
    }

    private static Map<String, CobTeam> teams = new HashMap<>();

    private ScoreboardTeam team;
    private Rank rank;

    public CobTeam(ScoreboardTeam team, Rank rank) {
        this.team = team;
        this.rank = rank;

        teams.put(team.getName(), this);
    }

    public void sendToPlayer(Player player, CobPlayer cobPlayer) {
        //
        try {
            PacketPlayOutScoreboardTeam packet = new PacketPlayOutScoreboardTeam(this.team, rank.getId());
            ReflectionUtils.setValue(packet, packet.getClass(), true, "c", (cobPlayer.getLang() == Lang.FRENCH ? rank.getFrenchPrefix() : rank.getEnglishPrefix()));
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
            cobPlayer.setDisplayRankLoaded(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void refresh() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player != null && player.isOnline()) {
                sendToPlayer(player, CobPlayer.findByUuid(player.getUniqueId()));
            }
        }
    }

}
