package fr.mimifan.projethypixel.frames;

import com.formdev.flatlaf.FlatDarculaLaf;
import fr.mimifan.projethypixel.api.Ressources;
import fr.mimifan.projethypixel.events.MainFrameActionListener;
import fr.mimifan.projethypixel.events.menu.FileMenuActionListener;

import javax.swing.*;
import java.awt.*;

public class MainFrame {

    private static MainFrame instance = new MainFrame();
    private JFrame frame;
    private JTabbedPane playersPane;
    private JMenu refreshMenu;

    private final int WIDTH = 800;
    private final int HEIGHT = 500;
    private final Dimension SIZE = new Dimension(WIDTH, HEIGHT);

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

    private JMenuBar getMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(getOptionsMenu());
        menuBar.add(getRefreshButton());

        return menuBar;
    }

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

    public int getHEIGHT() {
        return HEIGHT;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public Dimension getSIZE() {
        return SIZE;
    }

    public static MainFrame getInstance() {
        return instance;
    }
}
