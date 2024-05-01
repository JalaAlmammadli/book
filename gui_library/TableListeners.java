package gui_library;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TableListeners {
    // Inner class to handle mouse clicks on the review column
    public static class ReviewColumnMouseListener extends MouseAdapter {
        private final JTable table;
        private final int reviewColumnIndex;

        // Constructor to initialize the table and review column index
        public ReviewColumnMouseListener(JTable table, int reviewColumnIndex) {
            this.table = table;
            this.reviewColumnIndex = reviewColumnIndex;
        }

        // Override mouseClicked method to handle mouse clicks
        @Override
        public void mouseClicked(MouseEvent e) {
            int column = table.columnAtPoint(e.getPoint());
            int row = table.rowAtPoint(e.getPoint());

            if (column == reviewColumnIndex) {
                Object reviewValue = table.getValueAt(row, column);

                if (reviewValue != null && reviewValue.toString().equals("No Review")) {
                    String username = (String) reviewValue;
                    String title = (String) table.getValueAt(row, DatabaseLib.TITLE_COLUMN_INDEX);
                    String author = (String) table.getValueAt(row, DatabaseLib.AUTHOR_COLUMN_INDEX);
                    String rating = (String) table.getValueAt(row, DatabaseLib.RATING_COLUMN_INDEX);
                    new ReviewFrame(title, author, rating, username);
                }
            }
        }
    }

    // Inner class to handle search action
    public static class SearchActionListener implements ActionListener {
        private final JTable table;
        private final JTextField searchField;
        private final String[] column;
        private final Object[][] data;
        private final DefaultTableModel model;

        // Constructor to initialize table, search field, column names, data, and model
        public SearchActionListener(JTable table, JTextField searchField, String[] column, Object[][] data,
                DefaultTableModel model) {
            this.table = table;
            this.searchField = searchField;
            this.column = column;
            this.data = data;
            this.model = model;
        }

        // Override actionPerformed method to handle search action
        @Override
        public void actionPerformed(ActionEvent e) {
            searchTable(searchField.getText().toLowerCase());
        }

        // Method to perform search operation
        private void searchTable(String searchText) {
            if (searchText.trim().length() == 0) {
                table.setModel(model);
                return;
            }

            // Create a new table model to hold filtered data
            DefaultTableModel filteredModel = new DefaultTableModel(column, 0);

            // Iterate over each row of the original data
            for (Object[] rowData : data) {
                for (Object cellData : rowData) {
                    if (cellData != null && cellData.toString().toLowerCase().contains(searchText)) {
                        filteredModel.addRow(rowData);
                        break;
                    }
                }
            }

            // Set the table model to the filtered model
            table.setModel(filteredModel);
        }
    }

    // Inner class to handle mouse events on panels
    public static class PanelMouseListener extends MouseAdapter {
        private final JButton button;
        private final Color originalColor;

        public PanelMouseListener(JButton button) {
            this.button = button;
            this.originalColor = button.getBackground();
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            button.setBackground(Color.decode("0x96B6C5"));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            button.setBackground(originalColor);
        }
    }

}
