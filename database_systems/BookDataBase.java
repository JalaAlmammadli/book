package database_systems;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

import database_systems.exceptions.IllegalMemberException;
import entities.book.Book;
import program_settings.Parametres;

public class BookDataBase {

    private static ArrayList<Book> bookList = new ArrayList<Book>();
    private static ArrayList<String> titleList = new ArrayList<>();

    public static void loadData() {

        try (BufferedReader br = new BufferedReader(new FileReader("./data/book_list.csv"))) {

            String line;
            while ((line = br.readLine()) != null) {
                String data[] = line.split(";");
                bookList.add(Book.readBook(data[0], data[1]));
                titleList.add(data[0]);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void writeData() {

        new File("./data/book_list.csv").delete();

        try {
            File newFile = new File("./data/book_list.csv");
            newFile.createNewFile();

            BufferedWriter bw = new BufferedWriter(new FileWriter("./data/book_list.csv"));

            for (int i = 0; i < titleList.size(); i++) {
                Book b = bookList.get(i);
                bw.write(b.getTitle() + ";" + b.getAuthor());
                bw.newLine();
            }

            bw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void add(Book book) throws IllegalMemberException {

        // if (contains(book.getTitle())) {
        // throw new IllegalMemberException("User with username " + book.getTitle() + "
        // already exists");
        // }

        bookList.add(book);
        titleList.add(book.getTitle());
    }

    // This method removes user from user_map by its username
    public static void remove(String title) {
        if (contains(title)) {

            bookList.remove(bookList.get(titleList.indexOf(title)));
        }

        System.out.println("There is no such user in the map");
    }

    public static boolean contains(String title) {
        return titleList.contains(title) ? true : false;
    }

    // This method will return user
    public static Book getMember(String title) {

        return bookList.get(titleList.indexOf(title));
    }

    public static int size() {
        return bookList.size();
    }
}
