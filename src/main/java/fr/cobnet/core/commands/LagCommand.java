package fr.cobnet.core.commands;

import fr.cobnet.api.commands.BaseCommand;
import fr.cobnet.api.i18n.I18n;
import fr.cobnet.api.i18n.Lang;
import fr.cobnet.api.server.ServerUtils;
import fr.cobnet.core.players.CobPlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class LagCommand extends BaseCommand {

    public LagCommand() {
        super(null, "lag", true, 0);
    }

    @Override
    protected void displayHelp(CommandSender sender) {

    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) return true;

        Player player = (Player) sender;
        Lang lang = CobPlayer.findByPlayer(player).getLang();

        player.sendMessage("§c▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀");
        player.sendMessage("§6§l         LAG         ");
        player.sendMessage(I18n.tl(lang, "lag_ping", ((CraftPlayer) player).getHandle().ping + ""));
        player.sendMessage(I18n.tl(lang, "server_lag", ServerUtils.getServerLagAverage() + ""));

        player.sendMessage("§c▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄");

        return false;

    }

}
