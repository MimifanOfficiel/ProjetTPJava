package fr.mimifan.projethypixel.api.data.bedwars.classic;

import com.fasterxml.jackson.databind.JsonNode;
import fr.mimifan.projethypixel.api.data.Infos;

public class FourFour extends Infos {

    public FourFour(JsonNode infos) {
        super(infos, "four_four_");
        this.statName = "4v4v4v4";
    }

}
