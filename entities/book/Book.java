package entities.book;

import database_system.BookDataBase;
import entities.other.ControlOpinion;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import program_settings.Parametres;

public class Book extends AbstractWork {

    private Book(String bookTitle, String author) {
        super(bookTitle, author);

        try {
        } catch (Exception e) {
        }
    }

    public static Book readBook(File file) {

        try (BufferedReader br = new BufferedReader(new FileReader(file.getPath()))) {

            String data[] = br.readLine().split(";", -1);

            return new Book(data[0], data[1]);
        } catch (IOException e) {
            System.out.println(e);
        }

        return null;
    }

    public int[] getAllReviews(){
        return ControlOpinion.getAllOpinion(Parametres.BOOK_REVIEW_PATH + super.title + Parametres.FILE_FORMAT);
    }

    public static Book readBook(String title, String author) {

        return new Book(title, author);
    }

    public static Book createBook(String bookTitle, String author) {
        Book book = new Book(bookTitle, author);
        return book;
    }

    public static Book deleteBook(String bookTitle, String author) {
        return BookDataBase.MainBookList.deleteBook(bookTitle, author);
    }


}
