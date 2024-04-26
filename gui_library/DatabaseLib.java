package gui_library;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class DatabaseLib {
    // Constants for file name and column indices
    private static final String FILE_NAME = "./brodsky.csv";
    static final int TITLE_COLUMN_INDEX = 0;
    static final int AUTHOR_COLUMN_INDEX = 1;
    static final int RATING_COLUMN_INDEX = 2;
    static final int REVIEW_COLUMN_INDEX = 3;

    // JFrame and other GUI components
    private JFrame jf;
    private JScrollPane js;
    private JTable jt;
    private JTextField searchField;
    private String[] column;
    private Object[][] data;
    private DefaultTableModel model;

    // Constructor
    public DatabaseLib() {
        SwingUtilities.invokeLater(() -> {
            System.out.println("Hello World!");
            TableGUI();
        });
    }

    // Method to initialize GUI components and display the table
    private void TableGUI() {
        // Initialize JFrame
        jf = new JFrame("Book Database");
        jf.setPreferredSize(new Dimension(900, 600));

        // Load data and headers
        Object[][] headersAndData = getDataAndHeaders();
        if (headersAndData != null) {
            initializeTable(headersAndData);
            addSearchFunctionality();
            addReviewColumnMouseListener();
            propertyJFrame();
        } else {
            JOptionPane.showMessageDialog(null, "Failed to load data from file.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        customizeTableAppearance();
    }

    // Method to initialize the table with data and headers
    private void initializeTable(Object[][] headersAndData) {
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

        // Initialize JTable
        jt = new JTable(model);
        jt.getTableHeader().setReorderingAllowed(false);
        js = new JScrollPane(jt);
        jf.add(js, BorderLayout.CENTER);
    }

    // Method to add search functionality
    private void addSearchFunctionality() {
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

        searchButton.addActionListener(new TableListeners.SearchActionListener(jt, searchField, column, data, model));

        searchField.addActionListener(new TableListeners.SearchActionListener(jt, searchField, column, data, model));
    }

    // Method to handle mouse clicks on the review column
    private void addReviewColumnMouseListener() {
        jt.addMouseListener(new TableListeners.ReviewColumnMouseListener(jt, REVIEW_COLUMN_INDEX));
    }

    // Method to configure JFrame properties
    private void propertyJFrame() {
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
    }

    // Method to customize table appearance
    private void customizeTableAppearance() {
        // Set table header and cell properties
        jt.getTableHeader().setForeground(Color.BLACK);
        jt.getTableHeader().setBackground(Color.decode("#776B5D"));
        jt.getTableHeader().setPreferredSize(new Dimension(jt.getTableHeader().getWidth(), 40));
        jt.setBackground(Color.WHITE);
        jt.setForeground(Color.BLACK);
        jt.setSelectionBackground(Color.decode("#F3EEEA"));
        jt.setSelectionForeground(Color.BLACK);

        // Set column widths
        TableColumnModel columnModel = jt.getTableHeader().getColumnModel();
        columnModel.getColumn(TITLE_COLUMN_INDEX).setPreferredWidth(250);
        columnModel.getColumn(AUTHOR_COLUMN_INDEX).setPreferredWidth(250);
        columnModel.getColumn(RATING_COLUMN_INDEX).setPreferredWidth(170);
        columnModel.getColumn(REVIEW_COLUMN_INDEX).setPreferredWidth(230);

        // Set row height
        jt.setRowHeight(30);
    }

    // Method to load data and headers from file
    private Object[][] getDataAndHeaders() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String header = br.readLine();
            String[] headers = readHeaders(header);
            ArrayList<Object[]> dataRows = readDataRows(br);
            return assembleResult(headers, dataRows);
        } catch (IOException e) {
            // Display an error message to the user
            JOptionPane.showMessageDialog(null, "Error reading data from file.", "Error", JOptionPane.ERROR_MESSAGE);
            // Log the exception (optional)
            e.printStackTrace();
            return null;
        }
    }

    // Method to read headers from the first line of the file
    private String[] readHeaders(String header) {
        if (header != null) {
            String[] headers = header.split(",", -1);
            String[] finalHeaders = new String[headers.length + 2];
            System.arraycopy(headers, 0, finalHeaders, 0, headers.length);
            finalHeaders[headers.length] = "Rating";
            finalHeaders[headers.length + 1] = "Review";
            return finalHeaders;
        }
        return null;
    }

    // Method to read data rows from the file
    private ArrayList<Object[]> readDataRows(BufferedReader br) throws IOException {
        ArrayList<Object[]> dataRows = new ArrayList<>();
        String str;
        while ((str = br.readLine()) != null) {
            add(dataRows, str);
        }
        return dataRows;
    }

    // Method to assemble the final result from headers and data rows
    private Object[][] assembleResult(String[] headers, ArrayList<Object[]> dataRows) {
        if (headers != null && !dataRows.isEmpty()) {
            Object[][] result = new Object[dataRows.size() + 1][headers.length];
            result[0] = headers;
            for (int i = 0; i < dataRows.size(); i++) {
                result[i + 1] = dataRows.get(i);
            }
            return result;
        } else {
            return null;
        }
    }

    // Method to parse and add data to the table
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

    // Method to add book details to the list of data rows
    private void addToList(ArrayList<Object[]> dataRows, String book, String author) {
        Object[] dataRow = new Object[]{book, author, "No Rating", "No Review"};
        dataRows.add(dataRow);
    }
}
