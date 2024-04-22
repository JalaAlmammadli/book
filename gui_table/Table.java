package gui_table;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class Table {
    JFrame jf;
    JScrollPane js;
    JTable jt;
    JTextField searchField;
    String[] column;
    Object[][] data;
    DefaultTableModel model;
    TableRowSorter<DefaultTableModel> sorter;
    int[] sortingOrder;

    public Table() {
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

            sorter = new TableRowSorter<>(model);
            jt.setRowSorter(sorter);

            searchField = new JTextField(20);
            JButton searchButton = new JButton("Search");
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

            sortingOrder = new int[column.length];
            for (int i = 0; i < sortingOrder.length; i++) {
                sortingOrder[i] = 0;
            }

            jt.getTableHeader().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int column = jt.columnAtPoint(e.getPoint());
                    sortTable(column);
                }
            });

            jf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            jf.pack();
            jf.setLocationRelativeTo(null);
            jf.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Failed to load data from file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private Object[][] getDataAndHeaders() {
        try {
            // File Name***************************************************
            String fileName = "team-project-team-50/brodsky.csv";
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
        Object[] dataRow = new Object[] { book, author, "No Rating", "No Review" };
        dataRows.add(dataRow);
    }

    private void sortTable(int colIndex) {
        Comparator<Object> comparator = (Comparator<Object>) (a, b) -> {
            if (a == null && b == null) {
                return 0;
            } else if (a == null) {
                return 1;
            } else if (b == null) {
                return -1;
            } else {
                return a.toString().compareToIgnoreCase(b.toString());
            }
        };
    
        int sortOrder = sortingOrder[colIndex];
        sortingOrder[colIndex] = (sortOrder + 1) % 3;
        
        switch (sortOrder) {
            case 0: // First click - sort ascending
                sorter.setComparator(colIndex, comparator);
                break;
            case 1: // Second click - sort descending
                sorter.setComparator(colIndex, comparator.reversed());
                break;
            case 2: // Third click - restore original order
                sorter.setComparator(colIndex, null);
                sorter.setSortKeys(null); // Clear sorting for all columns
                break;
        }
    }
    
}
