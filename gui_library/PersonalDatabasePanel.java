package gui_library;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class PersonalDatabasePanel extends JPanel {

    private JTable personalTable;
    private JScrollPane personalTableScrollPane;

    public PersonalDatabasePanel(Object[][] data, String[] columnNames) {
        initializeComponents(data, columnNames);
        setupLayout();
    }

    private void initializeComponents(Object[][] data, String[] columnNames) {
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        personalTable = new JTable(model);
        personalTable.setPreferredScrollableViewportSize(new Dimension(500, 200));
        personalTableScrollPane = new JScrollPane(personalTable);
    }

    private void setupLayout() {
        setLayout(new BorderLayout());
        add(personalTableScrollPane, BorderLayout.CENTER);
    }
}
