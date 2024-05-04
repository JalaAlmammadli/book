package gui_library;

import gui_library.admin_crud.AddBook;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class AdminGUI extends DatabaseLib {
    private JButton addBookButton;
    private JButton deleteMovieButton;
    private JButton removeReviewButton;
    private JButton deleteUserButton;

    public AdminGUI() {
        super();

        addBookButton = new JButton("Add New Book");
        deleteMovieButton = new JButton("Delete Book");
        removeReviewButton = new JButton("Remove Review");
        deleteUserButton = new JButton("Delete User");

        addBookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Hello");
                new AddBook();
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
    }

    @Override
    public void initializeTable(Object[][] headersAndData) {
        super.initializeTable(headersAndData); 

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addBookButton);
        buttonPanel.add(deleteMovieButton);
        buttonPanel.add(removeReviewButton);
        buttonPanel.add(deleteUserButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        new AdminGUI();
    }
}
