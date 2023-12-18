package fr.mimifan.projethypixel.frames;

import com.formdev.flatlaf.FlatDarculaLaf;
import fr.mimifan.projethypixel.api.API;
import fr.mimifan.projethypixel.api.Ressources;
import fr.mimifan.projethypixel.events.MainFrameActionListener;
import fr.mimifan.projethypixel.events.menu.OptionsMenuActionListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

/**
 * @author Lila
 * Class of the application's frame. <br>
 * Singleton as it doesn't need to be instantiated more than once. <br>
 * JFrame can be get outside this class to be used and modified by other classes.
 */
public class MainFrame {

    /**
     * The single instance of this class.
     */
    private static final MainFrame instance = new MainFrame();

    /**
     * The JFrame of this class.
     */
    private JFrame frame;

    /**
     * The tabbed pane containing player's panels.
     */
    private JTabbedPane playersPane;

    /**
     * The JMenu to refresh player's stats.
     */
    private JMenu refreshMenu;

    /**
     * The name of user's favorite players list.
     */
    private static final String PLAYER_LIST_PREF_KEY = "playerList";

    /**
     * The model of the user's favorite players list.
     */
    private DefaultListModel<JLabel> playerListModel;

    /**
     * The preferences of this class.
     */
    private Preferences preferences;

    /**
     * The list of players' names which are user's favorites.
     */
    private final List<String> favoritesPlayers = new ArrayList<>();

    /**
     * The dimensions of {@link MainFrame#frame}
     */
    private final int WIDTH = 800, HEIGHT = 500;

    /**
     * The dimensions of {@link MainFrame#frame}
     */
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
        refreshMenu.addMouseListener(new MainFrameActionListener());

        frame.setJMenuBar(getMenuBar());
        frame.setIconImage(Ressources.getInstance().getHypixelIcon());

        playerListModel = new DefaultListModel<>();
        JList<JLabel> playerList = new JList<>(playerListModel);
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
        menuBar.add(getFavouritePlayersMenu());
        menuBar.add(getRefreshButton());
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
        setPlayer.addActionListener(new OptionsMenuActionListener());

        changeApiKey.setActionCommand("changeKey");
        changeApiKey.addActionListener(new OptionsMenuActionListener());

        quit.setActionCommand("quit");
        quit.addActionListener(new OptionsMenuActionListener());

        optionsMenu.add(setPlayer);
        optionsMenu.add(changeApiKey);
        optionsMenu.addSeparator();
        optionsMenu.add(quit);

        return optionsMenu;
    }

    /**
     * @return A JMenu containing the adding/removing buttons for favorites players.
     */
    private JMenu getFavouritePlayersMenu() {
        JMenu favMenu = new JMenu("Favourite");

        JMenuItem addFavPlayer = new JMenuItem("Add Favourite Player");
        JMenuItem removeFavPlayer = new JMenuItem("Remove Favourite Player");

        addFavPlayer.setActionCommand("addFavPlayer");
        addFavPlayer.addActionListener(new MainFrameActionListener());
        removeFavPlayer.setActionCommand("removeFavPlayer");
        removeFavPlayer.addActionListener(new MainFrameActionListener());

        favMenu.add(addFavPlayer);
        favMenu.add(removeFavPlayer);

        return favMenu;
    }

    /**
     * @return The string list containing all favorites players.
     */
    public List<String> getFavoritesPlayers() {
        return favoritesPlayers;
    }

    /**
     * @return The model of favorite players JList.
     */
    public DefaultListModel<JLabel> getPlayerListModel() {
        return playerListModel;
    }

    /**
     * Method to add a player to the list.
     * @param playerName Name of the player to be added.
     */
    public void addPlayerToList(String playerName) {
        playerListModel.addElement(API.getInstance().getSessionLabel(playerName));
        if(!favoritesPlayers.contains(playerName)) favoritesPlayers.add(playerName);
    }

    /**
     * Method to remove a player from the list.
     * @param playerName Name of the player to be removed.
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
     * Load the player list from preferences.
     */
    public void loadPlayerListFromPreferences() {
        String playerListPref = preferences.get(PLAYER_LIST_PREF_KEY, "");
        String[] players = playerListPref.split(",");
        for (String player : players) {
            if (!player.isEmpty()) {
                addPlayerToList(player);
            }
        }
    }

    /**
     * Save the player list to preferences.
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

    /**
     * @return The button to refresh a player's data.
     */
    public JMenu getRefreshButton(){
        return refreshMenu;
    }

    /**
     * @return The current frame, to be updated elsewhere.
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     * @return The frame's width.
     */
    public int getWIDTH() {
        return WIDTH;
    }

    /**
     * @return The tabbed pane which contains all players' panels.
     */
    public JTabbedPane getPlayersPane() {
        return playersPane;
    }

    /**
     * @return This instance to access other methods.
     * {@link fr.mimifan.projethypixel.frames.MainFrame#frame}
     */
    public static MainFrame getInstance() {
        return instance;
    }
}
