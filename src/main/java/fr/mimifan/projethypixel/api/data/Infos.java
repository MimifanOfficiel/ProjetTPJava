package fr.mimifan.projethypixel.api.data;

import javax.swing.*;
import javax.swing.table.TableColumn;

public abstract class Infos {

    protected Integer kills, deaths, wins, losses;
    protected String statName;


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

    public TableColumn getColumn() {
        TableColumn column = new TableColumn();
        column.setHeaderValue(statName);


    }
}