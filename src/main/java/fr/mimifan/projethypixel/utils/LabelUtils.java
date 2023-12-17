package fr.mimifan.projethypixel.utils;

import fr.mimifan.projethypixel.api.Ressources;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author Lila
 * Utility class to create some custom labels easily. <br>
 * Functions here can make a label with an icon, a different font size, a different color (in HEX format) or a multiplier to the icon.
 */
public class LabelUtils {

    private static final LabelUtils instance = new LabelUtils();

    /**
     * Creates a custom label with : <br> <ul>
     * <li> An icon. (textures has to be in resources/textures) </li>
     * <li> A text. </li>
     * <li> A hex color.</li> </ul>
     * @param iconName The png name of the icon. (without the extension, supposed .png)
     * @param labelText The label text, will appear after the icon.
     * @param textHexColor The hex formatted color of the text.
     * @return A Box (JComponent) to add to your container.
     */
    public Box getLabelWithIcon(String iconName, String labelText, String textHexColor) {
        Box box = Box.createHorizontalBox();
        JLabel image = new JLabel();
        image.setIcon(new ImageIcon(Ressources.getInstance().getTextureImage(iconName)));
        box.add(image);
        box.add(new JLabel("<html><font color=" + textHexColor + "> " + labelText + "</font></html>"));
        box.setAlignmentX(Component.LEFT_ALIGNMENT);
        return box;
    }

    /**
     * Creates a custom label with : <br> <ul>
     * <li> An icon. (textures has to be in resources/textures) </li>
     * <li> A text. </li>
     * @param iconName The png name of the icon. (without the extension, supposed .png)
     * @param labelText The label text, will appear after the icon.
     * @return A Box (JComponent) to add to your container.
     */
    public Box getLabelWithIcon(String iconName, String labelText) {
        Box box = Box.createHorizontalBox();
        JLabel image = new JLabel();
        image.setIcon(new ImageIcon(Ressources.getInstance().getTextureImage(iconName)));
        box.add(image);
        box.add(new JLabel(labelText));
        box.setAlignmentX(Component.LEFT_ALIGNMENT);
        return box;
    }

    /**
     * Creates a custom label with : <br> <ul>
     * <li> An icon. (textures has to be in resources/textures) </li>
     * <li> A text. </li>
     * <li> A different font size than the default one.</li> </ul>
     * @param iconName The png name of the icon. (without the extension, supposed .png)
     * @param labelText The label text, will appear after the icon.
     * @param fontSize The size of label's text.
     * @return A Box (JComponent) to add to your container.
     */
    public Box getLabelWithIcon(String iconName, String labelText, int fontSize) {
        Box box = Box.createHorizontalBox();

        JLabel image = new JLabel();
        image.setIcon(new ImageIcon(Ressources.getInstance().getTextureImage(iconName)));
        box.add(image);

        JLabel textLabel = new JLabel(labelText);
        Font font = textLabel.getFont();
        textLabel.setFont(new Font(font.getName(), font.getStyle(), fontSize));
        box.add(textLabel);

        box.setAlignmentX(Component.LEFT_ALIGNMENT);

        return box;
    }

    /**
     * Creates a custom label with : <br> <ul>
     * <li> An icon. (textures has to be in resources/textures) </li>
     * <li> A text. </li>
     * <li> A hex color.</li>
     * <li> An different font size than the default one. </li>
     * <li> A multiplier to scale the icon.</li></ul>
     * @param iconName The png name of the icon. (without the extension, supposed .png)
     * @param labelText The label text, will appear after the icon.
     * @param textHexColor The hex formatted color of the text.
     * @param fontSize The size of label's text.
     * @param imageSizeMultiplier The multiplier to scale the icon with.
     * @return A Box (JComponent) to add to your container.
     */
    public Box getLabelWithIcon(String iconName, String labelText, String textHexColor, int fontSize, double imageSizeMultiplier) {
        Box box = Box.createHorizontalBox();
        JLabel image = new JLabel();
        BufferedImage bufferedImage = Ressources.getInstance().getTextureImage(iconName);
        int newWidth = (int) (bufferedImage.getWidth()*imageSizeMultiplier);
        int newHeight = (int) (bufferedImage.getHeight()*imageSizeMultiplier);
        image.setIcon(new ImageIcon(bufferedImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH)));
        box.add(image);
        JLabel textLabel = new JLabel("<html><font color=" + textHexColor + "> " + labelText + "</font></html>");
        Font font = textLabel.getFont();
        textLabel.setFont(new Font(font.getName(), font.getStyle(), fontSize));
        box.add(textLabel);
        box.setAlignmentX(Component.LEFT_ALIGNMENT);
        return box;
    }

    /**
     * Creates a custom label with : <br> <ul>
     * <li> An icon. (textures has to be in resources/textures) </li>
     * <li> A text. </li>
     * <li> An different font size than the default one. </li>
     * <li> A multiplier to scale the icon.</li></ul>
     * @param iconName The png name of the icon. (without the extension, supposed .png)
     * @param labelText The label text, will appear after the icon.
     * @param fontSize The size of label's text.
     * @param imageSizeMultiplier The multiplier to scale the icon with.
     * @return A Box (JComponent) to add to your container.
     */
    public Box getLabelWithIcon(String iconName, String labelText, int fontSize, double imageSizeMultiplier) {
        Box box = Box.createHorizontalBox();
        JLabel image = new JLabel();
        BufferedImage bufferedImage = Ressources.getInstance().getTextureImage(iconName);
        int newWidth = (int) (bufferedImage.getWidth()*imageSizeMultiplier);
        int newHeight = (int) (bufferedImage.getHeight()*imageSizeMultiplier);
        image.setIcon(new ImageIcon(bufferedImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH)));
        box.add(image);
        JLabel textLabel = new JLabel(labelText);
        Font font = textLabel.getFont();
        textLabel.setFont(new Font(font.getName(), font.getStyle(), fontSize));
        box.add(textLabel);
        box.setAlignmentX(Component.LEFT_ALIGNMENT);
        return box;
    }

    /**
     * Creates a custom label with : <br> <ul>
     * <li> An icon. (textures has to be in resources/textures) </li>
     * <li> A text. </li>
     * <li> A multiplier to scale the icon.</li></ul>
     * @param iconName The png name of the icon. (without the extension, supposed .png)
     * @param labelText The label text, will appear after the icon.
     * @param imageSizeMultiplier The multiplier to scale the icon with.
     * @return A Box (JComponent) to add to your container.
     */
    public Box getLabelWithIcon(String iconName, String labelText, double imageSizeMultiplier) {
        Box box = Box.createHorizontalBox();
        JLabel image = new JLabel();
        BufferedImage bufferedImage = Ressources.getInstance().getTextureImage(iconName);
        int newWidth = (int) (bufferedImage.getWidth()*imageSizeMultiplier);
        int newHeight = (int) (bufferedImage.getHeight()*imageSizeMultiplier);
        image.setIcon(new ImageIcon(bufferedImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH)));
        box.add(image);
        box.add(new JLabel(labelText));
        box.setAlignmentX(Component.LEFT_ALIGNMENT);
        return box;
    }



    public static LabelUtils getInstance() {
        return instance;
    }
}
