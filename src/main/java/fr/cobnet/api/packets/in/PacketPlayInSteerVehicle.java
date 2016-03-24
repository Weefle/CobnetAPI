package fr.cobnet.api.packets.in;

import fr.cobnet.api.packets.InPacketType;
import fr.cobnet.api.packets.ReceivedPacket;
import net.minecraft.server.v1_8_R3.Packet;
import org.bukkit.entity.Player;

/**
 * Créé par Creart le 26/01/2016.
 */
public class PacketPlayInSteerVehicle extends ReceivedPacket {

    public PacketPlayInSteerVehicle(Packet<?> packet, Player player) {
        super(packet, player);
    }

    /**
     * If positive = moving to the left
     * @return
     */
    public float getSideways() {
        return (float) reflection().get("a");
    }

    /**
     * If positive = moving forward
     * @return
     */
    public float getForward() {
        return (float) reflection().get("b");
    }

    public boolean isJumping() {
        return (boolean) reflection().get("c");
    }

    public boolean isUnmounting() {
        return (boolean) reflection().get("d");
    }

    public PacketPlayInSteerVehicle setSideways(float value) {
        reflection().set("a", value);
        return this;
    }

    public PacketPlayInSteerVehicle setForward(float value) {
        reflection().set("b", value);
        return this;
    }

    public PacketPlayInSteerVehicle setJumping(boolean value) {
        reflection().set("c", value);
        return this;
    }

    public PacketPlayInSteerVehicle setUnmounting(boolean value) {
        reflection().set("d", value);
        return this;
    }

    @Override
    public InPacketType getType() {
        return InPacketType.STEER_VEHICLE;
    }

}
