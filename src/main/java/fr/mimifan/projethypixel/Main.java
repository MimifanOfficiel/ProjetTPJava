package fr.mimifan.projethypixel;

import fr.mimifan.projethypixel.api.data.HypixelData;
import fr.mimifan.projethypixel.frames.MainFrame;

public class Main {

    public static String apiKey = "beedd70b-12d7-43f8-973c-2c4f185a4c7d";
    private static String privateApiKey = "4ec5d1f8-2250-49cb-b59c-1abd46f7f3ea";

    public static void main(String[] args){
        HypixelData.getInstance().init();
        MainFrame.getInstance().load();
        MainFrame.getInstance().getFrame().setVisible(true);
    }

}
