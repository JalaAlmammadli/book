package gui_library.admin_crud;


import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import gui_library.AdminGUI;
import entities.book.Book;
import gui_elements.Button;
import gui_elements.Label;
import gui_elements.TextField;
import lang_change.Lang;

public class DeleteBook extends JFrame {
    TextField field1;
    TextField field2;
    Button button;
    JPanel panel;
    JFrame frame;

    public DeleteBook() {
        frame = new JFrame(Lang.deleteBook);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setBounds(500, 250, 350, 200);
        frame.setResizable(false);

        panel = new JPanel();
        panel.setLayout(null);
        frame.add(panel);

        new Label(10, 30, 50, 25, Lang.bookTitle, panel);
        new Label(10, 70, 50, 25, Lang.bookAuthor, panel);

        field1 = new TextField(70, 30, 200, 25, 25, panel);
        field2 = new TextField(70, 70, 200, 25, 25, panel);

        button = new Button(120, 120, 100, 25, Lang.confirm, panel);
        button.getObject().addActionListener((ActionEvent e) -> {

            Book deletedBook = Book.deleteBook(field1.getObject().getText(), field2.getObject().getText());
            if (deletedBook != null) {
                JOptionPane.showMessageDialog(frame, "Book deleted successfully: " + deletedBook.getTitle());
                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(frame, "Book not found.");
            }

        });

        frame.setVisible(true);
    }
}
