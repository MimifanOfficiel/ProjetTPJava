package fr.mimifan.projethypixel.panels;

import fr.mimifan.projethypixel.api.data.murder.MurderMystery;
import fr.mimifan.projethypixel.utils.LabelUtils;

import javax.swing.*;
import java.text.NumberFormat;

/**
 * Class MurderMysteryPanel
 * Contains all player's MuderMystery stats as a JPanel
 */
public class MurderMysteryPanel extends JPanel {

    /**
     * Default constructor
     * @param murderInfos the infos to take and label.
     */
    public MurderMysteryPanel(MurderMystery murderInfos) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(LabelUtils.getInstance().getLabelWithIcon("gold_nugget.png", "Coins : " +
                NumberFormat.getInstance().format(murderInfos.getCoins()), "#FFAA00", 13, 1.25));

        add(LabelUtils.getInstance().getLabelWithIcon("gold_ingot.png", "Coins Picked Up : " +
                NumberFormat.getInstance().format(murderInfos.getCoinsPickedUp()), "#FFAA00", 13, 1.25));

        add(LabelUtils.getInstance().getLabelWithIcon("iron_sword.png", "Murderer Chance : " +
                NumberFormat.getInstance().format(murderInfos.getMurderChance()) + "%", "#FF5555", 13, 1.25));

        add(LabelUtils.getInstance().getLabelWithIcon("bow_pulling_2.png", "Detective Chance : " +
                NumberFormat.getInstance().format(murderInfos.getDetectiveChance()) + "%", "#55FFFF", 13, 1.25));

        add(new JScrollPane(murderInfos.getStatTable()));
    }

}
