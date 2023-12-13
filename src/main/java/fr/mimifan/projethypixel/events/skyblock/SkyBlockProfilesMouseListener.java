package fr.mimifan.projethypixel.events.skyblock;

import fr.mimifan.projethypixel.api.data.skyblock.SkyblockInfos;
import fr.mimifan.projethypixel.manager.SkyBlockProfileManager;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SkyBlockProfilesMouseListener implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource() instanceof JButton button) {
            String profileId = (String) button.getClientProperty("profileId");
            SkyblockInfos profile = SkyBlockProfileManager.getInstance().getProfile(profileId);
            button.setToolTipText(profile.getTooltipText());
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource() instanceof JButton button) {
            button.setToolTipText(null);
        }
    }
}
