package fr.mimifan.projethypixel.panels;

import fr.mimifan.projethypixel.api.API;
import fr.mimifan.projethypixel.api.Player;
import fr.mimifan.projethypixel.api.data.HypixelData;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;

public class GlobalPanel extends JPanel {

    public GlobalPanel(Player player) {
        setBackground(Color.WHITE);
        setLayout(new GridLayout(1, 2));

        JPanel playerPanel = new JPanel();
        playerPanel.setBackground(Color.WHITE);
        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));
        playerPanel.add(new JLabel("Player Informations"));
        playerPanel.add(Box.createVerticalStrut(Math.min(10, Integer.MAX_VALUE)));
        playerPanel.add(new JLabel(HypixelData.getInstance().getFormattedName(player)));
        playerPanel.add(new JLabel("<html><font color=green>Level : " + player.getLevel() + "</font></html>"));
        playerPanel.add(new JLabel("<html><font color=#8932b7>Karma</font> : <font color=#c64fbd>" + NumberFormat.getInstance().format(player.getKarma()) + "</font></html>"));
        playerPanel.add(Box.createVerticalStrut(Math.min(10, Integer.MAX_VALUE)));
        playerPanel.add(new JLabel("First logged : " + player.getFirstJoin()));
        playerPanel.add(new JLabel("Last logged : " + player.getLastLogin()));
        playerPanel.add(Box.createVerticalStrut(Math.min(10, Integer.MAX_VALUE)));
        playerPanel.add(new JLabel("<html>Status : <font " + (player.isOnline() ? "color=green>Online" : "color=red>Offline") + " </font> </html>"));
        if(player.isOnline()) {
            playerPanel.add(new JLabel("    Game : " + player.getGameType()));
            playerPanel.add(new JLabel("    Mode : " + player.getMode()));
        }



        add(new JLabel(new ImageIcon(API.getInstance().getSkin(player.getName()))));
        add(playerPanel);
    }

}
