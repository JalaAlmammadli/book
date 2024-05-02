package gui_library;

import app_runner.*;
import gui_elements.Actions;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.*;

public class DatabaseLib extends Actions implements  WindowListener{
    private static final String FILE_NAME = "./brodsky.csv";
    static final int TITLE_COLUMN_INDEX = 0;
    static final int AUTHOR_COLUMN_INDEX = 1;
    static final int RATING_COLUMN_INDEX = 2;
    static final int REVIEW_COLUMN_INDEX = 3;

    protected JFrame jf;
    protected JPanel mainPanel;
    public JPanel tablePanel;
    protected JPanel settingsPanel;
    protected JScrollPane js;
    protected JTable jt;
    private JTextField searchField;
    protected String[] column;
    protected Object[][] data;
    protected DefaultTableModel model;

    public DatabaseLib() {
        SwingUtilities.invokeLater(this::initializeGUI);
    }

    // Shut down the whole program and saves all data while closing
    @Override
    public void windowClosing(WindowEvent e) {

        System.out.println("closing");
        SaveData.save();
        System.exit(0);
    }

    private void initializeGUI() {
        jf = new JFrame("Book Database");
        jf.setPreferredSize(new Dimension(1050, 650));
        mainPanel = new JPanel(new BorderLayout());
        tablePanel = new JPanel(new BorderLayout());

        // It will track the changes in the window
        // DONT DELETE THIS
        jf.addWindowListener(this);

        Object[][] headersAndData = getDataAndHeaders();
        if (headersAndData != null) {
            initializeTable(headersAndData);
            addReviewColumnMouseListener();
            customizeTableAppearance();
        } else {
            JOptionPane.showMessageDialog(null, "Failed to load data from file.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        mainPanel.add(tablePanel, BorderLayout.CENTER);
        settingsPanel = new SettingsPanel();
        addLeftPanels();
        jf.add(mainPanel);
        propertyJFrame();
        addSearchFunctionality();

        mainPanel.add(settingsPanel, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    protected void initializeTable(Object[][] headersAndData) {
        column = (String[]) headersAndData[0];
        data = new Object[headersAndData.length - 1][column.length];
        for (int i = 1; i < headersAndData.length; i++) {
            data[i - 1] = headersAndData[i];
        }

        model = new DefaultTableModel(data, column); 

        jt = new JTable(model);
        jt.getTableHeader().setReorderingAllowed(false);
        js = new JScrollPane(jt);
        tablePanel.add(js, BorderLayout.CENTER);
    }

    private void addSearchFunctionality() {
        searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        Color buttonHeaderColor = new Color(173, 196, 206);
        searchButton.setBackground(buttonHeaderColor);
        searchButton.setForeground(Color.BLACK);
        JPanel searchPanel = new JPanel();
        
        // searchPanel.add(new JLabel("Search: "));

        searchPanel.setBackground(Color.WHITE);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        tablePanel.add(searchPanel, BorderLayout.NORTH);

        searchButton.addActionListener(e -> search());
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                search();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                search();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                search();
            }
        });
    }

    private void search() {
        String searchText = searchField.getText().trim().toLowerCase();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        jt.setRowSorter(sorter);
        if (searchText.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
        }
    }

    private void addReviewColumnMouseListener() {
        jt.addMouseListener(new TableListeners.ReviewColumnMouseListener(jt, REVIEW_COLUMN_INDEX));
    }

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

    private void customizeTableAppearance() {
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

    protected String[] readHeaders(String header) {
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

    private ArrayList<Object[]> readDataRows(BufferedReader br) throws IOException {
        ArrayList<Object[]> dataRows = new ArrayList<>();
        String str;
        while ((str = br.readLine()) != null) {
            add(dataRows, str);
        }
        return dataRows;
    }

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

    public void addLeftPanels() {
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        JButton tableButton = new JButton("General Database");
        JButton settingsButton = new JButton("Settings");

        int buttonWidth = 250;
        int buttonHeight = 30;

        tableButton.setMaximumSize(new Dimension(buttonWidth, buttonHeight));
        settingsButton.setMaximumSize(new Dimension(buttonWidth, buttonHeight));

        leftPanel.add(tableButton);
        leftPanel.add(settingsButton);

        Color buttonColor = new Color(150, 182, 197);
        tableButton.setBackground(buttonColor);
        settingsButton.setBackground(buttonColor);

        Color textColor = Color.BLACK;
        tableButton.setForeground(textColor);
        settingsButton.setForeground(textColor);

        tableButton.addActionListener(e -> {
            if (settingsPanel != null) {
                mainPanel.remove(settingsPanel);
            }
            mainPanel.add(tablePanel, BorderLayout.CENTER);
            mainPanel.revalidate();
            mainPanel.repaint();
        });

        settingsButton.addActionListener(e -> {
            if (tablePanel != null) {
                mainPanel.remove(tablePanel);
            }

            mainPanel.add(settingsPanel, BorderLayout.CENTER);
            mainPanel.revalidate();
            mainPanel.repaint();
        });

        Border border = BorderFactory.createLineBorder(Color.BLACK);
        leftPanel.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(1, 1, 1, 1)));

        jf.add(leftPanel, BorderLayout.WEST);
    }

    public static void main(String[] args) {
        new DatabaseLib();
    }
}
