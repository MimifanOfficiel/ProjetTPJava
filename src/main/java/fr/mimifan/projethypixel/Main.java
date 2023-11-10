package fr.mimifan.projethypixel;

import fr.mimifan.projethypixel.frames.MainFrame;

public class Main {

    public static String apiKey = "beedd70b-12d7-43f8-973c-2c4f185a4c7d";

    public static void main(String[] args){
        MainFrame.getInstance().load();
        MainFrame.getInstance().getFrame().setVisible(true);
    }

}
