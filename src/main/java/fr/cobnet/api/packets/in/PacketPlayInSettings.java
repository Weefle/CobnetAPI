package fr.cobnet.api.packets.in;

import fr.cobnet.api.packets.InPacketType;
import fr.cobnet.api.packets.ReceivedPacket;
import net.minecraft.server.v1_12_R1.EntityHuman.EnumChatVisibility;
import net.minecraft.server.v1_12_R1.Packet;
import org.bukkit.entity.Player;

/**
 * Créé par Creart le 26/01/2016.
 */
public class PacketPlayInSettings extends ReceivedPacket {

    public PacketPlayInSettings(Packet<?> packet, Player player) {
        super(packet, player);
    }

    public String getLocale() {
        return reflection().get("a");
    }

    public int getViewDistance() {
        return reflection().get("b");
    }

    public EnumChatVisibility getChatVisibility() {
        return reflection().get("c");
    }

    public boolean areColoursEnabled() {
        return reflection().get("d");
    }

    @Override
    public InPacketType getType() {
        return InPacketType.SETTINGS;
    }

}
