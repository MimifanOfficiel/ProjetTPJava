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

public class SkyblockProfilePanel extends JPanel {

    private final BufferedImage bufferedImage = Ressources.getInstance().getTextureImage("sb_profiles_gui.png");
    private final ImageIcon sbGui = new ImageIcon(bufferedImage.getScaledInstance(bufferedImage.getWidth()*4,
            bufferedImage.getHeight()*4, Image.SCALE_SMOOTH));

    private final List<SkyblockInfos> skyblockInfosList = new ArrayList<>();

    public SkyblockProfilePanel(String ownerUUID, HashMap<String, String> profilesIdName){
        setLayout(null);
        profilesIdName.forEach((id, name) -> {
            SkyblockInfos profile = new SkyblockInfos(ownerUUID, id, name);
            SkyBlockProfileManager.getInstance().addProfile(profile);
            skyblockInfosList.add(profile);
        });

        addComponentListener(new ComponentAdapter() { @Override public void componentResized(ComponentEvent e) { updateButtonLayout(); }});
    }


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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int x = (getWidth() - sbGui.getIconWidth()) / 2;
        int y = (getHeight() - sbGui.getIconHeight()) / 2;

        g.drawImage(sbGui.getImage(), x, y, sbGui.getIconWidth(), sbGui.getIconHeight(), this);
    }

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
