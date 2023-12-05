package fr.mimifan.projethypixel.api.data.bedwars.dream;

import com.fasterxml.jackson.databind.JsonNode;
import fr.mimifan.projethypixel.api.data.bedwars.BedwarsInfo;

public class EightTwoVoid extends BedwarsInfo {
    public EightTwoVoid(JsonNode infos) {
        super(infos, "eight_two_void_");
        this.statName = "Dream 2v2";
    }
}
