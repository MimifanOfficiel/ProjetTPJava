package fr.mimifan.projethypixel.api.data.skyblock;


import com.fasterxml.jackson.databind.JsonNode;
import fr.mimifan.projethypixel.api.API;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SkyblockInfos {

    private final String gameMode;
    private final String cuteName, profileId;
    private final List<String> coopMembers = new ArrayList<>();
    private final HashMap<String, Integer> skills = new HashMap<>();

    public SkyblockInfos(String profileId, String cuteName) {
        JsonNode infos = API.getInstance().getSkyblockProfileInfos(profileId).get("profile");

        this.gameMode = infos.has("game_mode") ? infos.get("game_mode").asText() : "default";
        this.profileId = profileId;
        this.cuteName = cuteName;

        if(infos.has("members")) for(JsonNode entry : infos.get("members")) coopMembers.add(API.getInstance().getName(entry.get("player_id").asText()));

    }


    public String getCuteName() {
        return cuteName;
    }

    public String getProfileId() {
        return profileId;
    }

    public String getGameMode() {
        return gameMode;
    }

    public String getTooltipText() {
        StringBuilder tooltipBuilder = new StringBuilder();
        tooltipBuilder.append("<html>");

        if(ironMan()) tooltipBuilder.append("<font color=#AAAAAA>Ironman </font>");
        if(bingo()) tooltipBuilder.append("<font color=#AAAAAA>Bingo </font>");
        if(stranded()) tooltipBuilder.append("<font color=#55FF55>Stranded </font>");

        tooltipBuilder.append("<font color=#FFFF55>Profile: </font>");
        tooltipBuilder.append("<font color=#55FF55>").append(cuteName).append("</font><br>");

        if(coopMembers.size() > 1) {
            tooltipBuilder.append("<font color=#55FFFF>Co-op with ").append(coopMembers.size()-1).append(" players:</font><br>");
            for (int i = 1; i < coopMembers.size(); i++) {
                tooltipBuilder.append("- <font color=#55FF55>").append(coopMembers.get(i)).append("</font><br>");
            }
        }


        tooltipBuilder.append("</html>");

        return tooltipBuilder.toString();
    }

    public boolean ironMan() {
        return gameMode.equals("ironman");
    }

    public boolean isDefault() {
        return gameMode.equals("default");
    }

    public boolean stranded() {
        return gameMode.equals("island");
    }

    public boolean bingo() {
        return gameMode.equals("bingo");
    }

}
