package fr.mimifan.projethypixel.frames;

import com.formdev.flatlaf.FlatDarculaLaf;
import fr.mimifan.projethypixel.api.API;
import fr.mimifan.projethypixel.api.Ressources;
import fr.mimifan.projethypixel.events.MainFrameActionListener;
import fr.mimifan.projethypixel.events.menu.FileMenuActionListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.prefs.Preferences;

/**
 * @author Lila
 * Class of the application's frame. <br>
 * Singleton as it doesn't need to be instantiated more than once. <br>
 * JFrame can be get outside this class to be used and modified by other classes.
 */
public class MainFrame {

    private static final MainFrame instance = new MainFrame();
    private JFrame frame;
    private JTabbedPane playersPane;
    private JMenu refreshMenu, addFavPlayer, removeFavPlayer;

    private static final String PLAYER_LIST_PREF_KEY = "playerList";

    private DefaultListModel<JLabel> playerListModel;

    private Preferences preferences;
    private final List<String> favoritesPlayers = new ArrayList<>();
    private JList<JLabel> playerList;

    private final int WIDTH = 800, HEIGHT = 500;
    private final Dimension SIZE = new Dimension(WIDTH, HEIGHT);

    /**
     * Initialize the main frame to it's initial state
     */
    public void load(){
        FlatDarculaLaf.setup();
        preferences = Preferences.userNodeForPackage(MainFrame.class);

        frame = new JFrame("Statpixel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        refreshMenu = new JMenu("Refresh");

        addFavPlayer = new JMenu("Add Favourite Player");

        removeFavPlayer = new JMenu("Remove Favourite Player");
        refreshMenu.addMouseListener(new MainFrameActionListener());

        addFavPlayer.addMouseListener(new MainFrameActionListener());
        removeFavPlayer.addMouseListener(new MainFrameActionListener());


        frame.setJMenuBar(getMenuBar());
        frame.setIconImage(Ressources.getInstance().getHypixelIcon());

        playerListModel = new DefaultListModel<>();
        playerList = new JList<>(playerListModel);
        playerList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                return (JLabel) value;
            }
        });
        JScrollPane playerListScrollPane = new JScrollPane(playerList);
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Player List");
        playerListScrollPane.setBorder(titledBorder);
        playerListScrollPane.setPreferredSize(new Dimension(WIDTH/6, 0));

        loadPlayerListFromPreferences();


        frame.add(playerListScrollPane, BorderLayout.WEST);

        playersPane = new JTabbedPane();
        frame.add(playersPane);

        frame.pack();
        frame.setMinimumSize(SIZE);
        frame.setPreferredSize(SIZE);
        frame.setLocationRelativeTo(null);

    }

    /**
     * Creates the JMenuBar that will be shown on top of the application.
     * @return JMenuBar to put on the frame.
     */
    private JMenuBar getMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(getOptionsMenu());
        menuBar.add(getRefreshButton());
        menuBar.add(getAddFavPlayer());
        menuBar.add(getRemoveFavPlayer());
        return menuBar;
    }

    /**
     * Creates the "Options" for JMenuBar. <br>
     * This menu contains 3 options:
     * <ul> <li> Set player : to retrieve a player's information </li>
     * <li> Change API key : To change the API key at anytime. </li>
     * <li> Quit : to simply quit the application from the menu. </li>
     * </uL>
     * @return JMenu to put in the JMenuBar.
     */
    private JMenu getOptionsMenu() {
        JMenu optionsMenu = new JMenu("Options");

        JMenuItem setPlayer = new JMenuItem("Set Player");
        JMenuItem changeApiKey = new JMenuItem("Change API Key");
        JMenuItem quit = new JMenuItem("Quit");

        setPlayer.setActionCommand("setplayer");
        setPlayer.addActionListener(new FileMenuActionListener());

        changeApiKey.setActionCommand("changeKey");
        changeApiKey.addActionListener(new FileMenuActionListener());

        quit.setActionCommand("quit");
        quit.addActionListener(new FileMenuActionListener());

        optionsMenu.add(setPlayer);
        optionsMenu.add(changeApiKey);
        optionsMenu.addSeparator();
        optionsMenu.add(quit);

        return optionsMenu;
    }

    public JMenu getAddFavPlayer() {
        return addFavPlayer;
    }

    public JMenu getRemoveFavPlayer() {
        return removeFavPlayer;
    }

    public JList<JLabel> getPlayerList() {
        return playerList;
    }

    public DefaultListModel<JLabel> getPlayerListModel() {
        return playerListModel;
    }

    /**
     * Method to add a player to the list
     * @param playerName Name of the player to be added
     */
    public void addPlayerToList(String playerName) {
        playerListModel.addElement(API.getInstance().getSessionLabel(playerName));
        if(!favoritesPlayers.contains(playerName)) favoritesPlayers.add(playerName);
    }

    /**
     * Method to remove a player from the list
     * @param playerName Name of the player to be removed
     */
    public void removePlayerFromList(String playerName) {
        for (int i = 0; i < playerListModel.size(); i++) {
            if (playerListModel.getElementAt(i).getText().contains(playerName)) {
                playerListModel.removeElementAt(i);
                favoritesPlayers.remove(playerName);
                break;
            }
        }
    }

    /**
     * Load the player list from preferences
     */
    public void loadPlayerListFromPreferences() {
        String playerListPref = preferences.get(PLAYER_LIST_PREF_KEY, "");
        String[] players = playerListPref.split(",");
        System.out.println(Arrays.toString(players));
        for (String player : players) {
            if (!player.isEmpty()) {
                addPlayerToList(player);
            }
        }
    }

    /**
     * Save the player list to preferences
     */
    public void savePlayerListToPreferences() {
        StringBuilder playerListPref = new StringBuilder();
        for (int i = 0; i < favoritesPlayers.size(); i++) {
            playerListPref.append(favoritesPlayers.get(i));
            if (i < playerListModel.size() - 1) {
                playerListPref.append(",");
            }
        }
        preferences.put(PLAYER_LIST_PREF_KEY, playerListPref.toString());
    }

    public JMenu getRefreshButton(){
        return refreshMenu;
    }

    public JFrame getFrame() {
        return frame;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public JTabbedPane getPlayersPane() {
        return playersPane;
    }

    public static MainFrame getInstance() {
        return instance;
    }
}
