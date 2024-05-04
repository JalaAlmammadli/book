package gui_library.admin_crud;

import database_system.BookDataBase;
import database_system.exceptions.IllegalMemberException;
import entities.book.Book;
import gui_elements.Button;
import gui_elements.Label;
import gui_elements.TextField;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import lang_change.Lang;

public class AddBook extends JFrame{

    TextField field1;
    TextField field2;
    Button button;
    JPanel panel;
    JFrame frame;

    public AddBook(){
        frame = new JFrame(Lang.addBook);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.setBounds(500, 250, 350, 200);
        frame.setResizable(false);

        panel = new JPanel();
        panel.setVisible(true);
        panel.setLayout(null);
        frame.add(panel);

        // Title and Author labels
        new Label(10, 30, 50, 25, Lang.bookTitle, panel);
        new Label(10, 70, 50, 25, Lang.bookAuthor, panel);

        //Title text field
        field1 = new TextField(70, 30, 200, 25, 25, panel);
        //Author text field
        field2 = new TextField(70, 70, 200, 25, 25, panel);



        //Confirm button
        button = new Button(120, 120, 100, 25, Lang.confirm, panel);
        button.getObject().addActionListener((ActionEvent e) -> {

            try {
                Book newBook = Book.createBook(field1.getObject().getText(), field2.getObject().getText());
                BookDataBase.MainBookList.add(newBook);
                // addBookToList(newBook);
                frame.dispose();   
            } catch (IllegalMemberException ex) {

                Label info = new Label(100, 100, 200, 25, ex.getMessage(), panel);
            }
        });
    }
}
