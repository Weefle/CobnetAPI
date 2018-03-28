package fr.cobnet.api.packets.in;

import fr.cobnet.api.packets.InPacketType;
import fr.cobnet.api.packets.ReceivedPacket;
import net.minecraft.server.v1_12_R1.Packet;
import net.minecraft.server.v1_12_R1.PacketDataSerializer;
import org.bukkit.entity.Player;

/**
 * Créé par Creart le 26/01/2016.
 */
public class PacketPlayInCustomPayload extends ReceivedPacket {

    public PacketPlayInCustomPayload(Packet<?> packet, Player player) {
        super(packet, player);
    }

    public String getChannel() {
        return reflection().get("a");
    }

    public PacketDataSerializer getSeralizer() {
        return reflection().get("b");
    }

    @Override
    public InPacketType getType() {
        return InPacketType.CUSTOM_PAYLOAD;
    }

}
