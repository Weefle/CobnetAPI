package fr.cobnet.core.commands;

import fr.cobnet.api.commands.BaseCommand;
import fr.cobnet.api.players.Permission;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class SilenceCommand extends BaseCommand {

    private static boolean silence;

    public static boolean hasSilence() {
        return silence;
    }

    public SilenceCommand() {
        super(Permission.getSilencePermission(), "silence", true, 0);
    }

    @Override
    protected void displayHelp(CommandSender sender) {
        sender.sendMessage("§c/silence (toggle)");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!super.check(sender, cmd, label, args)) return false;
        silence = !silence;

        if (silence)
            Bukkit.broadcastMessage("§cLe chat vient d'être \"fermé\", seules certaines personnes peuvent encore écrire dans le chat.");
        else
            Bukkit.broadcastMessage("§aLe chat est de nouveau \"ouvert\".");

        return false;
    }

}
