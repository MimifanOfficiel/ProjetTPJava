package fr.mimifan.projethypixel.frames;

import com.formdev.flatlaf.FlatDarculaLaf;
import fr.mimifan.projethypixel.api.Ressources;
import fr.mimifan.projethypixel.events.MainFrameActionListener;
import fr.mimifan.projethypixel.events.menu.FileMenuActionListener;

import javax.swing.*;
import java.awt.*;

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
    private JMenu refreshMenu;

    private final int WIDTH = 800, HEIGHT = 500;
    private final Dimension SIZE = new Dimension(WIDTH, HEIGHT);

    /**
     * Initialize the main frame to it's initial state
     */
    public void load(){
        FlatDarculaLaf.setup();
        frame = new JFrame("Statpixel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        refreshMenu = new JMenu("Refresh");
        refreshMenu.addMouseListener(new MainFrameActionListener());

        frame.setJMenuBar(getMenuBar());
        frame.setIconImage(Ressources.getInstance().getHypixelIcon());

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

    public JMenu getRefreshButton(){
        return refreshMenu;
    }

    public JFrame getFrame() {
        return frame;
    }

    public JTabbedPane getPlayersPane() {
        return playersPane;
    }

    public static MainFrame getInstance() {
        return instance;
    }
}
