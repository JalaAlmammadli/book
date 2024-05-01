package gui_library;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class ReviewFrame extends JFrame {
    public ReviewFrame(String title, String author, String rating, String username) {
        super("User Review");

        // Create data for the book table
        Object[][] bookData = { { title, author, rating } };
        String[] bookColumns = { "Title", "Author", "Rating" };

        // Create and configure the book table
        JTable bookTable = new JTable(bookData, bookColumns);
        bookTable.setEnabled(false);
        bookTable.setRowHeight(20);
        JTableHeader bookHeader = bookTable.getTableHeader();
        bookHeader.setBackground(Color.decode("#ADC4CE"));

        // Create data for the user review table
        Object[][] userData = { { username, "", "" } };
        String[] userColumns = { "Username", "User Rating", "User Review" };

        // Create and configure the user review table
        JTable userTable = new JTable(userData, userColumns);
        userTable.setEnabled(false);
        userTable.setRowHeight(20);
        JTableHeader userHeader = userTable.getTableHeader();
        userHeader.setBackground(Color.decode("#ADC4CE"));

        // Create scroll panes for the tables
        JScrollPane bookScrollPane = new JScrollPane(bookTable);
        JScrollPane userScrollPane = new JScrollPane(userTable);

        // Create panels to hold the tables
        JPanel bookPanel = new JPanel(new GridLayout(0, 1));
        bookPanel.setBorder(BorderFactory.createTitledBorder("Book Details"));
        bookPanel.add(bookScrollPane);

        JPanel userPanel = new JPanel(new GridLayout(0, 1));
        userPanel.setBorder(BorderFactory.createTitledBorder("User Review"));
        userPanel.add(userScrollPane);

        // Configure the layout of the frame and add panels
        setLayout(new GridLayout(2, 1));
        add(bookPanel);
        add(userPanel);

        // Set frame properties
        setPreferredSize(new Dimension(500, 200));
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
    }
}
