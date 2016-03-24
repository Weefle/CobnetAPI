package fr.cobnet.api.items;

/**
 * Created by remi on 25/01/2016.
 */
public enum Head {
    APPLE("MHF_Apple"),
    CAKE("MHF_Cake"),
    MELON("MHF_Melon"),
    PUMPKIN("MHF_Pumpkin"),
    OAK_LOG("MHF_OakLog"),
    CACTUS("MHF_Cactus"),
    TNT("MHF_TNT"),
    TNT2("MHF_TNT2"),
    CHEST("MHF_Chest"),
    PIGMAN("MHF_PigZombie"),
    SPIDER("MHF_Spider"),
    MAGMA("MHF_LavaSlime"),
    SQUID("MHF_Squid"),
    GHAST("MHF_Ghast"),
    MUSHROOM("MHF_MushroomCow"),
    COW("MHF_Cow"),
    CHICKEN("MHF_Chicken"),
    PIG("MHF_Pig"),
    WITHER("MHF_Wither"),
    BLAZE("MHF_Blaze"),
    GOLEM("MHF_Golem"),
    ENDERMAN("MHF_Enderman"),
    KINGENDERMAN("KingEndermen"),
    VILLAGER("MHF_Villager"),
    SHEEP("MHF_Sheep"),
    CAVESPIDER("MHF_CaveSpider"),
    QUESTION("MHF_Question"),
    EXCLAMATION("MHF_Exclamation"),
    ARROW_DOWN("MHF_ArrowDown"),
    ARROW_UP("MHF_ArrowUP"),
    ARROW_LEFT("MHF_ArrowLeft"),
    ARROW_RIGHT("MHF_ArrowRight"),
    STONE("mescovic"),
    COBBLE("Cobble"),
    ICROQUE("iCroque"),
    STONE_BRICK("Cakers"),
    EMERALD_ORE("Tereneckla"),
    REDSTONE_ORE("annayirb"),
    DIAMOND_ORE("acissejxd"),
    DIAMOND("AllTheDiamond"),
    IRON("metalhedd"),
    GOLD("CameronGoldRush"),
    OBSIDIAN("loiwiol"),
    ICE("icytouch"),
    DIRT("ChazOfftopic"),
    TV("GameNilo"),
    RADIO("uioz"),
    COMPUTER("CoderPuppy"),
    GLOBE("Kevos"),
    BOOKSHELF("conorf1807"),
    CLOCK("Olaf_C"),
    GIFT_GREEN("SeerPotion"),
    GIFT_RED("CruXXx"),
    LEAVES("AlphaPieter1");

    private String name;

    Head(String name) {
        this.name  = name;
    }

    public String getName() {
        return name;
    }
}
