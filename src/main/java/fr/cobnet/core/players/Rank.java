package fr.cobnet.core.players;

public enum Rank {

    ADMINISTRATEUR(100, "§4[Admin] "),
    COMMUNITY_MANAGER(95, "§2[CM] "),
    RESP_DEV(90, "§c[Resp. dev'] ", "§c[Chef dev'] "),
    RESP_BUILDER(85, "§2[Resp. build] ", "§2[Build Chef] "),
    RESP_MODO(80, "§9[Resp. Mod'] ", "§9[Mod' Chef] "),
    FAMOUSSITE(75, "§6[Famoussité] ", "§6[SoFamous] "),
    POTO(70, "§6[Poto] ", "§6[Buddy] "),
    DEV(65, "§c[Développeur] ", "§c[Developer] "),
    BUILDER(60, "§2[Builder] "),
    MODERATEUR(55, "§9[Modérateur] ", "§9[Moderator] "),
    HELPER(50, "§3[Helper] "),
    VIP(10, "§a[VIP] "),
    SMALL_VIP(5, "§e[SmallVIP] "),
    JOUEUR(1, "§7");

    private int id;
    private String prefix;
    private String eng;

    Rank(int id, String prefix, String... eng) {
        this.id = id;
        this.prefix = prefix;
        if (eng.length == 0 || eng[0] == null) {
            this.eng = this.prefix;
        } else {
            this.eng = eng[0];
        }
    }

    public int getId() {
        return this.id;
    }

    /**
     *
     * @return Le préfixe français du grade
     */
    public String getFrenchPrefix() {
        return this.prefix;
    }

    /**
     *
     * @return Le préfixe anglais du grade
     */
    public String getEnglishPrefix() {
        return this.eng;
    }

    public static Rank getById(int id) {
        for (Rank rank : values()) {
            if (rank.getId() == id) return rank;
        }
        return null;
    }

}
