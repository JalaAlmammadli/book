package database;

import java.util.TreeMap;

import entities.book.BasicBook;

public class BookDataBase {

    private static TreeMap<String, BasicBook> bookMap = new TreeMap<String, BasicBook>();

    public static void add(BasicBook book) {
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

    public static BasicBook getMember(String name) {
        return bookMap.get(name);
    }
}
