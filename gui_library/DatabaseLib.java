package gui_library;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DatabaseLib {
    JFrame jf;
    JScrollPane js;
    JTable jt;
    JTextField searchField;
    String[] column;
    Object[][] data;
    DefaultTableModel model;

    public DatabaseLib() {
        SwingUtilities.invokeLater(() -> {
            System.out.println("Hello World!");
            TableGUI();
        });
    }

    private void TableGUI() {
        jf = new JFrame("Book Database");
        jf.setPreferredSize(new Dimension(900, 600));

        Object[][] headersAndData = getDataAndHeaders();
        if (headersAndData != null) {
            column = (String[]) headersAndData[0];
            data = new Object[headersAndData.length - 1][column.length];
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
            jf.add(js, BorderLayout.CENTER);

            searchField = new JTextField(20);
            JButton searchButton = new JButton("Search");
            Color buttonHeaderColor = new Color(0x776B5D);
            searchButton.setBackground(buttonHeaderColor);
            searchButton.setForeground(Color.BLACK);
            JPanel searchPanel = new JPanel();
            searchPanel.add(new JLabel("Search: "));
            searchPanel.add(searchField);
            searchPanel.add(searchButton);
            jf.add(searchPanel, BorderLayout.NORTH);

            searchButton.addActionListener(e -> {
                String searchText = searchField.getText().toLowerCase();
                searchTable(searchText);
            });

            searchField.addActionListener(e -> {
                String searchText = searchField.getText().toLowerCase();
                searchTable(searchText);
            });

            jt.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int row = jt.rowAtPoint(e.getPoint());
                    int column = jt.columnAtPoint(e.getPoint());

                    if (column == 3) {
                        String username = (String) jt.getValueAt(row, column);
                        if (username != null) {
                            reviewUser(username);
                        }
                    }
                }
            });

            jf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); 
            jf.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                    System.exit(0); 
                }
            });
            jf.pack();
            jf.setLocationRelativeTo(null);
            jf.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Failed to load data from file.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        jt.getTableHeader().setForeground(Color.BLACK);
        jt.getTableHeader().setBackground(Color.decode("#776B5D"));
        jt.getTableHeader().setPreferredSize(new Dimension(jt.getTableHeader().getWidth(), 40));
        jt.setBackground(Color.WHITE);
        jt.setForeground(Color.BLACK);
        jt.setSelectionBackground(Color.decode("#F3EEEA"));
        jt.setSelectionForeground(Color.BLACK);

        TableColumnModel columnModel = jt.getTableHeader().getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(250); // Adjust as needed for the Title column
        columnModel.getColumn(1).setPreferredWidth(250); // Adjust as needed for the Author column
        columnModel.getColumn(2).setPreferredWidth(170); // Adjust as needed for the Rating column
        columnModel.getColumn(3).setPreferredWidth(230);

        jt.setRowHeight(30);
        
    }

    private Object[][] getDataAndHeaders() {
        try {
            // File Name***************************************************
            String fileName = "./brodsky.csv";
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            ArrayList<Object[]> dataRows = new ArrayList<>();
            String header = br.readLine();
            String[] headers = null;

            if (header != null) {
                headers = header.split(",", -1);
                String[] finalHeaders = new String[headers.length + 2];
                System.arraycopy(headers, 0, finalHeaders, 0, headers.length);
                finalHeaders[headers.length] = "Rating";
                finalHeaders[headers.length + 1] = "Review";
                headers = finalHeaders;
            }
            String str;
            while ((str = br.readLine()) != null) {
                // Added by Orkhan*****************
                add(dataRows, str);
            }
            br.close();
            if (!dataRows.isEmpty()) {
                Object[][] result = new Object[dataRows.size() + 1][headers.length];
                result[0] = headers;
                for (int i = 0; i < dataRows.size(); i++) {
                    result[i + 1] = dataRows.get(i);
                }
                return result;
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void searchTable(String searchText) {
        if (searchText.trim().length() == 0) {
            jt.setModel(model);
            return;
        }

        DefaultTableModel filteredModel = new DefaultTableModel(column, 0);
        for (Object[] rowData : data) {
            for (Object cellData : rowData) {
                if (cellData != null && cellData.toString().toLowerCase().contains(searchText)) {
                    filteredModel.addRow(rowData);
                    break;
                }
            }
        }
        jt.setModel(filteredModel);
    }

    private void add(ArrayList<Object[]> dataRows, String line) {
        if (line.charAt(0) == '\"') {
            String row[] = line.split("\",", -1);
            String[] titles = row[0].replaceAll("\"", "").split(",", -1);

            for (int i = 0; i < titles.length; i++) {
                if (titles[i].equals("")) {
                    titles[i] = "Unknown";
                } else if (titles[i].startsWith(" ")) {
                    titles[i] = titles[i].replaceFirst(" ", "");
                }
                addToList(dataRows, titles[i], row[1]);
            }
        } else if (line.charAt(line.length() - 1) == '\"') {
            String row[] = line.split(",\"", -1);
            addToList(dataRows, row[0], row[1].replaceAll("\"", ""));

        } else {
            String row[] = line.split(",", -1);

            if (row[0].equals("")) {
                row[0] = "Unknown";
            } else if (row[1].equals("")) {
                row[1] = "Unknown";
            }
            addToList(dataRows, row[0], row[1]);
        }
    }

    private void addToList(ArrayList<Object[]> dataRows, String book, String author) {
        Object[] dataRow = new Object[]{book, author, "No Rating", "No Review"};
        dataRows.add(dataRow);
    }

    private void reviewUser(String username) {
        int selectedRow = jt.getSelectedRow();

        String title = (String) jt.getValueAt(selectedRow, 0);
        String author = (String) jt.getValueAt(selectedRow, 1);
        String rating = (String) jt.getValueAt(selectedRow, 2);

        Object[][] bookData = {{title, author, rating}};
        String[] bookColumns = {"Title", "Author", "Rating"};
        
        JTable bookTable = new JTable(bookData, bookColumns);
        bookTable.setEnabled(false);
        bookTable.setRowHeight(20);
        JTableHeader bookHeader = bookTable.getTableHeader();
        bookHeader.setBackground(Color.decode("#776B5D"));

        Object[][] userData = {{username, "", ""}}; // Fill with appropriate data
        String[] userColumns = {"Username", "User Rating", "User Review"};
        JTable userTable = new JTable(userData, userColumns);
        userTable.setEnabled(false);
        userTable.setRowHeight(20);
        JTableHeader userHeader = userTable.getTableHeader();
        userHeader.setBackground(Color.decode("#776B5D"));


        JScrollPane bookScrollPane = new JScrollPane(bookTable);
        JScrollPane userScrollPane = new JScrollPane(userTable);

        JPanel bookPanel = new JPanel(new GridLayout(0, 1));
        bookPanel.setBorder(BorderFactory.createTitledBorder("Book Details"));
        bookPanel.add(bookScrollPane);

        JPanel userPanel = new JPanel(new GridLayout(0, 1));
        userPanel.setBorder(BorderFactory.createTitledBorder("User Review"));
        userPanel.add(userScrollPane);

        JFrame reviewFrame = new JFrame("User Review");
        reviewFrame.setLayout(new GridLayout(2, 1));
        reviewFrame.add(bookPanel);
        reviewFrame.add(userPanel);

        reviewFrame.setPreferredSize(new Dimension(500, 200));
        reviewFrame.pack();
        reviewFrame.setVisible(true);
        reviewFrame.setLocationRelativeTo(null);
    }
}
