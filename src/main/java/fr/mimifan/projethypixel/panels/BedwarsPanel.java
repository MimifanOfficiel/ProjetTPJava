package fr.mimifan.projethypixel.panels;

import fr.mimifan.projethypixel.api.data.bedwars.Bedwars;
import fr.mimifan.projethypixel.utils.LabelUtils;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;

public class BedwarsPanel extends JPanel {

    private Bedwars bedwarsInfos;

    public BedwarsPanel(Bedwars bedwarsInfos) {
        this.bedwarsInfos = bedwarsInfos;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(LabelUtils.getInstance().getLabelWithIcon("experience_bottle.png",
                "Bedwars Level : " + bedwarsInfos.getLevel(), "#2A914E", 13, 1.25));

        add(LabelUtils.getInstance().getLabelWithIcon("gold_nugget.png", "Coins : " +
                NumberFormat.getInstance().format(bedwarsInfos.getCoins()), "#FFAA00", 13, 1.25));

        add(LabelUtils.getInstance().getLabelWithIcon("iron_ingot.png", "Iron Ingots collected : " +
                NumberFormat.getInstance().format(bedwarsInfos.getTotalIronIngots()), "#AAAAAA", 13, 1.25));

        add(LabelUtils.getInstance().getLabelWithIcon("gold_ingot.png", "Gold Ingots collected : " +
                NumberFormat.getInstance().format(bedwarsInfos.getTotalGoldIngots()), "#FFD255", 13, 1.25));

        add(LabelUtils.getInstance().getLabelWithIcon("diamond.png", "Diamonds Collected : " +
                NumberFormat.getInstance().format(bedwarsInfos.getTotalDiamonds()), "#00AAAA", 13, 1.25));

        add(LabelUtils.getInstance().getLabelWithIcon("emerald.png", "Emeralds Collected : " +
                NumberFormat.getInstance().format(bedwarsInfos.getTotalEmeralds()), "#00AA00", 13, 1.5));

        add(new JScrollPane(bedwarsInfos.getStatTable()));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
