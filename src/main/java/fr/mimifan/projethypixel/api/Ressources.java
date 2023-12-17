package fr.mimifan.projethypixel.api;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * This class handles resources retrievement from resources folder.
 */
public class Ressources {

    private static final Ressources instance = new Ressources();

    /**
     * Gets the hypixel icon in resources folder.
     * @return a BufferedImage containing hypixel_logo.png data.
     */
    public BufferedImage getHypixelIcon() {
        BufferedImage image;
        InputStream stream = Ressources.class.getResourceAsStream("/images/hypixel_logo.png");

        try {
            assert stream != null;
            image = ImageIO.read(stream); }
        catch (IOException e) { throw new RuntimeException(e); }

        return image;
    }

    /**
     * Retrieves an image by its name in textures folder inside resources.
     * @param textureName the texture name to get, without extension as it's supposed to be png.
     * @return a BufferedImage containing the texture data if file has been found.
     */
    public BufferedImage getTextureImage(String textureName) {
        BufferedImage image;
        InputStream stream = Ressources.class.getResourceAsStream("/textures/" + textureName + ".png");

        try {
            assert stream != null;
            image = ImageIO.read(stream); } catch (IOException e) { throw new RuntimeException(e);}

        return image;
    }


    public static Ressources getInstance() {
        return instance;
    }
}
