package fr.mimifan.projethypixel.api.data.bedwars.classic;

import com.fasterxml.jackson.databind.JsonNode;
import fr.mimifan.projethypixel.api.data.bedwars.BedwarsInfo;

public class EightOne extends BedwarsInfo {

    public EightOne(JsonNode infos) {
        super(infos, "eight_one_");
        this.statName = "Solo";
    }
}
