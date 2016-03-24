package fr.cobnet.api.players;

public enum TeamColour {
	
	RED_CROSS("red_cross", "§c§l✠ §r§c "),
	RED_DIAMOND("red_diamond", "§c§l✦§r§c"),
	RED_AIRCRAFT("red_airc", "§c§l✈ §r§c"),
	LIME_FLOWER1("lime_fl1", "§a§l✿§r§a "),
	LIME_OMEGA("lime_omega", "§a§lΩ§r§a "),
	LIME_ANCHOR("lime_anc", "§a§l⚓§r§a "),
	AQUA_FLOWER2("aqua_fl2", "§b§l❉§r§b "),
	AQUA_INFO("aqua_info", "§b§lℹ§r§b "),
	AQUA_STAR("aqua_star", "§b§l✪§r§b "),
	YELLOW_DIAMOND("yel_diams", "§e§l✦§r§e "),
	YELLOW_OMEGA("yel_om", "§e§lΩ§r§e "),
	YELLOW_ANCHOR("yel_anch", "§e§l⚓§r§e "),
	GREEN_AIRCRAFT("green_airc", "§2§l✈§r§2 "),
	GREEN_COFFEE("green_cof", "§2§l☕§r§2 "),
	GREEN_OMEGA("green_om", "§2§lΩ§r§2 "),
	PINK_COFFEE("pink_cof", "§d§l☕§r§d "),
	PINK_FLOWER1("pink_fl1", "§d§l✿§r§d"),
	PINK_FLOWER2("pink_fl2", "§d§l❉§r§d");

	private String teamName;
	private String prefix;
	
	TeamColour(String teamName, String prefix) {
		this.teamName = teamName;
		this.prefix = prefix;
	}
	
	public String getTeamName() {
		return this.teamName;
	}
	
	public String getPrefix() {
		return this.prefix;
	}

}
