package fr.mimifan.projethypixel.api;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Ressources {

    private static final Ressources instance = new Ressources();

    public BufferedImage getHypixelIcon() {
        BufferedImage image;
        File imageFile = new File("src/main/resources/images/hypixel_logo.png");

        try { image = ImageIO.read(imageFile); }
        catch (IOException e) { throw new RuntimeException(e); }

        return image;
    }

    public BufferedImage getRefreshIcon() {
        BufferedImage image;
        File imageFile = new File("src/main/resources/images/refresh.png");

        try { image = ImageIO.read(imageFile); }
        catch (IOException e) { throw new RuntimeException(e); }

        return image;
    }


    public static Ressources getInstance() {
        return instance;
    }
}
