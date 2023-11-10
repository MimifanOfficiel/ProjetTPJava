package fr.mimifan.projethypixel.frames;

import fr.mimifan.projethypixel.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageConsumer;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.Objects;

public class MainFrame {

    private static MainFrame instance = new MainFrame();
    private JFrame frame = new JFrame("Statpixel");

    private final int WIDTH = 700;
    private final int HEIGHT = 500;
    private final Dimension SIZE = new Dimension(WIDTH, HEIGHT);

    public void load(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setJMenuBar(getMenuBar());
        frame.setIconImage(getIcon());

        frame.pack();
        frame.setSize(SIZE);
        frame.setLocationRelativeTo(null);


    }

    private JMenuBar getMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(getFileMenu());

        return menuBar;
    }

    private JMenu getFileMenu() {
        JMenu fileMenu = new JMenu("File");

        JMenuItem setPlayer = new JMenuItem("Set Player");
        JMenuItem save = new JMenuItem("Save");
        JMenuItem load = new JMenuItem("Open");
        JMenuItem quit = new JMenuItem("Quit");

        fileMenu.add(setPlayer);
        fileMenu.add(load);
        fileMenu.add(save);
        fileMenu.addSeparator();
        fileMenu.add(quit);

        return fileMenu;
    }

    public BufferedImage getIcon() {
        BufferedImage image;
        File imageFile = new File("src/main/resources/images/hypixel_logo.png");

        try { image = ImageIO.read(imageFile); }
        catch (IOException e) { throw new RuntimeException(e); }

        return image;
    }

    public JFrame getFrame() {
        return frame;
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
