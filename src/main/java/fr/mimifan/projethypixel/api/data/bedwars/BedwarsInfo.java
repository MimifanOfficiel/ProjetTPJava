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
    protected String statName;

    public BedwarsInfo(JsonNode infos, String modePrefix, String statName) {
        this.statName = statName;
        this.played = infos.has(modePrefix + "games_played_bedwars") ? infos.get(modePrefix + "games_played_bedwars").asInt() : null;
        this.wins   = infos.has(modePrefix + "wins_bedwars") ? infos.get(modePrefix + "wins_bedwars").asInt() : null;
        this.losses = infos.has(modePrefix + "losses_bedwars") ? infos.get(modePrefix + "losses_bedwars").asInt() : null;
        this.kills  = infos.has(modePrefix + "kills_bedwars") ? infos.get(modePrefix + "kills_bedwars").asInt() : null;
        this.deaths = infos.has(modePrefix + "deaths_bedwars") ? infos.get(modePrefix + "deaths_bedwars").asInt() : null;
        this.bedsBroken = infos.has(modePrefix + "beds_broken_bedwars") ? infos.get(modePrefix + "beds_broken_bedwars").asInt() : null;
        this.bedsLost   = infos.has(modePrefix + "beds_lost_bedwars") ? infos.get(modePrefix + "beds_lost_bedwars").asInt() : null;

        DecimalFormat df = new DecimalFormat("#.##", DecimalFormatSymbols.getInstance(Locale.US));
        this.ratioKD = (double) kills/deaths;
        this.ratioKD = Double.parseDouble(df.format(this.ratioKD));

        this.coins = infos.has("coins") ? infos.get("coins").asInt() : 0;
        this.totalIronIngots = infos.has("iron_resources_collected_bedwars") ? infos.get("iron_resources_collected_bedwars").asInt() : 0;
        this.totalGoldIngots = infos.has("gold_resources_collected_bedwars") ? infos.get("gold_resources_collected_bedwars").asInt() : 0;
        this.totalDiamonds = infos.has("diamond_resources_collected_bedwars") ? infos.get("diamond_resources_collected_bedwars").asInt() : 0;
        this.totalEmeralds = infos.has("emerald_resources_collected_bedwars") ? infos.get("emerald_resources_collected_bedwars").asInt() : 0;
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

    public String getStatName() {
        return statName;
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
        data.add(statName);
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