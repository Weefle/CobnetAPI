package fr.cobnet.core.commands;

import fr.cobnet.api.commands.BaseCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

/**
 * Créé par Creart le 21/02/2016.
 */
public class PluginLoadCommand extends BaseCommand {

    private PluginManager pluginManager;

    public PluginLoadCommand() {
        super("", "plugin", false, 1);

        pluginManager = Bukkit.getPluginManager();
    }

    @Override
    protected void displayHelp(CommandSender sender) {

    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if (super.check(commandSender, command, s, args)) return false;
        Plugin plugin = pluginManager.getPlugin(args[0]);
        if (args.length == 1) {
            if (plugin == null) {
                commandSender.sendMessage("§cCe plugin n'existe pas.");
                return false;
            }

            if (plugin.isEnabled()) {
                pluginManager.disablePlugin(plugin);
                commandSender.sendMessage("Plugin " + args[0] + " désactivé");
            } else {
                pluginManager.enablePlugin(plugin);
                commandSender.sendMessage("§aPlugin" + args[0] + " réactivé");
            }
        }

        else {
            switch (args[1].toLowerCase()) {
                case "reload":
                case "rl":
                    Bukkit.getPluginManager().disablePlugin(plugin);
                    commandSender.sendMessage("§aPlugin " + args[0] + " reload");
                    break;

                case "disable":
                case "d":
                    if (plugin.isEnabled()) {
                        pluginManager.enablePlugin(plugin);
                        commandSender.sendMessage("§aPlugin " + args[0] + " désactivé");
                        return false;
                    }

                    commandSender.sendMessage("§cCe plugin est déjà désactivé !");

                    break;

                case "enable":
                case "e":
                    if (plugin.isEnabled()) {
                        commandSender.sendMessage("§cCe plugin est déjà activé !");
                        return false;
                    }

                    pluginManager.enablePlugin(plugin);
                    commandSender.sendMessage("§aPlugin " + args[0] + " activé");
                    break;

                default:
                    displayHelp(commandSender);
                    break;
            }
        }

        return false;
    }
}
