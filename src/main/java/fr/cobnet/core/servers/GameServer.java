package fr.cobnet.core.servers;

import fr.cobnet.api.events.Listener;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Créé par Creart le 07/02/2016.
 */
public abstract class GameServer extends Listener {

    private static GameServer instance;

    public static GameServer getInstance() {
        return instance;
    }

    public static void setInstance(GameServer i) {
        instance = i;
    }

    /*
    PARTY
     */

    /**
     * Permet d'obtenir l'UUID de party du joueur
     * @param player > UUID du joueur
     * @return UUID
     */
    public abstract UUID getPlayersParty(UUID player);

    /**
     * Permet de savoir si le joueur est en party
     * @param player > UUID du joueur
     * @return Boolean
     */
    public abstract boolean hasParty(UUID player);

    /**
     * Permet d'obtenir les joueurs d'une party
     * @param party > UUID de la party
     * @return Set
     */
    public abstract Set<UUID> getPlayersInParty(UUID party);

    /**
     * Permet d'obtenir les joueurs de la party du joueur demandé (brainfuck)
     * UUID du joueur => On récupère l'uuid de sa party => puis on récupère les joueurs de sa party
     * @param player > UUID du joueur
     * @return Set
     */
    public abstract Set<UUID> getPlayersInPartyByPlayer(UUID player);

    /**
     * Permet de savoir si le joueur en question est le leader de sa party
     * @param player > UUID du joueur
     * @return Boolean
     */
    public abstract boolean isLeaderOfParty(UUID player);

    /**
     * Permet d'obtenir le leader de la party
     * @param party > UUID de la party
     * @return UUID du leader
     */
    public abstract UUID getPartyLeader(UUID party);

    /**
     * Permet d'obtenir l'UUID du leader de la party du joueur
     * @param player > UUID du joueur
     * @return UUID
     */
    public abstract UUID getPlayersPartyLeader(UUID player);

    /**
     * Permet d'obtenir toutes les parties en ligne sur le serveur
     *
     * @return Set des uuids
     */
    public abstract Set<Set<UUID>> getOnlineParties();

    /*
    FRIENDS
     */

    /**
     * Permet d'obtenir la liste des amis du joueur
     * @param player > UUID du joueur
     * @return List
     */
    public abstract List<UUID> getFriends(UUID player);

    /**
     * Permet de savoir si les deux UUID
     * @param first > UUID du premier joueur
     * @param other > UUID du second joueur
     * @return Boolean
     */
    public abstract boolean areFriends(UUID first, UUID other);

    /**
     * Permet d'obtenir la liste des amis en ligne du joueur
     * @param player > UUID du joueur
     * @return List
     */
    public abstract List<UUID> getOnlineFriends(UUID player);

}
