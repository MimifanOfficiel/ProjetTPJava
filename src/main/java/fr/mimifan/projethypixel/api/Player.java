package fr.mimifan.projethypixel.api;

import com.fasterxml.jackson.databind.JsonNode;
import fr.mimifan.projethypixel.api.data.HypixelData;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Date;
import java.util.Locale;

public class Player {

    private String username;
    private String uuid;
    private String hypixelRank, rankPlusColor, gameType, mode;

    private Date firstJoin, lastLogin;
    private JsonNode infos, session;
    private boolean online, isSuperstar;

    private double level;
    private int karma;

    private Bedwars bedwarsInfos;


    public Player(JsonNode infos, JsonNode session) {
        this.username = infos.get("displayname").asText();
        this.uuid = infos.get("uuid").asText();
        this.infos = infos;
        this.session = session;
        this.online = session.get("online").asBoolean();
        if(online) {
            this.gameType = session.get("gameType").asText();
            this.mode = session.get("mode").asText();
        }

        this.firstJoin = new Date(infos.get("firstLogin").asLong());
        this.lastLogin = new Date(infos.get("lastLogin").asLong());
        this.hypixelRank = infos.has("newPackageRank") ? infos.get("newPackageRank").asText() : null;
        //this.rankPlusColor = infos.get("newRankPlusColor").asText();// == null ? null : infos.get("newRankPlusColor").asText();
        this.isSuperstar = infos.has("monthlyPackageRank");

        double level = (Math.sqrt((2 * infos.get("networkExp").asDouble()) + 30625) / 50) - 2.5;
        DecimalFormat df = new DecimalFormat("#.##", DecimalFormatSymbols.getInstance(Locale.US));

        this.level = Double.parseDouble(df.format(level));
        this.karma = infos.has("karma") ? infos.get("karma").asInt() : 0;
        init();
    }

    private void init() {
        this.bedwarsInfos = new Bedwars(infos.get("stats").get("Bedwars"));
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
}
