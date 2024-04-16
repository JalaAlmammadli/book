package gui_table;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

public class Table {
    JFrame jf;
    JScrollPane js;
    JTable jt;
    JTextField searchField;
    String[] column;
    Object[][] data;
    DefaultTableModel model;

    Table() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                TableGUI();
            }
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
            JPanel searchPanel = new JPanel();
            searchPanel.add(new JLabel("Search: "));
            searchPanel.add(searchField);
            searchPanel.add(searchButton);
            jf.add(searchPanel, BorderLayout.NORTH);

            searchButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String searchText = searchField.getText().toLowerCase();
                    searchTable(searchText);
                }
            });

            jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            jf.pack();
            jf.setLocationRelativeTo(null);
            jf.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Failed to load data from file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private Object[][] getDataAndHeaders() {
        try {
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
                String[] rowData = str.split(",", -1);
                if (rowData.length >= 2) {
                    Object[] dataRow = new Object[]{rowData[0], rowData[1], "", ""};
                    dataRows.add(dataRow);
                }
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

    public static void main(String[] args) {
        new Table();
    }
}
