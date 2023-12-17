package fr.mimifan.projethypixel.panels;


import fr.mimifan.projethypixel.api.Player;
import fr.mimifan.projethypixel.panels.skyblock.SkyblockProfilePanel;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.CompletableFuture;

public class PlayerPanel extends JPanel {

    private final JTabbedPane infosPane = new JTabbedPane();
    private SkyblockProfilePanel profilePanel = null;

    public PlayerPanel() {}

    public PlayerPanel load(Player player) {
        removeAll();
        infosPane.removeAll();
        setLayout(new BorderLayout());

        GlobalPanel globalPanel = new GlobalPanel(player);
        infosPane.add("General", globalPanel);

        BedwarsPanel bedwarsPanel;
        if(player.getBedwarsInfos() != null) {
            bedwarsPanel = new BedwarsPanel(player.getBedwarsInfos());
            infosPane.add("Bedwars", bedwarsPanel);
        }

        add(infosPane, BorderLayout.CENTER);

        if (player.getSbNode() != null) {
            CompletableFuture<Void> profileLoadingFuture = CompletableFuture.runAsync(() ->
                    profilePanel = new SkyblockProfilePanel(player.getUUID(), player.getSkyblockProfiles()));
            profileLoadingFuture.thenRun(() -> infosPane.add("Skyblock", profilePanel));
        }

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
