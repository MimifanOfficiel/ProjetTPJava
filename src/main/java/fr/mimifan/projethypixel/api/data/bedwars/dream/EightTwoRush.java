package fr.mimifan.projethypixel.api.data.bedwars.dream;

import com.fasterxml.jackson.databind.JsonNode;
import fr.mimifan.projethypixel.api.data.bedwars.BedwarsInfo;

public class EightTwoRush extends BedwarsInfo {
    public EightTwoRush(JsonNode infos) {
        super(infos, "eight_two_rush_");
        this.statName = "Dream 2v2 Rush";
    }
}
