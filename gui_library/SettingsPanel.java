package gui_library;

import javax.swing.*;
import java.awt.*;

public class SettingsPanel extends JPanel {

    public SettingsPanel() {
        initializeComponents();
        setLayout(new BorderLayout());
        setBackground(Color.WHITE); 
        JLabel label = new JLabel("Settings Panel");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label, BorderLayout.CENTER);
    }

    private void initializeComponents() {
    }
}
