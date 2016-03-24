package fr.cobnet.core.commands;

import fr.cobnet.api.commands.BaseCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Créé par Creart le 05/02/2016.
 */
public class LangCommand extends BaseCommand {

    public LangCommand(String permission, String command, boolean hasToBePlayer, int minArgs) {
        super(permission, command, hasToBePlayer, minArgs);
    }

    @Override
    protected void displayHelp(CommandSender sender) {

    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (!(commandSender instanceof Player)) return false;



        return false;
    }

}
