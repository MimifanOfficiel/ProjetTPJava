package fr.mimifan.projethypixel.panels;

import fr.mimifan.projethypixel.api.API;
import fr.mimifan.projethypixel.api.Player;

import javax.swing.*;
import java.awt.*;

public class GlobalPanel extends JPanel {

    public GlobalPanel(Player player) {
        setBackground(Color.WHITE);
        setLayout(new GridLayout(1, 2));

        JPanel playerPanel = new JPanel();
        playerPanel.setBackground(Color.WHITE);
        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));
        playerPanel.add(new JLabel("Player Informations"));
        playerPanel.add(Box.createVerticalStrut(Math.min(10, Integer.MAX_VALUE)));

        playerPanel.add(new JLabel("First logged : " + player.getFirstJoin()));
        playerPanel.add(new JLabel("<html>Status : <font " + (player.isOnline() ? "color=green>Online" : "color=red>Offline") + " </font> </html>"));

        playerPanel.add(new JLabel(new ImageIcon(API.getInstance().getSkin(player.getName()))));

        add(playerPanel);
        add(new JLabel("KOUKOU"));
    }
}
