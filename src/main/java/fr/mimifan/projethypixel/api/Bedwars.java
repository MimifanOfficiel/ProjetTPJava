package fr.mimifan.projethypixel.api;

import com.fasterxml.jackson.databind.JsonNode;
import fr.mimifan.projethypixel.api.data.Infos;

import javax.swing.*;

public class Bedwars extends Infos {

    private JsonNode infos;

    public Bedwars(JsonNode bedwarsInfos) {
        this.infos = bedwarsInfos;
        this.kills = bedwarsInfos.get("kills_bedwars").asInt();
        this.wins =  bedwarsInfos.get("wins_bedwars").asInt();
        this.losses = bedwarsInfos.get("losses_bedwars").asInt();
        this.deaths = bedwarsInfos.get("deaths_bedwars").asInt();
    }


    public JTable getStatTable() {
        JTable statTable = new JTable();



        return statTable;
    }

}
