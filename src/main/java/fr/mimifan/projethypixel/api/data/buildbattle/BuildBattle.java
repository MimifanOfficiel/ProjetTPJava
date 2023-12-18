package fr.mimifan.projethypixel.api.data.buildbattle;

import com.fasterxml.jackson.databind.JsonNode;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Class BuildBattle
 * Contains all the information of a players' buildbattle stats.
 */
public class BuildBattle {

    /**
     * Global statistics to retrieve from the node.
     */
    private final Integer coins, gamesPlayed, score, totalVotes, wins;

    /**
     * Default constructor
     * @param buildBattleInfos the JsonNode to retrieve stats from.
     */
    public BuildBattle(JsonNode buildBattleInfos) {
        this.coins = buildBattleInfos.has("coins") ? buildBattleInfos.get("coins").asInt() : 0;
        this.gamesPlayed = buildBattleInfos.has("games_played") ? buildBattleInfos.get("games_played").asInt() : 0;
        this.score = buildBattleInfos.has("score") ? buildBattleInfos.get("score").asInt() : 0;
        this.totalVotes = buildBattleInfos.has("total_votes") ? buildBattleInfos.get("total_votes").asInt() : 0;
        this.wins = buildBattleInfos.has("wins") ? buildBattleInfos.get("wins").asInt() : 0;
    }

    /**
     * @return {@link BuildBattle#coins}
     */
    public Integer getCoins() {
        return coins;
    }

    /**
     * @return A JTable containing all player's buildbattle information.
     */
    public JTable getStatTable() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Games Played");
        model.addColumn("Score");
        model.addColumn("Total Votes");
        model.addColumn("Wins");

        model.addRow(getRow().toArray());
        JTable table = new JTable(model);
        table.setEnabled(false);

        return table;
    }

    /**
     * @return A List of Objects representing the BuildBattle's data.
     */
    public List<Object> getRow() {
        List<Object> data = new ArrayList<>();
        data.add(gamesPlayed);
        data.add(score);
        data.add(totalVotes);
        data.add(wins);

        return data;
    }

}
