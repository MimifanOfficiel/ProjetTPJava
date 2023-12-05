package fr.mimifan.projethypixel.panels;


import fr.mimifan.projethypixel.api.Player;
import fr.mimifan.projethypixel.panels.skyblock.SkyblockPanel;
import fr.mimifan.projethypixel.panels.skyblock.SkyblockProfilePanel;

import javax.swing.*;
import java.awt.*;

public class PlayerPanel extends JPanel {

    private JTabbedPane infosPane = new JTabbedPane();
    private GlobalPanel globalPanel;
    private BedwarsPanel bedwarsPanel;
    private SkyblockProfilePanel profilePanel;
    private SkyblockPanel skyblockPanel;

    private final JButton refreshPlayer = new JButton();

    public PlayerPanel() {}

    public PlayerPanel load(Player player) {
        this.globalPanel = new GlobalPanel(player);
        this.bedwarsPanel = new BedwarsPanel(player.getBedwarsInfos());
        this.profilePanel = new SkyblockProfilePanel();


        removeAll();
        infosPane.removeAll();

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        infosPane.add("General", globalPanel);
        infosPane.add("Bedwars", bedwarsPanel);

        add(infosPane, BorderLayout.CENTER);
        return this;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }


    public BedwarsPanel getBedwarsPanel() {
        return bedwarsPanel;
    }

    public GlobalPanel getGlobalPanel() {
        return globalPanel;
    }

    public JTabbedPane getTabbedPane() {
        return infosPane;
    }
}
