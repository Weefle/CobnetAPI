package fr.cobnet.api.packets.test;

import fr.cobnet.api.packets.InPacketType;
import fr.cobnet.api.packets.PacketHandler;
import fr.cobnet.api.packets.PacketListener;
import fr.cobnet.api.packets.ReceivedPacket;

/**
 * Créé par Creart le 27/01/2016.
 */
public class PacketTestListener implements PacketListener {

    public PacketTestListener() {
        PacketHandler.getInstance().registerIncoming(this, InPacketType.BLOCK_DIG, InPacketType.BLOCK_PLACE);
    }

    @Override
    public void onPacketReceive(ReceivedPacket packet) {
        System.out.println("PACKET");
        if (packet.getType() == InPacketType.BLOCK_DIG) {
            packet.getPlayer().sendMessage("§cVous êtes en train ");
        } else if (packet.getType() == InPacketType.BLOCK_PLACE) {
            packet.getPlayer().sendMessage("§cVous placez un block");
        }
    }

}
