package gui_table;

import java.awt.BorderLayout;
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
        jf = new JFrame("JTable Demo");
        column = new String[]{"Title", "Author"};
        data = getData();

        if (data != null) {
            model = new DefaultTableModel(data, column);
            jt = new JTable(model);
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

    Object[][] getData() {
        try {
            String fileName = "team-project-team-50/brodsky.csv";
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            ArrayList<String[]> list = new ArrayList<>();
            String str;
            while ((str = br.readLine()) != null) {
                list.add(str.split(",", -1)); 
            }
            br.close();
            return list.toArray(new Object[0][]);
        } catch (IOException e) {
            e.printStackTrace(); 
            return null;
        }
    }

    public static void main(String[] args) {
        new Table();
    }
}
