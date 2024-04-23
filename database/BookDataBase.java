package database;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.TreeMap;

import entities.book.Book;
import program_settings.Parametres;

public class BookDataBase {

    private static TreeMap<String, Book> bookMap = new TreeMap<String, Book>();

    public static void loadData() {

        File userFolder = new File(Parametres.BOOK_PATH);

        // Add all files form users folder to the map
        for (File file : userFolder.listFiles()) {
            bookMap.put(file.getName(), Book.readBook(file));
        }
    }

    public static void add(Book book) {
        try {
            // Create new file for storing user data
            File newUser = new File(Parametres.BOOK_PATH + book.getTitle() + Parametres.FILE_FORMAT);
            newUser.createNewFile();

            // Write user data to the new file
            FileOutputStream fos = new FileOutputStream(newUser);
            fos.write((book.getTitle() + ";" + book.getAuthor()).getBytes());

            // Add new user to the map
            bookMap.put(book.getTitle(), book);

            fos.close();

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void remove(String title) {
        if (isInMap(title)) {
            bookMap.remove(title);

            File file = new File(Parametres.BOOK_PATH + title + Parametres.FILE_FORMAT);
            file.delete();
        }

        System.out.println("There is no such user in the map");
    }

    public static boolean isInMap(String title) {
        return bookMap.get(title) == null ? false : true;
    }

    public static Book getMember(String title) {
        return bookMap.get(title);
    }
}
