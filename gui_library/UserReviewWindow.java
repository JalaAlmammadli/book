package gui_library;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import lang_change.Lang;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserReviewWindow extends JFrame {
    private JTable userTable;
    private JButton saveButton;

    public UserReviewWindow(String title, String author) {
        super("User Review");

        Object[][] userData = { { title, author, "" } };
        String[] userColumns = { Lang.bookTitle, Lang.bookAuthor, Lang.bookReviews };

        DefaultTableModel userModel = new DefaultTableModel(userData, userColumns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 2;
            }
        };

        userTable = new JTable(userModel);
        userTable.setRowHeight(20);
        JTableHeader userHeader = userTable.getTableHeader();
        userHeader.setBackground(Color.decode("#ADC4CE"));

        JScrollPane userScrollPane = new JScrollPane(userTable);

        JPanel userPanel = new JPanel(new GridLayout(0, 1));
        userPanel.setBorder(BorderFactory.createTitledBorder("Book Details"));
        userPanel.add(userScrollPane);

        saveButton = new JButton("Save");
        saveButton.setBackground(Color.decode("#E5E1DA")); 
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveReview();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);

        setLayout(new BorderLayout());
        add(userPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setPreferredSize(new Dimension(400, 150));
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
    }

    private void saveReview() {
        // Get the user review data from the table and save it
        String title = userTable.getValueAt(0, 0).toString();
        String author = userTable.getValueAt(0, 1).toString();
        String review = userTable.getValueAt(0, 2).toString();

        // Perform saving operations here, e.g., updating the database

        dispose(); 
    }
}
