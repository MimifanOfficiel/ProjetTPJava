package fr.mimifan.projethypixel.panels;

import fr.mimifan.projethypixel.api.Ressources;
import fr.mimifan.projethypixel.api.data.bedwars.Bedwars;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;

public class BedwarsPanel extends JPanel {

    private Bedwars bedwarsInfos;

    public BedwarsPanel(Bedwars bedwarsInfos) {
        this.bedwarsInfos = bedwarsInfos;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        addLabelWithIcon("experience_bottle", "Bedwars Level : " + bedwarsInfos.getLevel(), "#2A914E");
        addLabelWithIcon("gold_nugget", "Coins : " +
                NumberFormat.getInstance().format(bedwarsInfos.getCoins()), "#FFAA00");

        addLabelWithIcon("iron_ingot", "Iron Ingots collected : " +
                NumberFormat.getInstance().format(bedwarsInfos.getTotalIronIngots()), "#AAAAAA");

        addLabelWithIcon("gold_ingot", "Gold Ingots collected : " +
                NumberFormat.getInstance().format(bedwarsInfos.getTotalGoldIngots()), "#FFD255");

        addLabelWithIcon("diamond", "Diamonds Collected : " +
                NumberFormat.getInstance().format(bedwarsInfos.getTotalDiamonds()), "#00AAAA");

        addLabelWithIcon("emerald", "Emeralds Collected : " +
                NumberFormat.getInstance().format(bedwarsInfos.getTotalEmeralds()), "#00AA00");

        add(new JScrollPane(bedwarsInfos.getStatTable()));
    }

    private void addLabelWithIcon(String iconName, String labelText, String textHexColor) {
        Box box = Box.createHorizontalBox();
        JLabel image = new JLabel();
        image.setIcon(new ImageIcon(Ressources.getInstance().getTextureImage(iconName)));
        box.add(image);
        box.add(new JLabel("<html><font color=" + textHexColor + "> " + labelText + "</font></html>"));
        box.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(box);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
