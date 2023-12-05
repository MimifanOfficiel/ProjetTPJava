package fr.mimifan.projethypixel.api.data.bedwars.classic;

import com.fasterxml.jackson.databind.JsonNode;
import fr.mimifan.projethypixel.api.data.bedwars.BedwarsInfo;

public class EightTwo extends BedwarsInfo {

    public EightTwo(JsonNode bedwarsInfos) {
        super(bedwarsInfos, "eight_two_");
        this.statName = "Doubles";
    }

}
