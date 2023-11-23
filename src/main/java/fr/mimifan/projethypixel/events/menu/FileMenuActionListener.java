package fr.mimifan.projethypixel.events.menu;

import com.fasterxml.jackson.databind.JsonNode;
import fr.mimifan.projethypixel.api.API;
import fr.mimifan.projethypixel.api.Player;
import fr.mimifan.projethypixel.manager.PlayerManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FileMenuActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "setplayer" -> {
                JTextField valueField = new JTextField("Name/UUID");
                JCheckBox uuidCheckBox = new JCheckBox("UUID");

                final JComponent[] shape_infos = new JComponent[] { valueField, uuidCheckBox };

                int result = JOptionPane.showConfirmDialog(null, shape_infos, "Set Player Stats", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    try {
                        JsonNode playerStats;
                        if(uuidCheckBox.isSelected()) {
                            playerStats = API.getInstance().getPlayerStatsFromUUID(valueField.getText());
                        } else {
                            playerStats = API.getInstance().getPlayerStatsFromName(valueField.getText());
                        }
                        PlayerManager.getInstance().addPlayer(new Player(playerStats, API.getInstance().isOnline(playerStats.get("uuid").asText())));


                    } catch (Exception ignored) {}

                } else {
                    System.out.println("User canceled / closed the dialog, result = " + result);
                }
            }
        }
    }

}
