package fr.mimifan.projethypixel.panels;

import fr.mimifan.projethypixel.api.data.buildbattle.BuildBattle;
import fr.mimifan.projethypixel.utils.LabelUtils;

import javax.swing.*;
import java.text.NumberFormat;

public class BuildBattlePanel extends JPanel {

    public BuildBattlePanel(BuildBattle buildBattleInfos) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(LabelUtils.getInstance().getLabelWithIcon("gold_nugget.png", "Coins : " +
                NumberFormat.getInstance().format(buildBattleInfos.getCoins()), "#FFAA00", 13, 1.25));

        add(new JScrollPane(buildBattleInfos.getStatTable()));
    }

}
