package fr.cobnet.api.packets.in;

import fr.cobnet.api.packets.InPacketType;
import fr.cobnet.api.packets.ReceivedPacket;
import java.util.UUID;
import net.minecraft.server.v1_8_R3.Packet;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Créé par Creart le 26/01/2016.
 */
public class PacketPlayInSpectate extends ReceivedPacket {

    public PacketPlayInSpectate(Packet<?> packet, Player player) {
        super(packet, player);
    }

    public Player getSpectated() {
        return Bukkit.getPlayer((UUID) reflection().get("a"));
    }

    @Override
    public InPacketType getType() {
        return InPacketType.SPECTATE;
    }

}
