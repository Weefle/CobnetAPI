package fr.cobnet.api.commands;

import fr.cobnet.api.i18n.I18n;
import fr.cobnet.api.misc.BasicFunctions;
import fr.cobnet.core.players.CobPlayer;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Oscar on 29/12/2015.
 */
public abstract class BaseCommand extends BasicFunctions implements CommandExecutor {

    private final String permission;

    public String getPermission() {
        return permission;
    }

    private final String command;

    public String getCommand() {
        return command;
    }

    private boolean hasToBePlayer;

    public boolean isHasToBePlayer() {
        return hasToBePlayer;
    }

    private int minArgs;

    public int getMinArgs() {
        return minArgs;
    }

    /**
     * Permet d'éviter des tests, la possession d'une permission, l'enregistrement de la commande, etc.
     * @param permission > La permission requise pour exécuter cette commande
     * @param command > Le nom de la commande (afin de l'enregistrer)
     * @param hasToBePlayer > <code>true</code> si la commande peut être executée seulement par un joueur
     * @param minArgs > Le nombre d'arguments minimal
     */
    public BaseCommand(String permission, String command, boolean hasToBePlayer, int minArgs) {
        Validate.notNull(command);
        Validate.notEmpty(command);
        Validate.isTrue(minArgs > -1);

        this.permission = permission;
        this.command = command;
        this.hasToBePlayer = hasToBePlayer;
        this.minArgs = minArgs;

        Bukkit.getPluginCommand(command).setExecutor(this);
    }

    protected abstract void displayHelp(CommandSender sender);

    /**
     * Permet de check si la commande doit être exécutée.
     * Renseignez tous les paramètres de la fonction {@link CommandExecutor#onCommand(CommandSender, Command, String, String[])}
     * @param sender CommandSender
     * @param command Command
     * @param label String
     * @param args String[]
     * @return <code>true</code> si les conditions de base de la commande son remplies, <code>false</code> sinon #CaptainObvious
     */
    protected boolean check(CommandSender sender, Command command, String label, String[] args) {

        if (hasToBePlayer && !(sender instanceof Player)) return true;

        Player player = (Player) sender;

        if (!player.hasPermission(permission)) {
            player.sendMessage(I18n.tl(CobPlayer.findByPlayer(player).getLang(), "perm_lack"));
            return true;
        }

        if(args.length < minArgs) {
            displayHelp(sender);
            return true;
        }

        return false;
    }
}
