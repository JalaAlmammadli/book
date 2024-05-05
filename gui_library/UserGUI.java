package gui_library;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

import gui_log_reg.LoginFrame;

public class UserGUI extends DatabaseLib {

    private JPanel personalDatabasePanel;
    private DefaultTableModel personalDatabaseTableModel;
    private JTable personalDatabaseTable;
    private JTextField personalDatabaseSearchField;
    private Set<Object> addedBooks = new HashSet<>();

    public static void main(String[] args) {
        new UserGUI();
    }

    @Override
    public void initializeTable(Object[][] headersAndData) {
        column = Arrays.copyOf((String[]) headersAndData[0], ((String[]) headersAndData[0]).length + 1);
        column[column.length - 1] = "<html><b>Add Book</b></html>";
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

        int addBookColumnWidth = 100;
        jt.getColumnModel().getColumn(column.length - 1).setPreferredWidth(addBookColumnWidth);

        jt.getColumnModel().getColumn(column.length - 1).setCellRenderer(new ButtonRenderer());
        js = new JScrollPane(jt);
        tablePanel.add(js, BorderLayout.CENTER);

        jt.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int column = jt.getColumnModel().getColumnIndexAtX(e.getX());
                int row = e.getY() / jt.getRowHeight();

                if (column == jt.getColumnCount() - 1 && row < jt.getRowCount()) {
                    addToPersonalDatabase(row);
                }
            }
        });

    }

    @Override
    public void addLeftPanels() {
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        JButton tableButton = new JButton("General Database");
        JButton personalDatabaseButton = new JButton("Personal Database");

        int buttonWidth = 250;
        int buttonHeight = 30;
        tableButton.setMaximumSize(new Dimension(buttonWidth, buttonHeight));
        personalDatabaseButton.setMaximumSize(new Dimension(buttonWidth, buttonHeight));

        leftPanel.add(tableButton);
        leftPanel.add(personalDatabaseButton);

        Color buttonColor = new Color(150, 182, 197);
        tableButton.setBackground(buttonColor);
        personalDatabaseButton.setBackground(buttonColor);

        Color textColor = Color.BLACK;
        tableButton.setForeground(textColor);
        personalDatabaseButton.setForeground(textColor);

        tableButton.addActionListener(e -> {
            showTablePanel();
        });

        personalDatabaseButton.addActionListener(e -> {
            showPersonalDatabasePanel();
        });

        JButton logoutButton = new JButton("Logout");
        logoutButton.setMaximumSize(new Dimension(buttonWidth, buttonHeight));
        logoutButton.setBackground(buttonColor);
        logoutButton.setForeground(Color.BLACK);

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jf.dispose();
                LoginFrame.Login();
            }
        });

        leftPanel.add(logoutButton);
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        leftPanel.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(1, 1, 1, 1)));

        jf.add(leftPanel, BorderLayout.WEST);
    }

    private void addToPersonalDatabase(int row) {
        Object[] rowData = new Object[column.length];
        for (int i = 0; i < column.length; i++) {
            rowData[i] = jt.getValueAt(row, i);
        }

        Object bookIdentifier = rowData[0];
        if (addedBooks.contains(bookIdentifier)) {
            System.out.println("Book already added.");
            return;
        }

        addedBooks.add(bookIdentifier);

        rowData[4] = "Not started";

        if (rowData.length > 7) {
            Object startDate = rowData[6];
            Object endDate = rowData[7];
            if (startDate != null && endDate != null) {
                if (((String) startDate).compareTo((String) endDate) > 0) {
                    Object temp = startDate;
                    rowData[6] = endDate;
                    rowData[7] = temp;
                }
            }
        }

        if (rowData.length > 9) {
            if (rowData[8] == null) {
                rowData[8] = "Add rating";
            }
            if (rowData[9] == null) {
                rowData[9] = "Add review";
            }
        }

        jt.setValueAt("Added", row, column.length - 1);

        addRowToPersonalDatabasePanel(rowData);

        System.out.println("Added book: " + rowData[0]);
    }

    private void addRowToPersonalDatabasePanel(Object[] rowData) {
        if (personalDatabasePanel == null) {
            initializePersonalDatabasePanel();
        }

        if (personalDatabaseTableModel == null) {
            personalDatabaseTableModel = new DefaultTableModel();
            personalDatabaseTable = new JTable(personalDatabaseTableModel);
            personalDatabaseTableModel.addColumn("Title");
            personalDatabaseTableModel.addColumn("Author");
            personalDatabaseTableModel.addColumn("Rating");
            personalDatabaseTableModel.addColumn("Review");

            personalDatabaseTable.getTableHeader().setForeground(Color.BLACK);
            personalDatabaseTable.getTableHeader().setBackground(Color.decode("#ADC4CE"));
            personalDatabaseTable.getTableHeader()
                    .setPreferredSize(new Dimension(personalDatabaseTable.getTableHeader().getWidth(), 30));
            personalDatabaseTable.setBackground(Color.WHITE);
            personalDatabaseTable.setForeground(Color.BLACK);
            personalDatabaseTable.setSelectionBackground(Color.decode("#F1F0E8"));
            personalDatabaseTable.setSelectionForeground(Color.BLACK);

            TableColumnModel columnModel = personalDatabaseTable.getTableHeader().getColumnModel();
            columnModel.getColumn(TITLE_COLUMN_INDEX).setPreferredWidth(170);
            columnModel.getColumn(AUTHOR_COLUMN_INDEX).setPreferredWidth(200);
            columnModel.getColumn(RATING_COLUMN_INDEX).setPreferredWidth(170);
            columnModel.getColumn(REVIEW_COLUMN_INDEX).setPreferredWidth(260);

            personalDatabaseTable.setRowHeight(30);
            personalDatabaseTable.getTableHeader().setResizingAllowed(false);

            JScrollPane scrollPane = new JScrollPane(personalDatabaseTable);
            personalDatabasePanel.add(scrollPane);
        }

        personalDatabaseTableModel.addRow(rowData);
    }

    private void initializePersonalDatabasePanel() {
        personalDatabasePanel = new JPanel();
        personalDatabasePanel.setLayout(new BorderLayout());

        addPersonalDatabaseSearchFunctionality();
        personalDatabaseTableModel = new DefaultTableModel();
        personalDatabaseTableModel.addColumn("<html><b>Title</b></html>");
        personalDatabaseTableModel.addColumn("<html><b>Author</b></html>");
        personalDatabaseTableModel.addColumn("<html><b>Rating</b></html>");
        personalDatabaseTableModel.addColumn("<html><b>Review</b></html>");
        personalDatabaseTableModel.addColumn("<html><b>Status</b></html>");
        personalDatabaseTableModel.addColumn("<html><b>Time Spent</b></html>");
        personalDatabaseTableModel.addColumn("<html><b>Start Date</b></html>");
        personalDatabaseTableModel.addColumn("<html><b>End Date</b></html>");
        personalDatabaseTableModel.addColumn("<html><b>User Rating</b></html>");
        personalDatabaseTableModel.addColumn("<html><b>User Review</b></html>");

        personalDatabaseTable = new JTable(personalDatabaseTableModel);
        personalDatabaseTable.getTableHeader().setReorderingAllowed(false);

        personalDatabaseTable.getTableHeader().setForeground(Color.BLACK);
        personalDatabaseTable.getTableHeader().setBackground(Color.decode("#ADC4CE"));
        personalDatabaseTable.getTableHeader()
                .setPreferredSize(new Dimension(personalDatabaseTable.getTableHeader().getWidth(), 40));
        personalDatabaseTable.setForeground(Color.BLACK);
        personalDatabaseTable.setSelectionBackground(Color.decode("#F1F0E8"));
        personalDatabaseTable.setSelectionForeground(Color.BLACK);

        TableColumnModel columnModel = personalDatabaseTable.getTableHeader().getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(150);
        columnModel.getColumn(1).setPreferredWidth(170);
        columnModel.getColumn(2).setPreferredWidth(100);
        columnModel.getColumn(3).setPreferredWidth(200);
        columnModel.getColumn(4).setPreferredWidth(100);
        columnModel.getColumn(5).setPreferredWidth(100);
        columnModel.getColumn(6).setPreferredWidth(100);
        columnModel.getColumn(7).setPreferredWidth(100);
        columnModel.getColumn(8).setPreferredWidth(100);
        columnModel.getColumn(9).setPreferredWidth(100);

        personalDatabaseTable.setRowHeight(30);
        personalDatabaseTable.getTableHeader().setResizingAllowed(false);

        JScrollPane scrollPane = new JScrollPane(personalDatabaseTable);
        personalDatabasePanel.add(scrollPane, BorderLayout.CENTER);

        personalDatabaseTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int column = personalDatabaseTable.getColumnModel().getColumnIndexAtX(e.getX());
                int row = e.getY() / personalDatabaseTable.getRowHeight();

                if (column == 8) {
                    openUserRatingWindow(row);
                } else if (column == 9) {
                    openUserReviewWindow(row);
                }
            }
        });
    }

    private void openUserRatingWindow(int row) {
        // Get the selected title and author from the personalDatabaseTableModel
        String selectedTitle = personalDatabaseTableModel.getValueAt(row, 0).toString();
        String selectedAuthor = personalDatabaseTableModel.getValueAt(row, 1).toString();

        // Open the UserRatingWindow with the selected title and author
        new UserRatingWindow(selectedTitle, selectedAuthor);
    }

    private void openUserReviewWindow(int row) {
        // Get the selected title and author from the personalDatabaseTableModel
        String selectedTitle = personalDatabaseTableModel.getValueAt(row, 0).toString();
        String selectedAuthor = personalDatabaseTableModel.getValueAt(row, 1).toString();

        // Open the UserReviewWindow with the selected title and author
        new UserReviewWindow(selectedTitle, selectedAuthor);
    }

    private void addPersonalDatabaseSearchFunctionality() {
        personalDatabaseSearchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        Color buttonHeaderColor = new Color(173, 196, 206);
        searchButton.setBackground(buttonHeaderColor);
        searchButton.setForeground(Color.BLACK);
        JPanel searchPanel = new JPanel();

        searchPanel.setBackground(Color.WHITE);
        searchPanel.add(personalDatabaseSearchField);
        searchPanel.add(searchButton);
        personalDatabasePanel.add(searchPanel, BorderLayout.NORTH);

        searchButton.addActionListener(e -> searchPersonalDatabase());
        personalDatabaseSearchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                searchPersonalDatabase();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                searchPersonalDatabase();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                searchPersonalDatabase();
            }
        });
    }

    private void searchPersonalDatabase() {
        String searchText = personalDatabaseSearchField.getText().trim().toLowerCase();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(personalDatabaseTableModel);
        personalDatabaseTable.setRowSorter(sorter);
        if (searchText.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
        }
    }

    private void showTablePanel() {
        if (personalDatabasePanel != null) {
            mainPanel.remove(personalDatabasePanel);
        }
        mainPanel.add(tablePanel, BorderLayout.CENTER);
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
        mainPanel.add(personalDatabasePanel, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }
}