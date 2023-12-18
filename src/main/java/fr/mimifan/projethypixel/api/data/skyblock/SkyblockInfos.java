package fr.mimifan.projethypixel.api.data.skyblock;

import com.fasterxml.jackson.databind.JsonNode;
import fr.mimifan.projethypixel.api.API;
import fr.mimifan.projethypixel.api.data.HypixelData;
import fr.mimifan.projethypixel.utils.IntegerToRoman;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Class SkyblockInfos
 * Contains all stats about a Skyblock profile, identified by its profileId.
 */
public class SkyblockInfos {

    private final String gameMode;
    private final String cuteName;
    private final String profileId;
    private final List<String> coopMembers = new ArrayList<>();
    private Double farmingExp, miningExp, combatExp, foragingExp, fishingExp,
            enchantingExp, alchemyExp, carpentryExp, runeCraftingExp, socialExp, tamingExp;
    private final HashMap<String, Integer> skills = new HashMap<>();
    private final Integer unlockedCollection;
    private final Integer MAXCOLLECTIONS = 73;

    /**
     * Default constructor
     * @param ownerUUID The unique ID of the player who created this profile.
     * @param profileId The ID of the profile.
     * @param cuteName The name of the profile.
     */
    public SkyblockInfos(String ownerUUID, String profileId, String cuteName) {
        JsonNode infos = API.getInstance().getSkyblockProfileInfos(profileId).get("profile");

        this.gameMode = infos.has("game_mode") ? infos.get("game_mode").asText() : "default";
        this.profileId = profileId;
        this.cuteName = cuteName;

        if(infos.has("members")) for(JsonNode entry : infos.get("members")) coopMembers.add(API.getInstance().getName(entry.get("player_id").asText()));

        JsonNode experience  = infos.get("members").has(ownerUUID) ? infos.get("members").get(ownerUUID): null;
        if(experience != null) {
            this.farmingExp      = experience.has("experience_skill_farming")      ? experience.get("experience_skill_farming").asDouble() : 0;
            this.miningExp       = experience.has("experience_skill_mining")       ? experience.get("experience_skill_mining").asDouble() : 0;
            this.combatExp       = experience.has("experience_skill_combat")       ? experience.get("experience_skill_combat").asDouble() : 0;
            this.foragingExp     = experience.has("experience_skill_foraging")     ? experience.get("experience_skill_foraging").asDouble() : 0;
            this.fishingExp      = experience.has("experience_skill_fishing")      ? experience.get("experience_skill_fishing").asDouble() : 0;
            this.enchantingExp   = experience.has("experience_skill_enchanting")   ? experience.get("experience_skill_enchanting").asDouble() : 0;
            this.alchemyExp      = experience.has("experience_skill_alchemy")      ? experience.get("experience_skill_alchemy").asDouble() : 0;
            this.carpentryExp    = experience.has("experience_skill_carpentry")    ? experience.get("experience_skill_carpentry").asDouble() : 0;
            this.runeCraftingExp = experience.has("experience_skill_runecrafting") ? experience.get("experience_skill_runecrafting").asDouble() : 0;
            this.socialExp       = experience.has("experience_skill_social2")      ? experience.get("experience_skill_social2").asDouble() : 0;
            this.tamingExp       = experience.has("experience_skill_taming")       ? experience.get("experience_skill_taming").asDouble() : 0;
            fillSkills();
        }

        unlockedCollection = infos.get("members").get(ownerUUID).has("collection") ? infos.get("members").get(ownerUUID).get("collection").size() : 0;
    }

    /**
     * Finds skills levels from name, total experience earned and the requirements.
     */
    private void fillSkills() {
        findLevel("Farming", farmingExp, HypixelData.getInstance().getFarmingRequirements());
        findLevel("Mining", miningExp, HypixelData.getInstance().getMiningRequirements());
        findLevel("Combat", combatExp, HypixelData.getInstance().getCombatRequirements());
        findLevel("Foraging", foragingExp, HypixelData.getInstance().getForagingRequirements());
        findLevel("Fishing", fishingExp, HypixelData.getInstance().getFishingRequirements());
        findLevel("Enchanting", enchantingExp, HypixelData.getInstance().getEnchantingRequirements());
        findLevel("Alchemy", alchemyExp, HypixelData.getInstance().getAlchemyRequirements());
        findLevel("Carpentry", carpentryExp, HypixelData.getInstance().getCarpentryRequirements());
        findLevel("RuneCrafting", runeCraftingExp, HypixelData.getInstance().getRuneCraftingRequirements());
        findLevel("Social", socialExp, HypixelData.getInstance().getSocialRequirements());
        findLevel("Taming", tamingExp, HypixelData.getInstance().getTamingRequirements());
    }

