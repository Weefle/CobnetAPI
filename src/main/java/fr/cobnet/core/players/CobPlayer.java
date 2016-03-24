package fr.cobnet.core.players;

import fr.cobnet.api.i18n.Lang;
import fr.cobnet.api.players.IndividualScoreboard;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.*;

public class CobPlayer {

    private static final String PRE_SCOREBOARD = "§6§lCOBNET MC §7— §6";

    private static Map<UUID, CobPlayer> players = new HashMap<UUID, CobPlayer>();

    private UUID uuid;

    private Rank realRank;

    public Rank getRealRank() {
        return realRank;
    }

    private Rank displayRank;

    public Rank getDisplayRank() {
        return displayRank;
    }

    private Lang lang = Lang.FRENCH;

    public Lang getLang() {
        return lang;
    }

    private int coins = 0;

    public int getCoins() {
        return coins;
    }

    private List<Purchase> purchases;

    public List<Purchase> getPurchases() {
        return purchases;
    }

    private List<Purchase> gadgets;

    public List<Purchase> getGadgets() {
        return gadgets;
    }

    private int coinsToAdd = 0;

    public int getCoinsToAdd() {
        return coinsToAdd;
    }

    private long nextCommand = 0;

    public long getNextCommand() {
        return nextCommand;
    }

    public void setNextCommand(long nextCommand) {
        this.nextCommand = nextCommand;
    }

    private long nextChat = 0;

    public long getNextChat() {
        return nextChat;
    }

    public void setNextChat(long nextChat) {
        this.nextChat = nextChat;
    }

    private String lastChatMessage = "";

    public String getLastChatMessage() {
        return lastChatMessage;
    }

    public void setLastChatMessage(String lastChatMessage) {
        this.lastChatMessage = lastChatMessage;
    }

    private boolean listensChat = true;

    public boolean listensChat() {
        return listensChat;
    }

    private boolean allowFriendNotifications;

    public boolean isAllowFriendNotifications() {
        return allowFriendNotifications;
    }

    private long joinTime;

    public long getJoinTime() {
        return joinTime;
    }

    private IndividualScoreboard scoreboard;

    public IndividualScoreboard getScoreboard() {
        return scoreboard;
    }

    private boolean displayRankLoaded = false;

    public boolean hasDisplayRankLoaded() {
        return displayRankLoaded;
    }

    void setDisplayRankLoaded(boolean displayRankLoaded) {
        this.displayRankLoaded = displayRankLoaded;
    }

    // nicknames
    private String nickname;

    public String getNickname() {
        return nickname;
    }

    private String customSkin;

    public String getCustomSkin() {
        return customSkin;
    }

    public CobPlayer(Player player) {
        this.uuid = player.getUniqueId();
        this.realRank = Rank.JOUEUR;
        this.displayRank = Rank.JOUEUR;
        this.purchases = new ArrayList<>();
        this.gadgets = new ArrayList<>();
        this.joinTime = System.currentTimeMillis();

        players.put(player.getUniqueId(), this);
    }

    public CobPlayer(Player player, int coins, Rank rank, Rank displayRank, Lang lang) {
        this.uuid = player.getUniqueId();
        this.coins = coins;
        this.realRank = rank;
        this.displayRank = displayRank;
        this.purchases = new ArrayList<>();
        this.gadgets = new ArrayList<>();
        this.lang = lang;
        this.joinTime = System.currentTimeMillis();

        players.put(this.uuid, this);
    }

    /**
     * @TODO: i18n
     */
    public void modifyCoins(CoinsModifier modifier) {
        Player player = getPlayer();

        this.coins += modifier.getAmount();
        if (modifier.isPositive())
            player.sendMessage("§aGain de Thunes " + modifier.getCurrency().getName() + " : §6+" + modifier.getAmount() + " §7(" + modifier.getReason() + ")");
        else
            player.sendMessage("§cVous avez été débité de §b" + modifier.getAmount() + " " + modifier.getCurrency().getName());
    }

    public void makeScoreboard(String objectiveName) {
        scoreboard = new IndividualScoreboard(getPlayer(), PRE_SCOREBOARD + objectiveName);
        scoreboard.make();

        new ScoreboardAnimationScheduler(this);
    }

    public UUID getUniqueId() {
        return this.uuid;
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(this.uuid);
    }

    public void remove() {
        if (scoreboard != null) scoreboard.end();

        players.remove(this.uuid);
    }

    public static Collection<CobPlayer> all() {
        return players.values();
    }

    public static CobPlayer findByUuid(UUID uuid) {
        return players.get(uuid);
    }

    public static CobPlayer findByPlayer(Player player) {
        return findByUuid(player.getUniqueId());
    }
}