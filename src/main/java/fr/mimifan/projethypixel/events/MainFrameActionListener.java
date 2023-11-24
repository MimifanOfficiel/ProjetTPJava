package fr.mimifan.projethypixel.events;

import com.fasterxml.jackson.databind.JsonNode;
import fr.mimifan.projethypixel.api.API;
import fr.mimifan.projethypixel.api.Player;
import fr.mimifan.projethypixel.frames.MainFrame;
import fr.mimifan.projethypixel.manager.PlayerManager;
import fr.mimifan.projethypixel.panels.PlayerPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrameActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("refreshPlayerData")){
            System.out.println("hey");
            int index = MainFrame.getInstance().getPlayersPane().getSelectedIndex();
            String playerName = MainFrame.getInstance().getPlayersPane().getTitleAt(index);
            String uuid = API.getInstance().getUUID(playerName);

            PlayerPanel playerPanel = PlayerManager.getInstance().getPlayerPanel(uuid);

            JsonNode playerStats = API.getInstance().getPlayerStatsFromUUID(uuid);
            Player player = new Player(playerStats.get("player"), API.getInstance().getSession(uuid));

            PlayerManager.getInstance().removePlayer(uuid);

            PlayerManager.getInstance().addPlayer(player, playerPanel);
            playerPanel.load(player);
        }
    }

}