    /**
     * Finds the level for one skill depending on the requirements and total experience earned.
     * @param skill the skill's name
     * @param experience the total amount of experience earned.
     * @param levels the levels requirements of the skill
     */
    private void findLevel(String skill, double experience, Map<Integer, Double> levels) {
        for (Map.Entry<Integer, Double> entry : levels.entrySet()) {
            if (experience < entry.getValue()) {
                skills.put(skill, entry.getKey() - 1);
                return;
            }
        }
        skills.put(skill, levels.size());
    }

    /**
     * Finds the n top levels from all the player's levels.
     * @param n the number of top levels to return
     * @return The n top levels.
     */
    private List<Map.Entry<String, Integer>> findTopLevels(int n) {
        List<Map.Entry<String, Integer>> sortedSkills = skills.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).collect(Collectors.toList());
        return sortedSkills.subList(0, Math.min(n, sortedSkills.size()));
    }


    /**
     * @return {@link SkyblockInfos#cuteName}
     */
    public String getCuteName() {
        return cuteName;
    }

    /**
     * @return {@link SkyblockInfos#profileId}
     */
    public String getProfileId() {
        return profileId;
    }

    /**
     * @return {@link SkyblockInfos#gameMode}
     */
    public String getGameMode() {
        return gameMode;
    }

    /**
     * Builds a string representation of a summary for the profile.
     * @return a HTML string.
     */
    public String getTooltipText() {
        StringBuilder tooltipBuilder = new StringBuilder();
        tooltipBuilder.append("<html>");

        if (ironMan()) tooltipBuilder.append("<font color=#AAAAAA>Ironman </font>");
        if (bingo()) tooltipBuilder.append("<font color=#AAAAAA>Bingo </font>");
        if (stranded()) tooltipBuilder.append("<font color=#55FF55>Stranded </font>");

        tooltipBuilder.append("<font color=#FFFF55>Profile: </font>");
        tooltipBuilder.append("<font color=#55FF55>").append(cuteName).append("</font><br><br>");

        if (coopMembers.size() > 1) {
            tooltipBuilder.append("<font color=#55FFFF>Co-op with ").append(coopMembers.size() - 1);
            if (coopMembers.size() - 1 > 1) tooltipBuilder.append(" players:</font><br>");
            else tooltipBuilder.append(" player:</font><br>");

            for (int i = 1; i < coopMembers.size(); i++)
                tooltipBuilder.append("- <font color=#55FF55>").append(coopMembers.get(i)).append("</font><br>");
            tooltipBuilder.append("<br>");
        }

        for (Map.Entry<String, Integer> topLevel : findTopLevels(3)) {
            tooltipBuilder.append(topLevel.getKey()).append(":<font color=#FFFF55> Level ").
                    append(IntegerToRoman.getInstance().intToRoman(topLevel.getValue())).append("</font><br>");
        }

        tooltipBuilder.append("<font color=#AAAAAA>Collections unlocked : </font><font color=#FFFF55>")
                .append( (Double.parseDouble(new DecimalFormat("#.##",
                        DecimalFormatSymbols.getInstance(Locale.US)).format(
                                ((double)unlockedCollection / MAXCOLLECTIONS)*100 ))  )).append("%");
        tooltipBuilder.append("<br>");
        tooltipBuilder.append("\t").append(unlockedCollection).append("/").append(MAXCOLLECTIONS).append("</font>");

        tooltipBuilder.append("</html>");
        return tooltipBuilder.toString();
    }

    /**
     * @return Wether profile is ironman or not.
     */
    public boolean ironMan() {
        return gameMode.equals("ironman");
    }

    /**
     * @return Wether profile is default or not.
     */
    public boolean isDefault() {
        return gameMode.equals("default");
    }

    /**
     * @return Wether profile is stranded or not.
     */
    public boolean stranded() {
        return gameMode.equals("island");
    }

    /**
     * @return Wether profile is bingo or not.
     */
    public boolean bingo() {
        return gameMode.equals("bingo");
    }

}
