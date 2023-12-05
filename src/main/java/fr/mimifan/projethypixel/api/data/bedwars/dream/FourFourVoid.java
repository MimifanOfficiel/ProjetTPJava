package fr.mimifan.projethypixel.api.data.bedwars.dream;

import com.fasterxml.jackson.databind.JsonNode;
import fr.mimifan.projethypixel.api.data.bedwars.BedwarsInfo;

public class FourFourVoid extends BedwarsInfo {
    public FourFourVoid(JsonNode infos) {
        super(infos, "four_four_void_");
        this.statName = "Dream 4v4";
    }
}
