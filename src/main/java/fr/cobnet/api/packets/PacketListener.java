package fr.cobnet.api.packets;

/**
 * Créé par Creart le 25/01/2016.
 */
public interface PacketListener {

    /**
     * Event appelé quand un packet est reçu (quand le serveur reçoit un packet d'un joueur)
     *
     * @param packet > Packet reçu
     */
    default void onPacketReceive(ReceivedPacket packet) {
        System.out.println("Not implemented onPacketReceive method, in class: '" + this.getClass().getName() + "'");
    }

    /**
     * Event appelé quand un packet est envoyé (quand le serveur envoie un packet au joueur)
     *
     * @param packet > Packet envoyé
     */
    default void onPacketSend(SentPacket packet) {
        System.out.println("Not implemented onPacketSend method, in class: '" + this.getClass().getName() + "'");
    }

}
