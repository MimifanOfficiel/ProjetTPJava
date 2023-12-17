package fr.mimifan.projethypixel.api.data.bedwars;

import com.fasterxml.jackson.databind.JsonNode;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BedwarsInfo {

    protected Integer played, wins, losses, kills, deaths, bedsBroken, bedsLost;
    protected Integer level, coins;
    protected Integer totalIronIngots, totalGoldIngots, totalDiamonds, totalEmeralds;
    protected Double ratioKD;
    protected String modeName;

    public BedwarsInfo(JsonNode bedwarsInfos, String modePrefix, String modeName) {
        this.modeName = modeName;
        this.played     = bedwarsInfos.has(modePrefix + "games_played_bedwars") ? bedwarsInfos.get(modePrefix + "games_played_bedwars").asInt() : 0;
        this.wins       = bedwarsInfos.has(modePrefix + "wins_bedwars") ? bedwarsInfos.get(modePrefix + "wins_bedwars").asInt() : 0;
        this.losses     = bedwarsInfos.has(modePrefix + "losses_bedwars") ? bedwarsInfos.get(modePrefix + "losses_bedwars").asInt() : 0;
        this.kills      = bedwarsInfos.has(modePrefix + "kills_bedwars") ? bedwarsInfos.get(modePrefix + "kills_bedwars").asInt() : 0;
        this.deaths     = bedwarsInfos.has(modePrefix + "deaths_bedwars") ? bedwarsInfos.get(modePrefix + "deaths_bedwars").asInt() : 0;
        this.bedsBroken = bedwarsInfos.has(modePrefix + "beds_broken_bedwars") ? bedwarsInfos.get(modePrefix + "beds_broken_bedwars").asInt() : 0;
        this.bedsLost   = bedwarsInfos.has(modePrefix + "beds_lost_bedwars") ? bedwarsInfos.get(modePrefix + "beds_lost_bedwars").asInt() : 0;

        DecimalFormat df = new DecimalFormat("#.##", DecimalFormatSymbols.getInstance(Locale.US));
        if(deaths > 0) this.ratioKD = (double) kills/deaths;
        else this.ratioKD = (double) kills;
        this.ratioKD = Double.parseDouble(df.format(this.ratioKD));

        this.coins = bedwarsInfos.has("coins") ? bedwarsInfos.get("coins").asInt() : 0;
        this.totalIronIngots = bedwarsInfos.has("iron_resources_collected_bedwars") ? bedwarsInfos.get("iron_resources_collected_bedwars").asInt() : 0;
        this.totalGoldIngots = bedwarsInfos.has("gold_resources_collected_bedwars") ? bedwarsInfos.get("gold_resources_collected_bedwars").asInt() : 0;
        this.totalDiamonds = bedwarsInfos.has("diamond_resources_collected_bedwars") ? bedwarsInfos.get("diamond_resources_collected_bedwars").asInt() : 0;
        this.totalEmeralds = bedwarsInfos.has("emerald_resources_collected_bedwars") ? bedwarsInfos.get("emerald_resources_collected_bedwars").asInt() : 0;
    }

    public Integer getLevel() {
        return level;
    }

    public Integer getCoins() {
        return coins;
    }

    public Integer getDeaths() {
        return deaths;
    }

    public Integer getKills() {
        return kills;
    }

    public Integer getLosses() {
        return losses;
    }

    public Integer getWins() {
        return wins;
    }

    public String getModeName() {
        return modeName;
    }

    public Integer getTotalDiamonds() {
        return totalDiamonds;
    }

    public Integer getTotalIronIngots() {
        return totalIronIngots;
    }

    public Integer getTotalEmeralds() {
        return totalEmeralds;
    }

    public Integer getTotalGoldIngots() {
        return totalGoldIngots;
    }

    public List<Object> getRow() {
        List<Object> data = new ArrayList<>();
        data.add(modeName);
        data.add(played);
        data.add(wins);
        data.add(losses);
        data.add(kills);
        data.add(deaths);
        data.add(bedsBroken);
        data.add(bedsLost);
        data.add(ratioKD);

        return data;
    }
}