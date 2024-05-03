package gui_library;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.Arrays;

public class AdminGUI extends DatabaseLib {
    private JButton addBookButton;
    private JButton deleteBookButton;
    private JButton removeReviewButton;
    private JButton deleteUserButton;

    // Constructor
    public AdminGUI() {
        super();

        addBookButton = new JButton("Add New Book");
        deleteBookButton = new JButton("Delete Book");
        removeReviewButton = new JButton("Remove Review");
        deleteUserButton = new JButton("Delete User");

        addBookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        deleteBookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        removeReviewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        deleteUserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    // Method to initialize the table
    public void initializeTable(Object[][] headersAndData) {
        column = Arrays.copyOf((String[]) headersAndData[0], ((String[]) headersAndData[0]).length + 1);
        column[column.length - 1] = "<html><b>Operation</b></html>";

        data = new Object[headersAndData.length - 1][column.length];
        for (int i = 1; i < headersAndData.length; i++) {
            Object[] rowData = headersAndData[i];
            for (int j = 0; j < rowData.length; j++) {
                data[i - 1][j] = rowData[j];
            }
            data[i - 1][column.length - 1] = "";
        }

        model = new DefaultTableModel(data, column) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        jt = new JTable(model);
        jt.getTableHeader().setReorderingAllowed(false);
        jt.getColumnModel().getColumn(column.length - 1).setPreferredWidth(100);
        jt.getColumnModel().getColumn(column.length - 1).setCellRenderer(new ButtonRenderer());

        js = new JScrollPane(jt);
        tablePanel.add(js, BorderLayout.CENTER);

        jt.addMouseListener(new MouseAdapter() {

        });
    }

    public class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
            setBackground(new Color(0xE5E1DA));
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText("<html><b>Edit</b></html>");
            setFont(table.getFont());
            setForeground(Color.BLACK);
            setBackground(new Color(0xE5E1DA));
            setBorder(BorderFactory.createLineBorder(Color.GRAY));
            return this;
        }
    }

    public void addNewMovie(String title, String author, String rating, String review) {
        // Implementation for adding a new movie
    }

    public void deleteMovie(int rowIndex) {
        // Implementation for deleting a movie
    }

    public void removeReview(int rowIndex) {
        // Implementation for removing a review
    }

    public void deleteUserAccount(String username) {
        // Implementation for deleting a user account
    }
    public static void main(String[] args) {
        new AdminGUI();
    }
}
