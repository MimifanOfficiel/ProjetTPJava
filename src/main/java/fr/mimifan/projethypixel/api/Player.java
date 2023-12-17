package fr.mimifan.projethypixel.api;

import com.fasterxml.jackson.databind.JsonNode;
import fr.mimifan.projethypixel.api.data.HypixelData;
import fr.mimifan.projethypixel.api.data.bedwars.Bedwars;
import fr.mimifan.projethypixel.api.data.buildbattle.BuildBattle;
import fr.mimifan.projethypixel.api.data.murder.MurderMystery;
import fr.mimifan.projethypixel.api.data.skyblock.SkyblockInfos;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

/**
 * Class containing every information needed about a player on Hypixel.
 */
public class Player {

    private final String username;
    private final String uuid;
    private final String hypixelRank;
    private String rankPlusColor;
    private String gameType;
    private String mode;

    private final Date firstJoin, lastLogin;
    private final boolean online, isSuperstar;

    private final double level;
    private final int karma;

    private Bedwars bedwarsInfos;
    private MurderMystery murderInfos;
    private BuildBattle buildBattleInfos;

    private final HashMap<String, String> skyblockProfiles = new HashMap<>();
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


    public String getName() {
        return username;
    }
    public String getUUID() {
        return uuid;
    }
    public Date getFirstJoin() {
        return firstJoin;
    }
    public Date getLastLogin() {
        return lastLogin;
    }
    public boolean isOnline() {
        return online;
    }
    public Bedwars getBedwarsInfos() {
        return bedwarsInfos;
    }

    public MurderMystery getMurderMysteryInfos() {
        return murderInfos;
    }

    public BuildBattle getBuildBattleInfos() {
        return buildBattleInfos;
    }

    public String getRank() {
        return HypixelData.getInstance().getRankPrefixes().get(hypixelRank);
    }
    public boolean isSuperstar() {
        return isSuperstar;
    }
    public String getRankPlusColor() {
        return rankPlusColor;
    }
    public double getLevel() {
        return level;
    }
    public int getKarma() {
        return karma;
    }
    public String getGameType() {
        return gameType;
    }
    public String getMode(){
        if(gameType.equals("SKYBLOCK") && mode.equalsIgnoreCase("dynamic")) return "Someone's Island";
        return mode;
    }

    public JsonNode getSbNode() {
        return sbNode;
    }

    public HashMap<String, String> getSkyblockProfiles() {
        return skyblockProfiles;
    }
}
