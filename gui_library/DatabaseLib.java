package gui_library;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.ArrayList;

import gui_elements.Actions;
import app_runner.*;

public class DatabaseLib extends Actions {
    // Constants for file name and column indices
    private static final String FILE_NAME = "./brodsky.csv";
    static final int TITLE_COLUMN_INDEX = 0;
    static final int AUTHOR_COLUMN_INDEX = 1;
    static final int RATING_COLUMN_INDEX = 2;
    static final int REVIEW_COLUMN_INDEX = 3;

    // JFrame and other GUI components
    private JFrame jf;
    private JPanel mainPanel; 
    private JPanel tablePanel; 
    private JPanel settingsPanel;
    private JPanel personalPanel;
    private JScrollPane js;
    private JTable jt;
    private JTextField searchField;
    private String[] column;
    private Object[][] data;
    private DefaultTableModel model;
    private MyLibraryPanel myLibraryPanel;

    // Constructor
    public DatabaseLib() {
        SwingUtilities.invokeLater(this::initializeGUI);
    }

    @Override
    public void windowClosing(WindowEvent e) {
        SaveData.save();
        System.exit(0);
    }

    // Method to initialize GUI components
    private void initializeGUI() {
        // Create the main frame
        jf = new JFrame("Book Database");
        jf.setPreferredSize(new Dimension(1000, 650));
        mainPanel = new JPanel(new BorderLayout());
        tablePanel = new JPanel(new BorderLayout());
    
        // Initialize table with data
        Object[][] headersAndData = getDataAndHeaders();
        if (headersAndData != null) {
            initializeTable(headersAndData);
            addReviewColumnMouseListener();
            customizeTableAppearance();
        } else {
            JOptionPane.showMessageDialog(null, "Failed to load data from file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    
        personalPanel = new PersonalDatabasePanel(data, column);
        mainPanel.add(tablePanel, BorderLayout.CENTER);
        myLibraryPanel = new MyLibraryPanel();
        settingsPanel = new SettingsPanel(); 
        addLeftPanels();
        jf.add(mainPanel);
        propertyJFrame();
        addSearchFunctionality();
    
        mainPanel.remove(myLibraryPanel);
        mainPanel.add(settingsPanel, BorderLayout.CENTER); 
        mainPanel.revalidate();
        mainPanel.repaint();
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

        jt = new JTable(model);
        jt.getTableHeader().setReorderingAllowed(false);
        js = new JScrollPane(jt);
        tablePanel.add(js, BorderLayout.CENTER);
    }

    // Method to add search functionality
    private void addSearchFunctionality() {
        searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        Color buttonHeaderColor = new Color(173, 196, 206);
        searchButton.setBackground(buttonHeaderColor);
        searchButton.setForeground(Color.BLACK);
        JPanel searchPanel = new JPanel();
        searchPanel.setBackground(Color.WHITE); 
        searchPanel.add(new JLabel("Search: "));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        tablePanel.add(searchPanel, BorderLayout.NORTH);

        // Add action listeners for search
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
        jt.getTableHeader().setBackground(Color.decode("#ADC4CE"));
        jt.getTableHeader().setPreferredSize(new Dimension(jt.getTableHeader().getWidth(), 40));
        jt.setBackground(Color.WHITE);
        jt.setForeground(Color.BLACK);
        jt.setSelectionBackground(Color.decode("#F1F0E8"));
        jt.setSelectionForeground(Color.BLACK);

        TableColumnModel columnModel = jt.getTableHeader().getColumnModel();
        columnModel.getColumn(TITLE_COLUMN_INDEX).setPreferredWidth(270);
        columnModel.getColumn(AUTHOR_COLUMN_INDEX).setPreferredWidth(300);
        columnModel.getColumn(RATING_COLUMN_INDEX).setPreferredWidth(170);
        columnModel.getColumn(REVIEW_COLUMN_INDEX).setPreferredWidth(260);

        jt.setRowHeight(30);
        jt.getTableHeader().setResizingAllowed(false);
    }

    // Method to load data and headers from file
    private Object[][] getDataAndHeaders() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String header = br.readLine();
            String[] headers = readHeaders(header);
            ArrayList<Object[]> dataRows = readDataRows(br);
            return assembleResult(headers, dataRows);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading data from file.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return null;
        }
    }

