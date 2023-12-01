package fr.mimifan.projethypixel.api.data;

import com.fasterxml.jackson.databind.JsonNode;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public abstract class Infos {

    protected Integer played, wins, losses, kills, deaths, bedsBroken, bedsLost;
    protected Integer level;
    protected Double ratioKD;
    protected String statName;

    public Infos(JsonNode infos, String modePrefix) {
        this.played = infos.get(modePrefix + "games_played_bedwars").asInt();
        this.wins   = infos.get(modePrefix + "wins_bedwars").asInt();
        this.losses = infos.get(modePrefix + "losses_bedwars").asInt();
        this.kills  = infos.get(modePrefix + "kills_bedwars").asInt();
        this.deaths = infos.get(modePrefix + "deaths_bedwars").asInt();
        this.bedsBroken = infos.get(modePrefix + "beds_broken_bedwars").asInt();
        this.bedsLost   = infos.get(modePrefix + "beds_lost_bedwars").asInt();

        DecimalFormat df = new DecimalFormat("#.##", DecimalFormatSymbols.getInstance(Locale.US));
        this.ratioKD = (double) kills/deaths;
        this.ratioKD = Double.parseDouble(df.format(this.ratioKD));
    }

    public Integer getLevel() {
        return level;
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