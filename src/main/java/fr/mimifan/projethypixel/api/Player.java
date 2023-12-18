package fr.mimifan.projethypixel.api;

import com.fasterxml.jackson.databind.JsonNode;
import fr.mimifan.projethypixel.api.data.HypixelData;
import fr.mimifan.projethypixel.api.data.bedwars.Bedwars;
import fr.mimifan.projethypixel.api.data.buildbattle.BuildBattle;
import fr.mimifan.projethypixel.api.data.murder.MurderMystery;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

/**
 * Class Player
 * Contains every information needed about a player on Hypixel.
 */
public class Player {

    /**
     * Player's name.
     */
    private final String username;

    /**
     * Player's unique identifier.
     */
    private final String uuid;

    /**
     * Player's rank on hypixel.
     */
    private final String hypixelRank;

    /**
     * Player's MVP+ (or MVP++) color if they have one.
     */
    @SuppressWarnings("unused")
    private String rankPlusColor;

    /**
     * The type of game player is on (if online)
     */
    private String gameType;

    /**
     * The mode of {@link Player#gameType} player is on.
     */
    private String mode;

    /**
     * The dates of player's first join and last login.
     */
    private final Date firstJoin, lastLogin;

    /**
     * Booleans indicating whether player is online/offline, mvp++ or not.
     */
    private final boolean online, isSuperstar;

    /**
     * Player's hypixel level.
     */
    private final double level;

    /**
     * Player's karma.
     */
    private final int karma;

    /**
     * Player's bedwars' stats.
     */
    private Bedwars bedwarsInfos;

    /**
     * Player's MurderMystery's stats.
     */
    private MurderMystery murderInfos;

    /**
     * Player's BuildBattle's stats.
     */
    private BuildBattle buildBattleInfos;

    /**
     * Player's skyblock profiles cuteName/ID.
     */
    private final HashMap<String, String> skyblockProfiles = new HashMap<>();

    /**
     * The JsonNode with all player's skyblock's data.
     */
    private final JsonNode sbNode;


    /**
     * Constructs a new instance of player with the two JsonNodes
     * @param infos all Hypixel stats related information
     * @param session session information (wether player is online or not)
     */
    public Player(JsonNode infos, JsonNode session) {
        this.username = infos.get("displayname").asText();
        this.uuid = infos.get("uuid").asText();
        this.online = session.get("online").asBoolean();
        if(infos.get("stats").has("Bedwars")) this.bedwarsInfos = new Bedwars(infos.get("stats").get("Bedwars"), infos.get("achievements").get("bedwars_level").asInt());
        if(infos.get("stats").has("MurderMystery")) this.murderInfos = new MurderMystery(infos.get("stats").get("MurderMystery"));
        if(infos.get("stats").has("BuildBattle")) this.buildBattleInfos = new BuildBattle(infos.get("stats").get("BuildBattle"));
        if(online) {
            this.gameType = session.get("gameType").asText();
            this.mode = session.get("mode").asText();
        }

        this.firstJoin = new Date(infos.get("firstLogin").asLong());
        this.lastLogin = new Date(infos.get("lastLogin").asLong());
        this.hypixelRank = infos.has("newPackageRank") ? infos.get("newPackageRank").asText() : null;
        //this.rankPlusColor = infos.get("newRankPlusColor").asText();// == null ? null : infos.get("newRankPlusColor").asText();
        this.isSuperstar = infos.has("monthlyPackageRank");

        double level = infos.has("networkExp") ? (Math.sqrt((2 * infos.get("networkExp").asDouble()) + 30625) / 50 - 2.5) : 0;
        DecimalFormat df = new DecimalFormat("#.##", DecimalFormatSymbols.getInstance(Locale.US));

        this.level = Double.parseDouble(df.format(level));
        this.karma = infos.has("karma") ? infos.get("karma").asInt() : 0;

        sbNode = infos.get("stats").has("SkyBlock") ? infos.get("stats").get("SkyBlock").get("profiles") : null;

        if(sbNode != null)
            for (JsonNode entry : sbNode) skyblockProfiles.put(entry.get("profile_id").asText(), entry.get("cute_name").asText());
    }


    /**
     * @return {@link Player#username}
     */
    public String getName() {
        return username;
    }

    /**
     * @return {@link Player#uuid}
     */
    public String getUUID() {
        return uuid;
    }

    /**
     * @return {@link Player#firstJoin}
     */
    public Date getFirstJoin() {
        return firstJoin;
    }

    /**
     * @return {@link Player#lastLogin}
     */
    public Date getLastLogin() {
        return lastLogin;
    }

    /**
     * @return {@link Player#online}
     */
    public boolean isOnline() {
        return online;
    }

    /**
     * @return {@link Player#bedwarsInfos}
     */
    @SuppressWarnings("unused")
    public Bedwars getBedwarsInfos() {
        return bedwarsInfos;
    }

    /**
     * @return {@link Player#murderInfos}
     */
    @SuppressWarnings("unused")
    public MurderMystery getMurderMysteryInfos() {
        return murderInfos;
    }

    /**
     * @return {@link Player#buildBattleInfos}
     */
    @SuppressWarnings("unused")
    public BuildBattle getBuildBattleInfos() {
        return buildBattleInfos;
    }

    /**
     * @return {@link Player#hypixelRank}
     */
    public String getRank() {
        return HypixelData.getInstance().getRankPrefixes().get(hypixelRank);
    }

    /**
     * @return {@link Player#isSuperstar}
     */
    public boolean isSuperstar() {
        return isSuperstar;
    }

    /**
     * @return {@link Player#rankPlusColor}
     */
    public String getRankPlusColor() {
        return rankPlusColor;
    }

    /**
     * @return {@link Player#level}
     */
    public double getLevel() {
        return level;
    }

    /**
     * @return {@link Player#karma}
     */
    public int getKarma() {
        return karma;
    }

    /**
     * @return {@link Player#gameType}
     */
    public String getGameType() {
        return gameType;
    }

    /**
     * @return {@link Player#gameType}
     */
    public String getMode(){
        if(gameType.equals("SKYBLOCK") && mode.equalsIgnoreCase("dynamic")) return "Someone's Island";
        return mode;
    }

    /**
     * @return {@link Player#sbNode}
     */
    public JsonNode getSbNode() {
        return sbNode;
    }

    /**
     * @return {@link Player#skyblockProfiles}
     */
    public HashMap<String, String> getSkyblockProfiles() {
        return skyblockProfiles;
    }
}
