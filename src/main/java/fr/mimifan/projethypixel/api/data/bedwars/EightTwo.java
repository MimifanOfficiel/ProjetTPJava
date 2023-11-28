package fr.mimifan.projethypixel.api.data.bedwars;

import com.fasterxml.jackson.databind.JsonNode;
import fr.mimifan.projethypixel.api.data.Infos;

public class EightTwo extends Infos {

    private final String prefix = "eight_two_";

    public EightTwo(JsonNode bedwarsInfos) {
        this.statName = "2v2";
        this.deaths = bedwarsInfos.get(prefix + "deaths_bedwars").asInt();
        this.wins   = bedwarsInfos.get(prefix + "wins_bedwars").asInt();
        this.kills  = bedwarsInfos.get(prefix + "kills_bedwars").asInt();
        this.losses = bedwarsInfos.get(prefix + "losses_bedwars").asInt();
    }

}
