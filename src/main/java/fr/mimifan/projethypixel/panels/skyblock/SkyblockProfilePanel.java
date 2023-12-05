package fr.mimifan.projethypixel.panels.skyblock;

import com.fasterxml.jackson.databind.JsonNode;

import javax.swing.*;
import java.util.HashMap;

public class SkyblockProfilePanel extends JPanel {

    private HashMap<String, String> profilesIdName = new HashMap<>();

    public SkyblockProfilePanel(JsonNode profiles){
        for (JsonNode profile : profiles.get("profiles")) profilesIdName.put(profile.get("profile_id").asText(), profile.get("cute_name").asText());

        profilesIdName.forEach( (id, name) -> {

        });
    }

}
