package fr.mimifan.projethypixel.manager;


import fr.mimifan.projethypixel.api.Player;
import fr.mimifan.projethypixel.frames.MainFrame;
import fr.mimifan.projethypixel.panels.PlayerPanel;

import java.util.HashMap;

/**
 * Class PlayerManager
 * Handles the association between players and their uuid/panels
 */
public class PlayerManager {

    /**
     * The current instance of this manager.
     */
    private static final PlayerManager instance = new PlayerManager();

    /**
     * The association between players and their panel.
     */
    private final HashMap<Player, PlayerPanel> playerPanels = new HashMap<>();

    /**
     * The association between players and their UUID
     */
    private final HashMap<String, Player> players = new HashMap<>();

    /**
     * Creates a PlayerPanel and adds a player in their association maps.
     * @param player the player to add
     */
    public void addPlayer(Player player) {
        PlayerPanel panel = new PlayerPanel().load(player);
        playerPanels.put(player, panel);
        players.put(player.getUUID(), player);
        MainFrame.getInstance().getPlayersPane().add(player.getName(), panel);
        MainFrame.getInstance().getFrame().revalidate();
        MainFrame.getInstance().getFrame().repaint();
    }

    /**
     * Adds a player when we already know both player and panel.
     * @param player the player to associate with the panel.
     * @param panel the panel to associate with the player.
     */
    public void addPlayer(Player player, PlayerPanel panel) {
        playerPanels.put(player, panel);
        players.put(player.getUUID(), player);
    }

    /**
     * Returns the PlayerPanel associated with a player via the player's unique identifier.
     * @param uuid the player's unique identifier.
     * @return the PlayerPanel associated with the player.
     */
    public PlayerPanel getPlayerPanel(String uuid) {return playerPanels.get(players.get(uuid));}

    /**
     * Removes association between a player and its panel by instance
     * @param player the player to dissociate.
     */
    public void removePlayer(Player player) {
        playerPanels.remove(player);
        players.remove(player.getUUID());
    }

    /**
     * Removes association between a player and its panel by player's unique identifier.
     * @param uuid the player's unique identifier to dissociate.
     */
    public void removePlayer(String uuid) {
        playerPanels.remove(players.get(uuid));
        players.remove(uuid);
    }

    /**
     * @return {@link PlayerManager#instance}
     */
    public static PlayerManager getInstance() {
        return instance;
    }
}
