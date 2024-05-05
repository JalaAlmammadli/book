package gui_library;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import lang_change.Lang;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserRatingWindow extends JFrame {
    private JTable bookTable;
    private JButton saveButton;

    public UserRatingWindow(String title, String author) {
        super("User Rating");

        Object[][] bookData = { { title, author, "" } }; 
        String[] bookColumns = { Lang.bookTitle, Lang.bookAuthor, Lang.bookRating };

        DefaultTableModel bookModel = new DefaultTableModel(bookData, bookColumns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 2; 
            }
        };

        bookTable = new JTable(bookModel) {
            @Override
            public void changeSelection(int rowIndex, int columnIndex, boolean toggle, boolean extend) {
                // Override the changeSelection method to prevent changing the selection
            }
        };

        bookTable.setRowHeight(20);
        JTableHeader bookHeader = bookTable.getTableHeader();
        bookHeader.setBackground(Color.decode("#ADC4CE"));
        
        bookTable.setSelectionBackground(new Color(0, 0, 0, 0));

        JScrollPane bookScrollPane = new JScrollPane(bookTable);

        JPanel bookPanel = new JPanel(new GridLayout(0, 1));
        bookPanel.setBorder(BorderFactory.createTitledBorder("Book Details"));
        bookPanel.add(bookScrollPane);

        saveButton = new JButton("Save");
        saveButton.setBackground(Color.decode("#E5E1DA")); // Set background color
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveRating();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);

        setLayout(new BorderLayout());
        add(bookPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setPreferredSize(new Dimension(400, 150));
        pack(); 
        setVisible(true);
        setLocationRelativeTo(null);
    }

    private void saveRating() {
        // Get the rating information from the table
        String title = bookTable.getValueAt(0, 0).toString();
        String author = bookTable.getValueAt(0, 1).toString();
        String rating = bookTable.getValueAt(0, 2).toString();
    
        // Perform saving operations here, e.g., updating the database
    
        dispose(); 
    }
}
