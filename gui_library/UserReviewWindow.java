package gui_library;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import database_system.ReviewDataBase;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import program_settings.Parametres;

public class UserReviewWindow extends JFrame {
    private JTable userTable;
    private JButton saveButton;
    private String title;
    private String author;

    public UserReviewWindow(String title, String author, String content) {
        super("User Review");

        this.title = title;
        this.author = author;

        Object[][] userData = {{title, author, content}};
        String[] userColumns = {"Book Title", "Book Author", "Book Reviews"};

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
                String reviewContent = getContentFromTable(); // Get review text from table
                if (!reviewContent.isEmpty()) {
                    ReviewDataBase.addReview(Parametres.getActiveUser(), title, author, reviewContent);
                    JOptionPane.showMessageDialog(UserReviewWindow.this, "Review saved successfully!");
                } else {
                    JOptionPane.showMessageDialog(UserReviewWindow.this, "Please enter a review before saving.");
                }
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

    private String getContentFromTable() {
        return (String) userTable.getValueAt(0, 2);
    }
}
