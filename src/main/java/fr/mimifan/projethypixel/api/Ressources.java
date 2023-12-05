package fr.mimifan.projethypixel.api;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;

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

    public BufferedImage getTextureImage(String textureName) {
        BufferedImage image;
        File imageFile = new File("src/main/resources/textures/" + textureName + ".png");

        try { image = ImageIO.read(imageFile); } catch (IOException e) { throw new RuntimeException(e);}

        return image;
    }


    public static Ressources getInstance() {
        return instance;
    }
}
