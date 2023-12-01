package fr.mimifan.projethypixel.api.data.bedwars.classic;

import com.fasterxml.jackson.databind.JsonNode;
import fr.mimifan.projethypixel.api.data.Infos;

public class EightOne extends Infos {

    public EightOne(JsonNode infos) {
        super(infos, "eight_one_");
        this.statName = "Solo";
    }
}
