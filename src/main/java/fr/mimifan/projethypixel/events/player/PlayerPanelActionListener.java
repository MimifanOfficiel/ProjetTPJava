package fr.mimifan.projethypixel.events.player;

import com.fasterxml.jackson.databind.JsonNode;
import fr.mimifan.projethypixel.api.API;
import fr.mimifan.projethypixel.api.Player;
import fr.mimifan.projethypixel.manager.PlayerManager;
import fr.mimifan.projethypixel.panels.PlayerPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerPanelActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().contains("refreshPlayerData")) {
            String uuid = e.getActionCommand().split(" ")[1];

            PlayerPanel playerPanel = PlayerManager.getInstance().getPlayerPanel(uuid);

            JsonNode playerStats = API.getInstance().getPlayerStatsFromUUID(uuid);
            Player player = new Player(playerStats, API.getInstance().isOnline(playerStats.get("uuid").asText()));

            PlayerManager.getInstance().removePlayer(uuid);

            PlayerManager.getInstance().addPlayer(player, playerPanel);
            playerPanel.load(player);
        }
    }
}
