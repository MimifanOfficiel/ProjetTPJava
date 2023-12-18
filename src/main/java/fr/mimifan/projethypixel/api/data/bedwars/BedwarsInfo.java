package fr.mimifan.projethypixel.api.data.bedwars;

import com.fasterxml.jackson.databind.JsonNode;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Class BedwarsInfos
 * Contains all information about a player's bedwars stats.
 */
public class BedwarsInfo {

    /**
     * The statistics to retrieve from each bedwars mode.
     */
    protected Integer played, wins, losses, kills, deaths, bedsBroken, bedsLost;

    /**
     * The global statistics to retrieve from the bedwars stats.
     */
    protected Integer level, coins;

    /**
     * The total amount of resources picked up by the player.
     */
    protected Integer totalIronIngots, totalGoldIngots, totalDiamonds, totalEmeralds;

    /**
     * The amount of kills per deaths for each mode.
     */
    protected Double ratioKD;

    /**
     * The name of the game mode.
     */
    protected String modeName;

    /**
     * The default constructor
     * @param bedwarsInfos the JsonNode containing all bedwars information
     * @param modePrefix the gamemode prefix in bedwarsInfos
     * @param modeName the name of the game mode
     */
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

    /**
     * @return {@link BedwarsInfo#level}
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * @return {@link BedwarsInfo#coins}
     */
    public Integer getCoins() {
        return coins;
    }

    /**
     * @return {@link BedwarsInfo#deaths}
     */
    public Integer getDeaths() {
        return deaths;
    }

    /**
     * @return {@link BedwarsInfo#kills}
     */
    public Integer getKills() {
        return kills;
    }

    /**
     * @return {@link BedwarsInfo#losses}
     */
    public Integer getLosses() {
        return losses;
    }

    /**
     * @return {@link BedwarsInfo#wins}
     */
    public Integer getWins() {
        return wins;
    }

    /**
     * @return {@link BedwarsInfo#modeName}
     */
    public String getModeName() {
        return modeName;
    }

    /**
     * @return {@link BedwarsInfo#totalDiamonds}
     */
    public Integer getTotalDiamonds() {
        return totalDiamonds;
    }

    /**
     * @return {@link BedwarsInfo#totalIronIngots}
     */
    public Integer getTotalIronIngots() {
        return totalIronIngots;
    }

    /**
     * @return {@link BedwarsInfo#totalEmeralds}
     */
    public Integer getTotalEmeralds() {
        return totalEmeralds;
    }

    /**
     * @return {@link BedwarsInfo#totalGoldIngots}
     */
    public Integer getTotalGoldIngots() {
        return totalGoldIngots;
    }

    /**
     * Returns the row of data for one bedwars mode.
     * @return A List of Object containing the data for a bedwars mode.
     */
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