    // Method to read headers from the first line of the file
    private String[] readHeaders(String header) {
        if (header != null) {
            String[] headers = header.split(",", -1);
            String[] finalHeaders = new String[headers.length + 2];
            finalHeaders[0] = "<html><b>Title</b></html>"; 
            finalHeaders[1] = "<html><b>Author</b></html>"; 
            finalHeaders[headers.length] = "<html><b>Rating</b></html>";
            finalHeaders[headers.length + 1] = "<html><b>Review</b></html>";
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

    // Method to add left side panels
    private void addLeftPanels() {
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        // Create buttons for different panels
        JButton tableButton = new JButton("Table");
        JButton myLibraryButton = new JButton("My Library");
        JButton settingsButton = new JButton("Settings");
        JButton personalDB = new JButton("Personal Database");

        // Set fixed width for buttons
        int buttonWidth = 250; 
        int buttonHeight = 30;

        // Set fixed width and let height adjust automatically
        tableButton.setMaximumSize(new Dimension(buttonWidth, buttonHeight));
        myLibraryButton.setMaximumSize(new Dimension(buttonWidth, buttonHeight));
        settingsButton.setMaximumSize(new Dimension(buttonWidth, buttonHeight));
        personalDB.setMaximumSize(new Dimension(buttonWidth, buttonHeight));

        // Add buttons to panel
        leftPanel.add(tableButton);
        leftPanel.add(myLibraryButton);
        leftPanel.add(settingsButton);
        leftPanel.add(personalDB);

        // Set background color for buttons
        Color buttonColor = new Color(150, 182, 197);
        tableButton.setBackground(buttonColor);
        myLibraryButton.setBackground(buttonColor);
        settingsButton.setBackground(buttonColor);
        personalDB.setBackground(buttonColor);

        // Set text color for buttons
        Color textColor = Color.BLACK; // Adjust as needed
        tableButton.setForeground(textColor);
        myLibraryButton.setForeground(textColor);
        settingsButton.setForeground(textColor);
        personalDB.setForeground(textColor);

        // Add action listeners to buttons
        tableButton.addActionListener(e -> {
            if (myLibraryPanel != null) {
                mainPanel.remove(myLibraryPanel); 
            }
            if (settingsPanel != null) {
                mainPanel.remove(settingsPanel);
            }
            if (personalPanel != null) {
                mainPanel.remove(personalPanel);
            }
            mainPanel.add(tablePanel, BorderLayout.CENTER);
            mainPanel.revalidate();
            mainPanel.repaint();
        });

        myLibraryButton.addActionListener(e -> {
            if (tablePanel != null) {
                mainPanel.remove(tablePanel); 
            }
            if (settingsPanel != null) {
                mainPanel.remove(settingsPanel);
            }
            if (personalPanel != null) {
                mainPanel.remove(personalPanel);
            }
            mainPanel.add(myLibraryPanel, BorderLayout.CENTER);
            mainPanel.revalidate();
            mainPanel.repaint();
        });

        settingsButton.addActionListener(e -> {
            if (tablePanel != null) {
                mainPanel.remove(tablePanel);
            }
            if (myLibraryPanel != null) {
                mainPanel.remove(myLibraryPanel);
            }
            if (personalPanel != null) {
                mainPanel.remove(personalPanel);
            }
            mainPanel.add(settingsPanel, BorderLayout.CENTER);
            mainPanel.revalidate();
            mainPanel.repaint();
        });

        personalDB.addActionListener(e -> {
            if (tablePanel != null) {
                mainPanel.remove(tablePanel); 
            }
            if (myLibraryPanel != null) {
                mainPanel.remove(myLibraryPanel);
            }
            if (settingsPanel != null) {
                mainPanel.remove(settingsPanel);
            }
            mainPanel.add(personalPanel, BorderLayout.CENTER);
            mainPanel.revalidate();
            mainPanel.repaint();
        });

        // Add a black line border between buttons
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        leftPanel.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(1, 1, 1, 1)));

        // Add the panel to the frame
        jf.add(leftPanel, BorderLayout.WEST);
    }
    public static void main(String[] args) {
        new DatabaseLib();
    }
}
