package fr.mimifan.projethypixel.api.data.bedwars;

import com.fasterxml.jackson.databind.JsonNode;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Class Bedwars
 * Loads all data of bedwars gamemode.
 */
public class Bedwars extends BedwarsInfo {

    /**
     * The JsonNode containing all bedwars data.
     */
    private JsonNode infos;

    /**
     * Constructor by default.
     * @param bedwarsInfos {@link Bedwars#infos}
     * @param level The player's bedwars level.
     */
    public Bedwars(JsonNode bedwarsInfos, Integer level) {
        super(bedwarsInfos, "", "Total");
        this.infos  = bedwarsInfos;
        this.level = level;
    }

    /**
     * @return A JTable containing all player's stats on bedwars.
     */
    public JTable getStatTable() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("GameMode");
        model.addColumn("Games Played");
        model.addColumn("Wins");
        model.addColumn("Losses");
        model.addColumn("Kills");
        model.addColumn("Deaths");
        model.addColumn("Beds Broken");
        model.addColumn("Beds Lost");
        model.addColumn("Ratio K/D");

        model.addRow(getRow().toArray());
        if(infos.has("eight_one_games_played_bedwars")) model.addRow(new BedwarsInfo(infos, "eight_one_", "Solo").getRow().toArray());
        if(infos.has("eight_two_games_played_bedwars")) model.addRow(new BedwarsInfo(infos, "eight_two_", "Doubles").getRow().toArray());
        if(infos.has("four_three_games_played_bedwars")) model.addRow(new BedwarsInfo(infos, "four_three_", "3v3v3v3").getRow().toArray());
        if(infos.has("four_four_games_played_bedwars")) model.addRow(new BedwarsInfo(infos, "four_four_", "4v4v4v4").getRow().toArray());
        if(infos.has("eight_two_void_games_played_bedwars")) model.addRow(new BedwarsInfo(infos, "eight_two_void_", "Dream 2v2").getRow().toArray());
        if(infos.has("four_three_void_games_played_bedwars")) model.addRow(new BedwarsInfo(infos, "four_three_void_", "Dream 3v3").getRow().toArray());
        if(infos.has("four_four_void_games_played_bedwars")) model.addRow(new BedwarsInfo(infos, "four_four_void_", "Dream 4v4").getRow().toArray());
        if(infos.has("eight_two_rush_games_played_bedwars")) model.addRow(new BedwarsInfo(infos, "eight_two_rush_", "Dream 2v2 Rush").getRow().toArray());
        if(infos.has("eight_two_voidless_games_played_bedwars")) model.addRow(new BedwarsInfo(infos, "eight_two_voidless_", "2v2 Voidless").getRow().toArray());

        JTable table = new JTable(model);
        table.setEnabled(false);

        return table;
    }

}
