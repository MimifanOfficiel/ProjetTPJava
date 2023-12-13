package fr.mimifan.projethypixel.panels;


import fr.mimifan.projethypixel.api.Player;
import fr.mimifan.projethypixel.panels.skyblock.SkyblockInfosPanel;
import fr.mimifan.projethypixel.panels.skyblock.SkyblockProfilePanel;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class PlayerPanel extends JPanel {

    private final JTabbedPane infosPane = new JTabbedPane();
    private SkyblockProfilePanel profilePanel = null;

    public PlayerPanel() {}

    public PlayerPanel load(Player player) {
        GlobalPanel globalPanel = new GlobalPanel(player);
        BedwarsPanel bedwarsPanel = new BedwarsPanel(player.getBedwarsInfos());

        removeAll();
        infosPane.removeAll();

        setLayout(new BorderLayout());
        add(infosPane, BorderLayout.CENTER);

        infosPane.add("General", globalPanel);
        infosPane.add("Bedwars", bedwarsPanel);

        if (player.getSbNode() != null) {
            CompletableFuture<Void> profileLoadingFuture = CompletableFuture.runAsync(() -> {
                profilePanel = new SkyblockProfilePanel(player.getSkyblockProfiles());
            });

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
