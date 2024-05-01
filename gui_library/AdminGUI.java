package gui_library;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminGUI extends DatabaseLib {
    private JButton addMovieButton;
    private JButton modifyMovieButton;
    private JButton deleteMovieButton;
    private JButton removeReviewButton;
    private JButton deleteUserButton;

    public AdminGUI() {
        super();

        addMovieButton = new JButton("Add New Movie");
        modifyMovieButton = new JButton("Modify Movie");
        deleteMovieButton = new JButton("Delete Movie");
        removeReviewButton = new JButton("Remove Review");
        deleteUserButton = new JButton("Delete User");

        addMovieButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        modifyMovieButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        deleteMovieButton.addActionListener(new ActionListener() {
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

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addMovieButton);
        buttonPanel.add(modifyMovieButton);
        buttonPanel.add(deleteMovieButton);
        buttonPanel.add(removeReviewButton);
        buttonPanel.add(deleteUserButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    public void initializeTable(Object[][] headersAndData) {
        column = (String[]) headersAndData[0];
        data = new Object[headersAndData.length - 1][column.length];
        for (int i = 1; i < headersAndData.length; i++) {
            data[i - 1] = headersAndData[i];
        }

        model = new DefaultTableModel(data, column) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != REVIEW_COLUMN_INDEX && column != RATING_COLUMN_INDEX;
            }
        };

        jt = new JTable(model);
        jt.getTableHeader().setReorderingAllowed(false);
        js = new JScrollPane(jt);
        tablePanel.add(js, BorderLayout.CENTER);
    }

    public void addNewMovie(String title, String author, String rating, String review) {

    }

    public void modifyMovie(int rowIndex, String title, String author, String rating, String review) {


    }

    public void deleteMovie(int rowIndex) {

    }

    public void removeReview(int rowIndex) {

    }

    public void deleteUserAccount(String username) {

    }

    public static void main(String[] args) {
        new AdminGUI();
    }
}
