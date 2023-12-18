package fr.mimifan.projethypixel.api.data;

import com.fasterxml.jackson.databind.JsonNode;
import fr.mimifan.projethypixel.api.API;
import fr.mimifan.projethypixel.api.Player;

import java.util.HashMap;

/**
 * @author Lila
 * Class containing all information on hypixel that does not need a player to be retrieved. <br>
 * This class is used as a singleton
 */
public class HypixelData {

    /**
     * Current instance of this class.
     */
    private static final HypixelData instance = new HypixelData();

    /**
     * JsonNode for skills requirements (which total exp required for which level).
     */
    private final JsonNode skillsRequirements = API.getInstance().getSkillsLeveling();

    /**
     * Prefixes of each ranks (e.g. MVP+)
     */
    private final HashMap<String, String> rankPrefixes = new HashMap<>();

    /**
     * Hashmap containing the Integer level and Double totalExperience
     * For each level of each SkyBlock skills
     */
    private final HashMap<Integer, Double> farmingRequirements = new HashMap<>(), miningRequirements = new HashMap<>(),
    combatRequirements = new HashMap<>(), foragingRequirements = new HashMap<>(), fishingRequirements = new HashMap<>(),
    enchantingRequirements = new HashMap<>(), alchemyRequirements = new HashMap<>(), carpentryRequirements = new HashMap<>(),
    runeCraftingRequirements = new HashMap<>(), socialRequirements = new HashMap<>(), tamingRequirements = new HashMap<>();


    /**
     * Initializes default values.
     */
    public void init(){
        rankPrefixes.put(null,        "<html><font color=#9c9d97>");
        rankPrefixes.put("VIP",       "<html><font color=#80c71f>[VIP]");
        rankPrefixes.put("VIP_PLUS",  "<html><font color=#80c71f>[VIP</font><font color=red>+</font><font color=80c71f>]");
        rankPrefixes.put("MVP",       "<html><font color=#3ab3da>[MVP]");
        rankPrefixes.put("MVP_PLUS",  "<html><font color=#3ab3da>[MVP</font><font color=red>+</font><font color=#3ab3da>]");
        rankPrefixes.put("SUPERSTAR", "<html><font color=#f9801d>[MVP");
    }

    /**
     * Fills skills hashmaps with levels and experiences
     */
    public void loadSkills() {
        fillMap(farmingRequirements, "FARMING");
        fillMap(miningRequirements, "MINING");
        fillMap(combatRequirements, "COMBAT");
        fillMap(foragingRequirements, "FORAGING");
        fillMap(fishingRequirements, "FISHING");
        fillMap(enchantingRequirements, "ENCHANTING");
        fillMap(alchemyRequirements, "ALCHEMY");
        fillMap(carpentryRequirements, "CARPENTRY");
        fillMap(runeCraftingRequirements, "RUNECRAFTING");
        fillMap(socialRequirements, "SOCIAL");
        fillMap(tamingRequirements, "TAMING");
    }

    /**
     * Fills a hashmap with the level integer and double total experience value <br>
     * For all levels we can get in the given skill
     * @param map The map to fill
     * @param skillName The name of the skill to fill the map from
     */
    private void fillMap(HashMap<Integer, Double> map, String skillName) {
        for(JsonNode level : skillsRequirements.get(skillName).get("levels")) map.put(level.get("level").asInt(), level.get("totalExpRequired").asDouble());
    }


    /**
     * @return {@link HypixelData#rankPrefixes}
     */
    public HashMap<String, String> getRankPrefixes() {
        return rankPrefixes;
    }

    /**
     * Returns a playerName formatted with its rank prefix and colors.
     * @param player the player to format its name.
     * @return a String, the formatted name.
     */
    public String getFormattedName(Player player){
        if(player.isSuperstar()) return "<html><font color=#f9801d>[MVP</font><font color=red>++</font><font color=#f9801d>] " + player.getName();
        return player.getRank() + " " + player.getName() + "</font></html>";


        /*if(rankPrefixes.containsKey(player.getRank())){
            if(!rankPrefixes.get(player.getRank()).contains("PLUS")){
                return rankPrefixes.get(player.getRank()) + " " + player.getName() + "</font></html>";
            } else {
                return rankPrefixes.get(player.getRank()) + " ";
            }
        } else {
            return "<html><font color=#9c9d97>" + player.getName() + "</font></html>";
        }*/
    }

    /**
     * @return {@link HypixelData#farmingRequirements}
     */
    public HashMap<Integer, Double> getFarmingRequirements() {
        return farmingRequirements;
    }

    /**
     * @return {@link HypixelData#miningRequirements}
     */
    public HashMap<Integer, Double> getMiningRequirements() {
        return miningRequirements;
    }

    /**
     * @return {@link HypixelData#combatRequirements}
     */
    public HashMap<Integer, Double> getCombatRequirements() {
        return combatRequirements;
    }

    /**
     * @return {@link HypixelData#foragingRequirements}
     */
    public HashMap<Integer, Double> getForagingRequirements() {
        return foragingRequirements;
    }

    /**
     * @return {@link HypixelData#fishingRequirements}
     */
    public HashMap<Integer, Double> getFishingRequirements() {
        return fishingRequirements;
    }

    /**
     * @return {@link HypixelData#enchantingRequirements}
     */
    public HashMap<Integer, Double> getEnchantingRequirements() {
        return enchantingRequirements;
    }

    /**
     * @return {@link HypixelData#alchemyRequirements}
     */
    public HashMap<Integer, Double> getAlchemyRequirements() {
        return alchemyRequirements;
    }

    /**
     * @return {@link HypixelData#carpentryRequirements}
     */
    public HashMap<Integer, Double> getCarpentryRequirements() {
        return carpentryRequirements;
    }

    /**
     * @return {@link HypixelData#runeCraftingRequirements}
     */
    public HashMap<Integer, Double> getRuneCraftingRequirements() {
        return runeCraftingRequirements;
    }

    /**
     * @return {@link HypixelData#socialRequirements}
     */
    public HashMap<Integer, Double> getSocialRequirements() {
        return socialRequirements;
    }

    /**
     * @return {@link HypixelData#tamingRequirements}
     */
    public HashMap<Integer, Double> getTamingRequirements() {
        return tamingRequirements;
    }

    /**
     * @return {@link HypixelData#instance}
     */
    public static HypixelData getInstance() {
        return instance;
    }
}
