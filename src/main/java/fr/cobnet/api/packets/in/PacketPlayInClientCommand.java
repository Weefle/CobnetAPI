package fr.cobnet.api.packets.in;

import fr.cobnet.api.packets.InPacketType;
import fr.cobnet.api.packets.ReceivedPacket;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayInClientCommand.EnumClientCommand;
import org.bukkit.entity.Player;

/**
 * Créé par Creart le 26/01/2016.
 */
public class PacketPlayInClientCommand extends ReceivedPacket {

    public PacketPlayInClientCommand(Packet<?> packet, Player player) {
        super(packet, player);
    }

    public Command getCommand() {
        return Command.fromNMS(reflection().get("a"));
    }

    public PacketPlayInClientCommand setCommand(Command command) {
        reflection().set("a", command.toNms());
        return this;
    }

    public enum Command {
        RESPAWN(net.minecraft.server.v1_8_R3.PacketPlayInClientCommand.EnumClientCommand.PERFORM_RESPAWN),
        REQUEST_STATS(net.minecraft.server.v1_8_R3.PacketPlayInClientCommand.EnumClientCommand.REQUEST_STATS),
        OPEN_INVENTORY(net.minecraft.server.v1_8_R3.PacketPlayInClientCommand.EnumClientCommand.OPEN_INVENTORY_ACHIEVEMENT);

        private static Map<EnumClientCommand, Command> commands = new HashMap<>();

        private net.minecraft.server.v1_8_R3.PacketPlayInClientCommand.EnumClientCommand nms;

        Command(net.minecraft.server.v1_8_R3.PacketPlayInClientCommand.EnumClientCommand nms) {
            this.nms = nms;
        }

        static {
            for (Command command : values())
                commands.put(command.nms, command);
        }

        protected static Command fromNMS(net.minecraft.server.v1_8_R3.PacketPlayInClientCommand.EnumClientCommand nms) {
            return commands.get(nms);
        }

        protected net.minecraft.server.v1_8_R3.PacketPlayInClientCommand.EnumClientCommand toNms() {
            return nms;
        }
    }

    @Override
    public InPacketType getType() {
        return InPacketType.CLIENT_COMMAND;
    }
}
