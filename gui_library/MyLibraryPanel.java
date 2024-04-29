package gui_library;

import javax.swing.*;
import java.awt.*;

public class MyLibraryPanel extends JPanel {

    public MyLibraryPanel() {
        initializeComponents();
        setLayout(new BorderLayout());
        JLabel label = new JLabel("My Library Panel");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label, BorderLayout.CENTER);
    }
    
    private void initializeComponents() {
        
    }
}
