package gui_library;

import gui_library.admin_crud.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import lang_change.Lang;

public class AdminGUI extends DatabaseLib {
    private JButton addBookButton;
    private JButton deleteBookButton;
    private JButton removeReviewButton;
    private JButton deleteUserButton;

    // Constructor
    public AdminGUI() {
        super();

        addBookButton = new JButton(Lang.addNewBook);
        deleteBookButton = new JButton(Lang.deleteBook);
        removeReviewButton = new JButton(Lang.removeReview);
        deleteUserButton = new JButton(Lang.deleteUser);

        addBookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                new AddBook();
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
        column[column.length - 1] = "<html><b>" + Lang.operation + "</b></html>";

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

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addBookButton);
        buttonPanel.add(deleteBookButton);
        buttonPanel.add(removeReviewButton);
        buttonPanel.add(deleteUserButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
    }

    public class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
            setBackground(new Color(0xE5E1DA));
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText("<html><b>" + Lang.editBook + "</b></html>");
            setFont(table.getFont());
            setForeground(Color.BLACK);
            setBackground(new Color(0xE5E1DA));
            setBorder(BorderFactory.createLineBorder(Color.GRAY));
            return this;
        }
    }


    public static void main(String[] args) {
        new AdminGUI();
    }
}