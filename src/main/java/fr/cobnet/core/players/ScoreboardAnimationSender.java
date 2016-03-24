package fr.cobnet.core.players;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang.Validate;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Créé par Creart le 03/01/2016.
 */
public class ScoreboardAnimationSender extends BukkitRunnable {

    private static List<Character> characterList = Collections.unmodifiableList(Arrays.asList('w', 'w', 'w', '.', 'c', 'o', 'b', 'n', 'e', 't', '.', 'f', 'r'));

    private static String full = "§ewww.cobnet.fr", almostFull = "§ewww.cobnet.f§6r";

    private Player player;
    private CobPlayer cob;
    private int index;
    boolean yellow = false;

    public ScoreboardAnimationSender(CobPlayer cob, Player player) {
        Validate.notNull(cob);
        Validate.notNull(player);

        this.player = player;
        this.cob = cob;
    }

    @Override
    public void run() {
        if (!player.isOnline()) {
            cancel();
            return;
        }

        if (characterList.size()+1 == index) {
            cob.getScoreboard().setLine(15, full);
            cancel();
            return;
        }

        /*if (characterList.size()+2 == index) {
            cob.getScoreboard().setLine(15, almostFull);
            cancel();
            return;
        }*/

        StringBuilder builder = new StringBuilder("§e");

        /*for (char c : list) {
            if (i-1 == index && index != characterList.size())
                builder.append("§c");
            else if (i+1 == index)
                builder.append("§e");

            builder.append(c);

            if (i == index)
                builder.append("§e");
            else if (i+1 == index && index != characterList.size())
                builder.append("§6");



            i++;
        }*/

        int i = 0;

        for (char c : characterList) {

            i++;

            if (yellow) {
                builder.append(c).append("§e");
                yellow = false;
            } else if (index == i) {
                builder.append("§c").append(c).append("§6");
                yellow = true;
            } else if (index-1 == i) {
                builder.append("§6").append(c);
            } else {
                builder.append(c);
            }

            /*i++;
            if (index == i) {
                builder.append("§c").append(c).append("§6");
            }

            else if (characterList.size() > i+1) {
                if (index+1 == i) {
                    builder.append(c).append("§e");
                } else if (index-1 == i) {
                    builder.append("§6").append(c).append("§e");
                } else {
                    builder.append(c);
                }
            } else {
                builder.append(c);
            }*/
        }

        index++;

        cob.getScoreboard().setLine(15, builder.toString());
    }

}
