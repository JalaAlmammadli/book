package gui_library;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

public class UserGUI extends DatabaseLib {

    private JPanel personalDatabasePanel;

    @Override
    public void initializeTable(Object[][] headersAndData) {
        column = (String[]) headersAndData[0];
        data = new Object[headersAndData.length][column.length];
        for (int i = 1; i < headersAndData.length; i++) {
            data[i - 1] = headersAndData[i];
        }

        model = new DefaultTableModel(data, column) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };

        jt = new JTable(model);
        jt.getTableHeader().setReorderingAllowed(false);
        js = new JScrollPane(jt);
        tablePanel.add(js, BorderLayout.CENTER);
    }

    @Override
    protected String[] readHeaders(String header) {
        if (header != null) {
            String[] headers = header.split(",", -1);
            String[] finalHeaders = new String[headers.length + 3];
            finalHeaders[0] = "<html><b>Title</b></html>";
            finalHeaders[1] = "<html><b>Author</b></html>";
            finalHeaders[headers.length] = "<html><b>Rating</b></html>";
            finalHeaders[headers.length + 1] = "<html><b>Review</b></html>";
            finalHeaders[headers.length + 2] = "<html><b>Add Book</b></html>";
            return finalHeaders;
        }
        return null;
    }

    @Override
    public void addLeftPanels() {
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        JButton tableButton = new JButton("General Database");
        JButton personalDatabaseButton = new JButton("Personal Database");
        JButton settingsButton = new JButton("Settings");

        int buttonWidth = 250;
        int buttonHeight = 30;
        tableButton.setMaximumSize(new Dimension(buttonWidth, buttonHeight));
        settingsButton.setMaximumSize(new Dimension(buttonWidth, buttonHeight));
        personalDatabaseButton.setMaximumSize(new Dimension(buttonWidth, buttonHeight));

        // Add buttons to the left panel
        leftPanel.add(tableButton);
        leftPanel.add(personalDatabaseButton);
        leftPanel.add(settingsButton);

        // Set background and foreground colors for buttons
        Color buttonColor = new Color(150, 182, 197);
        tableButton.setBackground(buttonColor);
        settingsButton.setBackground(buttonColor);
        personalDatabaseButton.setBackground(buttonColor);

        Color textColor = Color.BLACK;
        tableButton.setForeground(textColor);
        settingsButton.setForeground(textColor);
        personalDatabaseButton.setForeground(textColor);

        // Add action listeners for buttons
        tableButton.addActionListener(e -> {
            showTablePanel();
        });

        settingsButton.addActionListener(e -> {
            showSettingsPanel();
        });

        personalDatabaseButton.addActionListener(e -> {
            showPersonalDatabasePanel();
        });

        // Add border to the left panel
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        leftPanel.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(1, 1, 1, 1)));

        // Add left panel to the JFrame
        jf.add(leftPanel, BorderLayout.WEST);
    }

    private void showTablePanel() {
        if (settingsPanel != null) {
            mainPanel.remove(settingsPanel);
        }
        if (personalDatabasePanel != null) {
            mainPanel.remove(personalDatabasePanel);
        }
        mainPanel.add(tablePanel, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void showSettingsPanel() {
        if (tablePanel != null) {
            mainPanel.remove(tablePanel);
        }
        if (personalDatabasePanel != null) {
            mainPanel.remove(personalDatabasePanel);
        }
        mainPanel.add(settingsPanel, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void showPersonalDatabasePanel() {
        if (personalDatabasePanel == null) {
            initializePersonalDatabasePanel();
        }
        if (tablePanel != null) {
            mainPanel.remove(tablePanel);
        }
        if (settingsPanel != null) {
            mainPanel.remove(settingsPanel);
        }
        mainPanel.add(personalDatabasePanel, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void initializePersonalDatabasePanel() {
        personalDatabasePanel = new JPanel();
        JLabel label = new JLabel("Personal Database Panel");
        personalDatabasePanel.add(label);
    }

    public static void main(String[] args) {
        new UserGUI();
    }

}
