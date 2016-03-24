package fr.cobnet.core.listeners;

import fr.cobnet.api.events.Listener;
import fr.cobnet.api.i18n.I18n;
import fr.cobnet.api.i18n.Lang;
import fr.cobnet.api.nms.ActionBarBuilder;
import fr.cobnet.api.players.Permission;
import fr.cobnet.api.players.TeamUtils;
import fr.cobnet.core.commands.SilenceCommand;
import fr.cobnet.core.commands.SlowCommand;
import fr.cobnet.core.players.CobPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scoreboard.Team;

public class ChatSendListener extends Listener {

    public static final long ADDER = 2000;
    public static boolean doFormat = true;

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onChat(AsyncPlayerChatEvent e) {

        e.setCancelled(true);
        Player player = e.getPlayer();
        String message = e.getMessage();

        if (message.length() < 2) {
            return;
        }

        CobPlayer cob = getCobPlayer(player);

        if (SilenceCommand.hasSilence()) {
            if (!player.hasPermission(Permission.getSilencePermission())) {
                player.sendMessage(I18n.tl(cob.getLang(), "chat_closed"));
                return;
            }
        }

        CobPlayer cp = getCobPlayer(player);
        if (cp == null) return;

        // spam
        if (cp.getNextChat() > now()) {
            // si c'est un slow
            if (SlowCommand.hasSlow() && !player.hasPermission(Permission.getSilencePermission()))
                player.sendMessage(I18n.tl(cob.getLang(), "chat_slow", (SlowCommand.getSlow()/1000) + ""));
            else
                player.sendMessage(I18n.tl(cob.getLang(), "chat_spam"));

            return;
        }

		/* message accepté */

        String msg = message;

        boolean defaultGrade = true;
        // format
        if (doFormat) {
            Team team = TeamUtils.getPlayerTeam(player);
            StringBuilder builder = new StringBuilder();

            if (team != null)
                builder.append(team.getPrefix());
            else
                builder.append("§7");

            defaultGrade = team != null && team.getName().equals("joueur");
            builder.append(player.getName());
            builder.append(" _§ ");
            if (defaultGrade) builder.append(team.getName().equals("joueur") ? "§7" : "§f");
            else builder.append("§7");
            builder.append(message);
            msg = builder.toString();
        }

        String bits[] = e.getMessage().split(" ");

        // send & mention
        for (Player online : Bukkit.getOnlinePlayers()) {
            if (cob.listensChat() && !online.equals(player)) continue;
            String messg = msg;
            for (String bit : bits) {
                if (bit.equalsIgnoreCase(online.getName())) {
                    new ActionBarBuilder(I18n.tl(cob.getLang(), "chat.mention", player.getName())).sendTo(player);
                    messg = messg.replaceAll("(?i)".concat(bit), "§d"+player.getName()+ (defaultGrade ? "§7" : "§r"));
                    messg = messg.replaceAll(" _§", cob.getLang() == Lang.ENGLISH ? ":" : " :");
                }
            }
            online.sendMessage(messg);
        }

        cp.setLastChatMessage(message);
        if (SlowCommand.hasSlow() && !player.hasPermission(Permission.getSilencePermission()))
            cp.setNextChat(now() + SlowCommand.getSlow());
        else
            cp.setNextChat(now() + ADDER);
    }

}