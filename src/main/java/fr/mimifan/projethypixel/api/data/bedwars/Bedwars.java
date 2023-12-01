package fr.mimifan.projethypixel.api.data.bedwars;

import com.fasterxml.jackson.databind.JsonNode;
import fr.mimifan.projethypixel.api.data.Infos;
import fr.mimifan.projethypixel.api.data.bedwars.classic.EightOne;
import fr.mimifan.projethypixel.api.data.bedwars.classic.EightTwo;
import fr.mimifan.projethypixel.api.data.bedwars.classic.FourFour;
import fr.mimifan.projethypixel.api.data.bedwars.classic.FourThree;
import fr.mimifan.projethypixel.frames.MainFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Arrays;

public class Bedwars extends Infos {

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
        model.addRow(new EightOne(infos).getRow().toArray());
        model.addRow(new EightTwo(infos).getRow().toArray());
        model.addRow(new FourThree(infos).getRow().toArray());
        model.addRow(new FourFour(infos).getRow().toArray());

        JTable table = new JTable(model);
        table.setEnabled(false);

        return table;
    }

}
