package fr.mimifan.projethypixel.events.menu;

import com.fasterxml.jackson.databind.JsonNode;
import fr.mimifan.projethypixel.api.API;
import fr.mimifan.projethypixel.api.Player;
import fr.mimifan.projethypixel.frames.MainFrame;
import fr.mimifan.projethypixel.manager.PlayerManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FileMenuActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "setplayer" -> {
                JLabel displayLabel = new JLabel("Name / UUID");
                JTextField valueField = new JTextField();
                JCheckBox uuidCheckBox = new JCheckBox("UUID");

                final JComponent[] dialogComponents = new JComponent[] { displayLabel, valueField, uuidCheckBox };
                int result = JOptionPane.showConfirmDialog(null, dialogComponents, "Set Player Stats", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    try {

                        JsonNode playerStats;
                        if(uuidCheckBox.isSelected()) playerStats = API.getInstance().getPlayerStatsFromUUID(valueField.getText());
                        else playerStats = API.getInstance().getPlayerStatsFromName(valueField.getText());

                        PlayerManager.getInstance().addPlayer(new Player(playerStats.get("player"), API.getInstance().getSession(playerStats.get("player").get("uuid").asText())));

                    } catch (Exception ignored) {}

                }
            }

            case "changeKey" -> API.getInstance().changeAPIKey();

            case "quit" -> {
                int response = JOptionPane.showConfirmDialog(MainFrame.getInstance().getFrame(), "Do you want to Quit? ",
                        "Confirm",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                if (response == JOptionPane.YES_OPTION) System.exit(0);
            }

        }
    }

}
