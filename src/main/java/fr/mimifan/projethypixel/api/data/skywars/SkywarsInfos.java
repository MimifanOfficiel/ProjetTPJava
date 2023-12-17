package fr.mimifan.projethypixel.api.data.skywars;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.List;

public class SkywarsInfos {

    private final Integer played, blockPlaced, chestsOpened, deaths, losses, quits;
    private final String modeName;

    public SkywarsInfos(JsonNode skywarsInfos, String modePrefix, String modeName) {
        this.modeName = modeName;
        this.played = skywarsInfos.has("games_played_skywars" + modePrefix) ? skywarsInfos.get("games_played_skywars" + modePrefix).asInt() : 0;
        this.deaths = skywarsInfos.has("deaths" + modePrefix) ? skywarsInfos.get("deaths" + modePrefix).asInt() : 0;
        this.losses = skywarsInfos.has("losses" + modePrefix) ? skywarsInfos.get("losses" + modePrefix).asInt() : 0;
        this.quits =  skywarsInfos.has("quits" + modePrefix) ? skywarsInfos.get("quits" + modePrefix).asInt() : 0;
        this.chestsOpened = skywarsInfos.has("chests_opened" + modePrefix) ? skywarsInfos.get(modePrefix + "chests_opened").asInt() : 0;
        this.blockPlaced = skywarsInfos.has("blocks_placed" + modePrefix) ? skywarsInfos.get(modePrefix + "blocks_placed").asInt() : 0;
    }

    public List<Object> getRow() {
        List<Object> data = new ArrayList<>();
        data.add(modeName);
        data.add(played);
        data.add(losses);
        data.add(deaths);
        data.add(blockPlaced);
        data.add(chestsOpened);
        data.add(quits);

        return data;
    }

}
