package fr.mimifan.projethypixel.panels;


import fr.mimifan.projethypixel.api.Player;
import fr.mimifan.projethypixel.panels.skyblock.SkyblockInfosPanel;
import fr.mimifan.projethypixel.panels.skyblock.SkyblockProfilePanel;

import javax.swing.*;
import java.awt.*;

public class PlayerPanel extends JPanel {

    private final JTabbedPane infosPane = new JTabbedPane();
    private SkyblockInfosPanel skyblockPanel;


    public PlayerPanel() {}

    public PlayerPanel load(Player player) {
        GlobalPanel globalPanel = new GlobalPanel(player);
        BedwarsPanel bedwarsPanel = new BedwarsPanel(player.getBedwarsInfos());
        SkyblockProfilePanel profilePanel = new SkyblockProfilePanel(player.getSkyblockProfiles());

        removeAll();
        infosPane.removeAll();

        setLayout(new BorderLayout());
        infosPane.add("General", globalPanel);
        infosPane.add("Bedwars", bedwarsPanel);
        infosPane.add("Skyblock", profilePanel);

        add(infosPane, BorderLayout.CENTER);
        return this;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    public JTabbedPane getTabbedPane() {
        return infosPane;
    }
}
