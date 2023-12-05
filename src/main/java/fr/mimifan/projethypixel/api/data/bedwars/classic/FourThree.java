package fr.mimifan.projethypixel.api.data.bedwars.classic;

import com.fasterxml.jackson.databind.JsonNode;
import fr.mimifan.projethypixel.api.data.bedwars.BedwarsInfo;

public class FourThree extends BedwarsInfo {

    public FourThree(JsonNode infos) {
        super(infos, "four_three_");
        this.statName = "3v3v3v3";
    }

}
