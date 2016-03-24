package fr.cobnet.api.games;

/**
 * Created by remi on 24/01/2016.
 */
public enum GameStatus {
    LOBBY("En attente"),
    PLAYING("En jeu"),
    FINISHED("Fin de partie");

    private String name;

    GameStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
