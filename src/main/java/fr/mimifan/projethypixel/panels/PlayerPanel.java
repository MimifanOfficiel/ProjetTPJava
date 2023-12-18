package fr.mimifan.projethypixel.panels;


import fr.mimifan.projethypixel.api.Player;
import fr.mimifan.projethypixel.panels.skyblock.SkyblockProfilePanel;
import javax.swing.*;
import java.awt.*;
import java.util.concurrent.CompletableFuture;

/**
 * Class PlayerPanel
 * Contains all player's panels and components.
 */
public class PlayerPanel extends JPanel {

    /**
     * The tabbed pane containing games panels.
     */
    private final JTabbedPane infosPane = new JTabbedPane();

    /**
     * The panel containing all Skyblock profiles, loaded asynchronously.
     */
    private SkyblockProfilePanel profilePanel = null;

    /**
     * Loads the player's main panel
     * @param player The player to load data from.
     * @return a PlayerPanel instance
     */
    public PlayerPanel load(Player player) {
        removeAll();
        infosPane.removeAll();
        setLayout(new BorderLayout());

        GlobalPanel globalPanel = new GlobalPanel(player);
        infosPane.add("General", globalPanel);

        addGamePanel(player, "Bedwars", BedwarsPanel.class);
        addGamePanel(player, "BuildBattle", BuildBattlePanel.class);
        addGamePanel(player, "MurderMystery", MurderMysteryPanel.class);

        add(infosPane, BorderLayout.CENTER);

        if (player.getSbNode() != null) {
            CompletableFuture<Void> profileLoadingFuture = CompletableFuture.runAsync(() ->
                    profilePanel = new SkyblockProfilePanel(player.getUUID(), player.getSkyblockProfiles()));
            profileLoadingFuture.thenRun(() -> infosPane.add("Skyblock", profilePanel));
        }

        return this;
    }

    /**
     * Adds a game panel to the tabbed pane.
     * @param player the player to retrieve information from.
     * @param gameName the name of the game to display.
     * @param panelClass the class of the panel to display. (e.g. BedwarsPanel.class)
     */
    private void addGamePanel(Player player, String gameName, Class<? extends JPanel> panelClass) {
        JPanel gamePanel = null;
        try {
            Object gameInfo = player.getClass().getMethod("get" + gameName + "Infos").invoke(player);
            if (gameInfo != null) gamePanel = panelClass.getConstructor(gameInfo.getClass()).newInstance(gameInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (gamePanel != null) infosPane.add(gameName, gamePanel);
    }

    /**
     * Paints the JPanel
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    /**
     * @return {@link PlayerPanel#infosPane}
     */
    public JTabbedPane getTabbedPane() {
        return infosPane;
    }
}
