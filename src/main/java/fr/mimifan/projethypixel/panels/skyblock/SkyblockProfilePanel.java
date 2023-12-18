package fr.mimifan.projethypixel.panels.skyblock;

import fr.mimifan.projethypixel.api.Ressources;
import fr.mimifan.projethypixel.api.data.skyblock.SkyblockInfos;
import fr.mimifan.projethypixel.customLayouts.VerticalFlowLayout;
import fr.mimifan.projethypixel.events.skyblock.SkyBlockProfilesActionListener;
import fr.mimifan.projethypixel.events.skyblock.SkyBlockProfilesMouseListener;
import fr.mimifan.projethypixel.manager.SkyBlockProfileManager;
import fr.mimifan.projethypixel.utils.LabelUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Class SkyblockProfilePanel
 * Panel containing the list of profiles the player has.
 */
public class SkyblockProfilePanel extends JPanel {

    /**
     * The image corresponding to the profile's gamemode.
     */
    private final BufferedImage bufferedImage = Ressources.getInstance().getTextureImage("sb_profiles_gui.png");

    /**
     * The background image for this panel.
     */
    private final ImageIcon sbGui = new ImageIcon(bufferedImage.getScaledInstance(bufferedImage.getWidth()*4,
            bufferedImage.getHeight()*4, Image.SCALE_SMOOTH));

    /**
     * The list of profiles' data.
     */
    private final List<SkyblockInfos> skyblockInfosList = new ArrayList<>();

    /**
     * Default constructor
     * @param ownerUUID the owner unique identifier of these profiles.
     * @param profilesIdName the map filled with the name of the profile and their associated unique identifier.
     */
    public SkyblockProfilePanel(String ownerUUID, HashMap<String, String> profilesIdName){
        setLayout(null);
        profilesIdName.forEach((id, name) -> {
            SkyblockInfos profile = new SkyblockInfos(ownerUUID, id, name);
            SkyBlockProfileManager.getInstance().addProfile(profile);
            skyblockInfosList.add(profile);
        });

        addComponentListener(new ComponentAdapter() { @Override public void componentResized(ComponentEvent e) { updateButtonLayout(); }});
    }


    /**
     * Updates the layout containing the profile selector,
     * so it's rendered correctly according to the background image.
     */
    private void updateButtonLayout() {
        removeAll();

        for (int i = 0; i < skyblockInfosList.size(); i++) {
            int buttonWidth = sbGui.getIconWidth() / 5;
            int buttonHeight = sbGui.getIconHeight();

            JButton button = createButton(skyblockInfosList.get(i).getCuteName(), skyblockInfosList.get(i).getProfileId(), skyblockInfosList.get(i).getGameMode());
            int x = ((getWidth() - sbGui.getIconWidth()) / 2) + (i * buttonWidth);
            int y = (getHeight() - buttonHeight) / 2;
            button.setBounds(x, y, buttonWidth, buttonHeight);
            add(button);
        }

        add(positionBottomRightPanel());

        revalidate();
        repaint();
    }

    /**
     * Paints the JPanel
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int x = (getWidth() - sbGui.getIconWidth()) / 2;
        int y = (getHeight() - sbGui.getIconHeight()) / 2;

        g.drawImage(sbGui.getImage(), x, y, sbGui.getIconWidth(), sbGui.getIconHeight(), this);
    }

    /**
     * Creates a custom JButton with the specified Icon depending on the profile
     * @param alt the profile name displayed even if icon is not found.
     * @param profileId the profile's identifier
     * @param mode the mode of the profile.
     * @return A JButton fully usable
     */
    private JButton createButton(String alt, String profileId, String mode) {
        JButton button = new JButton(alt);
        BufferedImage bufferedImage = switch (mode) {
            case "ironman" -> Ressources.getInstance().getTextureImage("iron_chestplate.png");
            case "island"  -> Ressources.getInstance().getTextureImage("oak_sapling.png");
            case "bingo"   -> Ressources.getInstance().getTextureImage("golden_apple.png");
            case "default" -> Ressources.getInstance().getTextureImage("grass_block_side.png");
            default -> null;
        };
        if(bufferedImage != null) {
            ImageIcon image = new ImageIcon(bufferedImage.getScaledInstance(bufferedImage.getWidth()*2, bufferedImage.getHeight()*2, Image.SCALE_SMOOTH));
            button.setIcon(image);

            // Puts text under Icon
            button.setVerticalTextPosition(SwingConstants.BOTTOM);
            button.setHorizontalTextPosition(SwingConstants.CENTER);
            button.setMargin(new Insets(0, 0, 0, 0));

            // Hide button background
            button.setOpaque(false);
            button.setContentAreaFilled(false);
            button.setBorderPainted(false);
            button.setLayout(new VerticalFlowLayout());
        }
        button.setForeground(Color.BLACK);
        button.addActionListener(new SkyBlockProfilesActionListener());
        button.addMouseListener(new SkyBlockProfilesMouseListener());
        button.putClientProperty("profileId", profileId);

        return button;
    }


    /**
     * Creates a JPanel located in the bottom right corner of the main panel.
     * @return A JPanel telling user the association of icon and gamemode.
     */
    private JPanel positionBottomRightPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(LabelUtils.getInstance().getLabelWithIcon("grass_block_side.png", "Default Island Type", 16, 1.5));
        panel.add(LabelUtils.getInstance().getLabelWithIcon("oak_sapling.png", "Stranded Island", 16, 1.5));
        panel.add(LabelUtils.getInstance().getLabelWithIcon("iron_chestplate.png", "IronMan Island", 16, 1.5));
        panel.add(LabelUtils.getInstance().getLabelWithIcon("golden_apple.png", "Bingo Island", 16, 1.5));

        int panelWidth = 200;
        int panelHeight = 100;
        int panelX = getWidth() - panelWidth;
        int panelY = getHeight() - panelHeight;

        panel.setBounds(panelX, panelY, panelWidth, panelHeight);
        return panel;
    }
}
