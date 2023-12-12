package fr.mimifan.projethypixel.api.data.skyblock;


import com.fasterxml.jackson.databind.JsonNode;
import fr.mimifan.projethypixel.api.API;

public class SkyblockInfos {

    private final String gameMode;
    private final String cuteName, profileId;

    public SkyblockInfos(String profileId, String cuteName) {
        JsonNode infos = API.getInstance().getSkyblockProfileInfos(profileId).get("profile");

        this.gameMode = infos.has("game_mode") ? infos.get("game_mode").asText() : "default";
        this.profileId = profileId;
        this.cuteName = cuteName;
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
}
