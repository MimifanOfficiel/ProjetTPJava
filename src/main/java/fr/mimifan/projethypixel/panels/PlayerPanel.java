package fr.mimifan.projethypixel.panels;


import fr.mimifan.projethypixel.api.Player;
import fr.mimifan.projethypixel.api.Ressources;
import fr.mimifan.projethypixel.events.player.PlayerPanelActionListener;

import javax.swing.*;
import java.awt.*;

public class PlayerPanel extends JPanel {

    private JTabbedPane infosPane = new JTabbedPane();
    private GlobalPanel globalPanel;
    private BedwarsPanel bedwarsPanel;
    private final JButton refreshPlayer = new JButton();

    public PlayerPanel() {}

    public PlayerPanel load(Player player) {
        this.globalPanel = new GlobalPanel(player);
        this.bedwarsPanel = new BedwarsPanel(player.getBedwarsInfos());

        removeAll();
        infosPane.removeAll();

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        infosPane.add("General", globalPanel);
        infosPane.add("Bedwars", bedwarsPanel);

        refreshPlayer.setIcon(new ImageIcon(Ressources.getInstance().getRefreshIcon().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        refreshPlayer.setBackground(Color.WHITE);
        refreshPlayer.setMinimumSize(new Dimension(30, 30));
        refreshPlayer.setMaximumSize(new Dimension(40, 40));
        refreshPlayer.addActionListener(new PlayerPanelActionListener());
        refreshPlayer.setActionCommand("refreshPlayerData " + player.getUUID());

        add(refreshPlayer, BorderLayout.NORTH);
        add(infosPane, BorderLayout.CENTER);
        return this;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }


    public BedwarsPanel getBedwarsPanel() {
        return bedwarsPanel;
    }

    public GlobalPanel getGlobalPanel() {
        return globalPanel;
    }

}
