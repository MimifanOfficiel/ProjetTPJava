package fr.mimifan.projethypixel.frames;

import fr.mimifan.projethypixel.api.API;
import javax.swing.*;
import java.awt.*;

public class SettingKeyFrame extends JFrame {

    private final JTextField valueField;

    public SettingKeyFrame() {
        super("Change API key");

        JLabel displayLabel = new JLabel("Enter the new API key");
        valueField = new JTextField();

        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> handleOkButton());

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> {
            dispose();
        });

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(displayLabel);
        panel.add(new JLabel());
        panel.add(valueField);
        panel.add(new JLabel());
        panel.add(okButton);
        panel.add(cancelButton);

        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void handleOkButton() {
        API.getInstance().saveAPIKey(valueField.getText());
        dispose();
        if(!MainFrame.getInstance().getFrame().isVisible()) MainFrame.getInstance().getFrame().setVisible(true);
    }

}
