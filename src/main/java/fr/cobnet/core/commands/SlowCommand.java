package fr.cobnet.core.commands;

import fr.cobnet.api.commands.BaseCommand;
import fr.cobnet.api.lang.StringUtils;
import fr.cobnet.api.players.Permission;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class SlowCommand extends BaseCommand {

    private static boolean hasSlow;
    private static long slow;

    public static boolean hasSlow() {
        return hasSlow;
    }

    public static long getSlow() {
        return slow;
    }

    public SlowCommand() {
        super(Permission.getSlowPermission(), "slow", true, 2);
    }

    @Override
    protected void displayHelp(CommandSender sender) {
        sender.sendMessage("§c/slow <set|reset> <secondes> ");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!super.check(sender, cmd, label, args)) return false;

        if (args.length < getMinArgs()) return true;

        String mode = args[0].toLowerCase();

        switch (mode) {
            case "set":
                // si c'est un nombre
                if (StringUtils.isNumber(args[1])) {
                    int slowRaw = Integer.parseInt(args[1]);

                    // trop peu ou trop de temps
                    if (slowRaw < 0 || slowRaw > 180) {
                        sender.sendMessage("§cLe temps de slow doit être compris entre 0 et 180.");
                        return true;
                    }

                    slow = slowRaw * 1000;
                    hasSlow = true;
                    Bukkit.broadcastMessage("§6Un modérateur vient de fixer le slow de chat à " + slow + " secondes (il vous faudra donc attendre" + slow + " secondes entre chaque message pour écrire)");
                } else
                    displayHelp(sender);
                return true;
            case "reset":
                hasSlow = false;
                Bukkit.broadcastMessage("§6Un modérateur vient de retirer le slow de chat.");
                return true;
            default:
                displayHelp(sender);
                return true;
        }
    }

}
