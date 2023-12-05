package fr.mimifan.projethypixel.api.data.bedwars.dream;

import com.fasterxml.jackson.databind.JsonNode;
import fr.mimifan.projethypixel.api.data.bedwars.BedwarsInfo;

public class FourThreeVoid extends BedwarsInfo {
    public FourThreeVoid(JsonNode infos) {
        super(infos, "four_three_void_");
        this.statName = "Dream 3v3";
    }
}
