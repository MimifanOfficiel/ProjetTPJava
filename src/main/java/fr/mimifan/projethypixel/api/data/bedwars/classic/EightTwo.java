package fr.mimifan.projethypixel.api.data.bedwars.classic;

import com.fasterxml.jackson.databind.JsonNode;
import fr.mimifan.projethypixel.api.data.Infos;

import java.util.HashMap;
import java.util.List;

public class EightTwo extends Infos {

    public EightTwo(JsonNode bedwarsInfos) {
        super(bedwarsInfos, "eight_two_");
        this.statName = "Doubles";
    }

}
