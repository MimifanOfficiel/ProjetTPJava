package fr.mimifan.projethypixel;

import fr.mimifan.projethypixel.api.API;
import fr.mimifan.projethypixel.api.data.HypixelData;
import fr.mimifan.projethypixel.frames.MainFrame;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.concurrent.CompletableFuture;

/**
 * Class Main <br>
 * Main class of the program.
 */
public class Main {

    /**
     * Initializes hypixel information and the main frame of the application
     * @param args run parameters
     */
    public static void main(String[] args){
        HypixelData.getInstance().init();
        MainFrame.getInstance().load();


        // Check if API key was changed last time more than 72 hours ago, if so, update it.
        if (System.currentTimeMillis() - API.getInstance().getLastEdit() > 3 * 24 * 60 * 60 * 1000) API.getInstance().changeAPIKey();
        else MainFrame.getInstance().getFrame().setVisible(true);

        @SuppressWarnings("unused")
        CompletableFuture<Void> skillsLoading = CompletableFuture.runAsync(() -> HypixelData.getInstance().loadSkills());

        ActionListener taskPerformer = evt -> {
            MainFrame.getInstance().getPlayerListModel().removeAllElements();
            MainFrame.getInstance().loadPlayerListFromPreferences();
        };
        Timer timer = new Timer(30000, taskPerformer);

        timer.start();
    }

}
