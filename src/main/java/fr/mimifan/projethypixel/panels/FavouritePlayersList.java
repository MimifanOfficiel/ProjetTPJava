package fr.mimifan.projethypixel.panels;

import fr.mimifan.projethypixel.frames.MainFrame;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class FavouritePlayersList extends JList<JLabel> {

    private DefaultListModel<String> playerListModel;
    private JScrollPane playerListScrollPane;

    public FavouritePlayersList() {
        playerListScrollPane = new JScrollPane(this);
        setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                return (JLabel) value;
            }
        });

        playerListScrollPane.setPreferredSize(new Dimension(MainFrame.getInstance().getWIDTH()/6, 0));
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Favourite players");
        playerListScrollPane.setBorder(titledBorder);
    }

}
