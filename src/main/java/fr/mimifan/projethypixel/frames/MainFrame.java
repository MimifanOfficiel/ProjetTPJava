package fr.mimifan.projethypixel.frames;

import fr.mimifan.projethypixel.api.Ressources;
import fr.mimifan.projethypixel.events.MainFrameActionListener;
import fr.mimifan.projethypixel.events.menu.FileMenuActionListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainFrame {

    private static MainFrame instance = new MainFrame();
    private JFrame frame = new JFrame("Statpixel");
    private JTabbedPane playersPane = new JTabbedPane();
    private JMenu refreshMenu = new JMenu("Refresh");

    private final int WIDTH = 700;
    private final int HEIGHT = 500;
    private final Dimension SIZE = new Dimension(WIDTH, HEIGHT);

    public void load(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setJMenuBar(getMenuBar());
        frame.setIconImage(Ressources.getInstance().getHypixelIcon());

        frame.add(playersPane);

        refreshMenu.addMouseListener(new MainFrameActionListener());


        frame.pack();
        frame.setMinimumSize(SIZE);
        frame.setPreferredSize(SIZE);
        frame.setLocationRelativeTo(null);

    }

    private JMenuBar getMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(getFileMenu());
        menuBar.add(getRefreshButton());

        return menuBar;
    }

    private JMenu getFileMenu() {
        JMenu fileMenu = new JMenu("File");

        JMenuItem setPlayer = new JMenuItem("Set Player");
        JMenuItem save = new JMenuItem("Save");
        JMenuItem load = new JMenuItem("Open");
        JMenuItem quit = new JMenuItem("Quit");

        setPlayer.setActionCommand("setplayer");
        setPlayer.addActionListener(new FileMenuActionListener());

        fileMenu.add(setPlayer);
        fileMenu.add(load);
        fileMenu.add(save);
        fileMenu.addSeparator();
        fileMenu.add(quit);

        return fileMenu;
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
