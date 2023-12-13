package fr.mimifan.projethypixel.utils;

import fr.mimifan.projethypixel.api.Ressources;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class LabelUtils {

    private static final LabelUtils instance = new LabelUtils();


    public Box getLabelWithIcon(String iconName, String labelText, String textHexColor) {
        Box box = Box.createHorizontalBox();
        JLabel image = new JLabel();
        image.setIcon(new ImageIcon(Ressources.getInstance().getTextureImage(iconName)));
        box.add(image);
        box.add(new JLabel("<html><font color=" + textHexColor + "> " + labelText + "</font></html>"));
        box.setAlignmentX(Component.LEFT_ALIGNMENT);
        return box;
    }

    public Box getLabelWithIcon(String iconName, String labelText) {
        Box box = Box.createHorizontalBox();
        JLabel image = new JLabel();
        image.setIcon(new ImageIcon(Ressources.getInstance().getTextureImage(iconName)));
        box.add(image);
        box.add(new JLabel(labelText));
        box.setAlignmentX(Component.LEFT_ALIGNMENT);
        return box;
    }

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
