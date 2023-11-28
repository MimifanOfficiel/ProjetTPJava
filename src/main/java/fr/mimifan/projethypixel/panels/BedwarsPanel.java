package fr.mimifan.projethypixel.panels;

import fr.mimifan.projethypixel.api.Bedwars;

import javax.swing.*;
import java.awt.*;

public class BedwarsPanel extends JPanel {

    private Bedwars bedwarsInfos;

    public BedwarsPanel(Bedwars bedwarsInfos) {
        this.bedwarsInfos = bedwarsInfos;
        add(new JLabel("Total Deaths : " + bedwarsInfos.getDeaths()));
        add(new JLabel("Total Losses : " + bedwarsInfos.getLosses()));
        add(new JLabel("Total Wins   : " + bedwarsInfos.getWins()));
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
