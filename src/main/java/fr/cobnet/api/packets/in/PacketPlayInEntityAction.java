package fr.cobnet.api.packets.in;

import fr.cobnet.api.packets.InPacketType;
import fr.cobnet.api.packets.ReceivedPacket;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.server.v1_12_R1.Packet;
import net.minecraft.server.v1_12_R1.PacketPlayInEntityAction.EnumPlayerAction;
import org.bukkit.entity.Player;

/**
 * Créé par Creart le 26/01/2016.
 */
public class PacketPlayInEntityAction extends ReceivedPacket {

    public PacketPlayInEntityAction(Packet<?> packet, Player player) {
        super(packet, player);
    }

    public Action getAction() {
        return Action.fromNMS((net.minecraft.server.v1_12_R1.PacketPlayInEntityAction.EnumPlayerAction) reflection().get("animation"));
    }

    public int getRidingJumpBoost() {
        return reflection().get("c");
    }

    public int getEntityId() {
        return reflection().get("c");
    }

    public PacketPlayInEntityAction setAction(Action action) {
        reflection().set("animation", action.nms);
        return this;
    }

    public PacketPlayInEntityAction setRidingJumBoost(int jump) {
        reflection().set("c", jump);
        return this;
    }

    public PacketPlayInEntityAction setEntityId(int id) {
        reflection().set("a", id);
        return this;
    }

    @Override
    public InPacketType getType() {
        return InPacketType.ENTITY_ACTION;
    }

    public enum Action {

        START_SNEAKING(net.minecraft.server.v1_12_R1.PacketPlayInEntityAction.EnumPlayerAction.START_SNEAKING),
        STOP_SNEAKING(net.minecraft.server.v1_12_R1.PacketPlayInEntityAction.EnumPlayerAction.STOP_SNEAKING),
        STOP_SLEEPING(net.minecraft.server.v1_12_R1.PacketPlayInEntityAction.EnumPlayerAction.STOP_SLEEPING),
        START_SPRINTING(net.minecraft.server.v1_12_R1.PacketPlayInEntityAction.EnumPlayerAction.START_SPRINTING),
        STOP_SPRINTING(net.minecraft.server.v1_12_R1.PacketPlayInEntityAction.EnumPlayerAction.STOP_SPRINTING),
        //RIDING_JUMP(net.minecraft.server.v1_12_R1.PacketPlayInEntityAction.EnumPlayerAction.RIDING_JUMP),
        OPEN_INVENTORY(net.minecraft.server.v1_12_R1.PacketPlayInEntityAction.EnumPlayerAction.OPEN_INVENTORY);

        private static Map<EnumPlayerAction, Action> commands = new HashMap<>();

        private net.minecraft.server.v1_12_R1.PacketPlayInEntityAction.EnumPlayerAction nms;

        Action(net.minecraft.server.v1_12_R1.PacketPlayInEntityAction.EnumPlayerAction nms) {
            this.nms = nms;
        }

        static {
            for (Action command : values())
                commands.put(command.nms, command);
        }

        protected static Action fromNMS(net.minecraft.server.v1_12_R1.PacketPlayInEntityAction.EnumPlayerAction nms) {
            return commands.get(nms);
        }

        protected net.minecraft.server.v1_12_R1.PacketPlayInEntityAction.EnumPlayerAction toNms() {
            return nms;
        }

    }

}
