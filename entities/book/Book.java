package entities.book;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Book extends AbstractWork {

    private Book(String bookTitle, String author) {
        super(bookTitle, author);
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

    public static Book createBook(String bookTitle, String author) {
        return new Book(bookTitle, author);
    }
}
