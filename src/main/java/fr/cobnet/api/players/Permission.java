package fr.cobnet.api.players;

public final class Permission {
	
	private Permission() {}
	
	/*
	 * COMMANDES
	 */
	
	/**
	 * Permission qui donne le droit d'utiliser le /nick (donc de se renommer)
	 * @return
	 */
	public static String getNickPermission() {
		return "nick";
	}
	
	/**
	 * Permission qui donne le droit de set un slow et de parler normalement pdnt le slow
	 * @return
	 */
	public static String getSlowPermission() {
		return "set.slow";
	}
	
	/**
	 * Permission qui donne le droit de set un silence et de parler pendant un silence
	 * @return
	 */
	public static String getSilencePermission() {
		return "set.silence";
	}

    /**
     * Permission qui donne le droit d'utiliser la commande /game, et donc de démarrer une partie
     * @return
     */
    public static String getGameCommandPermission() {
        return "game";
    }
	
}
