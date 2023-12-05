package fr.mimifan.projethypixel.api.data.bedwars;

import com.fasterxml.jackson.databind.JsonNode;
import fr.mimifan.projethypixel.api.data.bedwars.classic.EightOne;
import fr.mimifan.projethypixel.api.data.bedwars.classic.EightTwo;
import fr.mimifan.projethypixel.api.data.bedwars.classic.FourFour;
import fr.mimifan.projethypixel.api.data.bedwars.classic.FourThree;
import fr.mimifan.projethypixel.api.data.bedwars.dream.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Bedwars extends BedwarsInfo {

    private JsonNode infos;

    public Bedwars(JsonNode bedwarsInfos, Integer level) {
        super(bedwarsInfos, "");
        this.infos  = bedwarsInfos;
        this.level = level;
        this.statName = "Total";
    }


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
        if(infos.has("eight_one_games_played_bedwars")) model.addRow(new EightOne(infos).getRow().toArray());
        if(infos.has("eight_two_games_played_bedwars")) model.addRow(new EightTwo(infos).getRow().toArray());
        if(infos.has("four_three_games_played_bedwars")) model.addRow(new FourThree(infos).getRow().toArray());
        if(infos.has("four_four_games_played_bedwars")) model.addRow(new FourFour(infos).getRow().toArray());
        if(infos.has("eight_two_void_games_played_bedwars")) model.addRow(new EightTwoVoid(infos).getRow().toArray());
        if(infos.has("four_three_void_games_played_bedwars")) model.addRow(new FourThreeVoid(infos).getRow().toArray());
        if(infos.has("four_four_void_games_played_bedwars")) model.addRow(new FourFourVoid(infos).getRow().toArray());
        if(infos.has("eight_two_rush_games_played_bedwars")) model.addRow(new EightTwoRush(infos).getRow().toArray());
        if(infos.has("eight_two_voidless_games_played_bedwars")) model.addRow(new EightTwoVoidless(infos).getRow().toArray());

        JTable table = new JTable(model);
        table.setEnabled(false);

        return table;
    }

}
