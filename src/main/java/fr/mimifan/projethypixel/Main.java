package fr.mimifan.projethypixel;

import fr.mimifan.projethypixel.api.API;
import fr.mimifan.projethypixel.api.data.HypixelData;
import fr.mimifan.projethypixel.frames.MainFrame;

public class Main {


    public static void main(String[] args){
        HypixelData.getInstance().init();
        MainFrame.getInstance().load();

        // Check if API key was changed last time more than 72 hours ago, if so, update it.
        if (System.currentTimeMillis() - API.getInstance().getLastEdit() > 3 * 24 * 60 * 60 * 1000) API.getInstance().changeAPIKey();
    }

}
