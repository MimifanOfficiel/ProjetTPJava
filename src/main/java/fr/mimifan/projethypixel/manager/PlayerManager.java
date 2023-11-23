package fr.mimifan.projethypixel.manager;


import fr.mimifan.projethypixel.api.Player;
import fr.mimifan.projethypixel.frames.MainFrame;
import fr.mimifan.projethypixel.panels.PlayerPanel;

import java.util.HashMap;

public class PlayerManager {

    private static PlayerManager instance = new PlayerManager();

    private HashMap<Player, PlayerPanel> playerPanels = new HashMap<>();
    private HashMap<String, Player> players = new HashMap<>();

    public void addPlayer(Player player) {
        PlayerPanel panel = new PlayerPanel().load(player);
        playerPanels.put(player, panel);
        players.put(player.getUUID(), player);
        MainFrame.getInstance().getPlayersPane().add(player.getName(), panel);
        MainFrame.getInstance().getFrame().revalidate();
        MainFrame.getInstance().getFrame().repaint();
    }

    public void addPlayer(Player player, PlayerPanel panel) {
        playerPanels.put(player, panel);
        players.put(player.getUUID(), player);
    }

    public PlayerPanel getPlayerPanel(Player player) {
        return playerPanels.get(player);
    }
    public PlayerPanel getPlayerPanel(String uuid) {return playerPanels.get(players.get(uuid));}
    public Player getPlayer(String uuid) { return players.get(uuid); }

    public void removePlayer(Player player) {
        playerPanels.remove(player);
        players.remove(player.getUUID());
    }

    public void removePlayer(String uuid) {
        playerPanels.remove(players.get(uuid));
        players.remove(uuid);
    }

    public static PlayerManager getInstance() {
        return instance;
    }
}
