package fr.mimifan.projethypixel.panels;

import fr.mimifan.projethypixel.api.data.bedwars.Bedwars;

import javax.swing.*;
import java.awt.*;

public class BedwarsPanel extends JPanel {

    private Bedwars bedwarsInfos;

    public BedwarsPanel(Bedwars bedwarsInfos) {
        this.bedwarsInfos = bedwarsInfos;
        setBackground(Color.WHITE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        add(new JLabel("<html><font color=green>Bedwars Level : " + bedwarsInfos.getLevel() + "</font></html>"), gbc);

        gbc.gridy = 1;
        gbc.weighty = 0.9;
        gbc.fill = GridBagConstraints.BOTH;

        add(new JScrollPane(bedwarsInfos.getStatTable()), gbc);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
