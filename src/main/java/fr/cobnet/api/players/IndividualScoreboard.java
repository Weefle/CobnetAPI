package fr.cobnet.api.players;

import fr.cobnet.api.lang.ReflectionUtils;
import net.minecraft.server.v1_12_R1.IScoreboardCriteria;
import net.minecraft.server.v1_12_R1.Packet;
import net.minecraft.server.v1_12_R1.PacketPlayOutScoreboardDisplayObjective;
import net.minecraft.server.v1_12_R1.PacketPlayOutScoreboardObjective;
import net.minecraft.server.v1_12_R1.PacketPlayOutScoreboardScore;
import org.apache.commons.lang3.Validate;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class IndividualScoreboard {

    Player player;
    boolean created;
    String lines[];
    String objectiveName;

    public IndividualScoreboard(Player player, String objectiveName) {
        this.player = player;
        this.lines = new String[16];
        this.objectiveName = objectiveName;
    }

    private void sendLine(int line) {
        Validate.isTrue(line >= 0 && line < 16);
        if (!created) return;

        int score = (line * -1) - 1;
        String val = lines[line];
        sendPacket(sendScore(val, score));
    }

    public void setLine(int line, String value) {
        String oldLine = getLine(line);
        if (oldLine != null && created)
            sendPacket(removeLine(oldLine));

        lines[line] = value;
        sendLine(line);
    }

    public void removeLine(int line) {
        String oldLine = getLine(line);
        if (oldLine != null && created)
            sendPacket(removeLine(oldLine));

        lines[line] = null;
    }

    public Player getPlayer() {
        return player;
    }

    public void setObjectiveName(String name) {
        objectiveName = name;
        if (created) sendPacket(getObjectivePacket(2, name));
    }

    public void make() {
        if (created) return;

        sendPacket(getObjectivePacket(0, objectiveName));
        sendPacket(setObjectiveSlot());
        int i = 0;
        while (i < lines.length)
            sendLine(i++);
        created = true;
    }

    public void end() {
        if (!created) return;

        sendPacket(getObjectivePacket(1, null));
        created = false;
    }

    private String getLine(int line) {
        if (line > 15 || line < 0) return null;
        return lines[line];
    }

    private PacketPlayOutScoreboardScore sendScore(String line, int score) {
        PacketPlayOutScoreboardScore packet = new PacketPlayOutScoreboardScore(line);
        try {
            ReflectionUtils.setValue(packet, true, "b", player.getName());
            ReflectionUtils.setValue(packet, true, "c", score);
            ReflectionUtils.setValue(packet, true, "d", PacketPlayOutScoreboardScore.EnumScoreboardAction.CHANGE);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return packet;
    }

    private void sendPacket(Packet<?> packet) {
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }

    private PacketPlayOutScoreboardObjective getObjectivePacket(int mode, String name) {
        PacketPlayOutScoreboardObjective packet = new PacketPlayOutScoreboardObjective();
        try {
            ReflectionUtils.setValue(packet, true, "a", player.getName());
            ReflectionUtils.setValue(packet, true, "d", mode);
            if (mode == 0 || mode == 2) {
                ReflectionUtils.setValue(packet, true, "b", name);
                ReflectionUtils.setValue(packet, true, "c", IScoreboardCriteria.EnumScoreboardHealthDisplay.INTEGER);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return packet;
    }

    private PacketPlayOutScoreboardScore removeLine(String line) {
        return new PacketPlayOutScoreboardScore(line);
    }

    private PacketPlayOutScoreboardDisplayObjective setObjectiveSlot() {
        PacketPlayOutScoreboardDisplayObjective packet = new PacketPlayOutScoreboardDisplayObjective();
        try {
            ReflectionUtils.setValue(packet, true, "a", 1);
            ReflectionUtils.setValue(packet, true, "b", player.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return packet;
    }

}
