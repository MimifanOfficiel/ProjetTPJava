package fr.mimifan.projethypixel.api.data.bedwars.dream;

import com.fasterxml.jackson.databind.JsonNode;
import fr.mimifan.projethypixel.api.data.bedwars.BedwarsInfo;

public class EightTwoVoidless extends BedwarsInfo {
    public EightTwoVoidless(JsonNode infos) {
        super(infos, "eight_two_voidless_");
        this.statName = "2v2 Voidless";
    }
}
