package fr.mimifan.projethypixel.api.data;

import java.awt.*;
import java.util.HashMap;

public class HypixelData {

    private static final HypixelData instance = new HypixelData();

    private HashMap<String, String> rankPrefixes = new HashMap<String, String>();
    private HashMap<String, Color> rankColors  = new HashMap<String, Color>();


    public void init(){
        rankPrefixes.put("MVP_PLUS", "[MVP+]");
        rankPrefixes.put("", "");
    }


    public HashMap<String, String> getRankPrefixes() {
        return rankPrefixes;
    }

    public HashMap<String, Color> getRankColors() {
        return rankColors;
    }

    public static HypixelData getInstance() {
        return instance;
    }
}
