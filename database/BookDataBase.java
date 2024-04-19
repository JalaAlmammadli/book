package database;

import java.util.TreeMap;

import book.Book;

public class BookDataBase {

    private static TreeMap<String, Book> bookMap = new TreeMap<String, Book>();

    public static void add(Book book) {
        bookMap.put(book.getName(), book);
    }

    public static void remove(String name) {
        if (isInMap(name)) {
            bookMap.remove(name);
        }
    }

    public static boolean isInMap(String name) {
        return bookMap.get(name) == null ? false : true;
    }

    public static Book getMember(String name) {
        return bookMap.get(name);
    }
}
