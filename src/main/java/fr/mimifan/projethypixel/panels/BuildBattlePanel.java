package fr.mimifan.projethypixel.panels;

import fr.mimifan.projethypixel.api.data.buildbattle.BuildBattle;
import fr.mimifan.projethypixel.utils.LabelUtils;

import javax.swing.*;
import java.text.NumberFormat;

/**
 * Class BuildBattlePanel
 * Contains all data about player's BuildBattle stats as a JPanel.
 */
public class BuildBattlePanel extends JPanel {

    /**
     * Default constructor
     * @param buildBattleInfos the BuildBattle information
     */
    public BuildBattlePanel(BuildBattle buildBattleInfos) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(LabelUtils.getInstance().getLabelWithIcon("gold_nugget.png", "Coins : " +
                NumberFormat.getInstance().format(buildBattleInfos.getCoins()), "#FFAA00", 13, 1.25));

        add(new JScrollPane(buildBattleInfos.getStatTable()));
    }

}
