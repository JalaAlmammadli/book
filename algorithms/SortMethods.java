package algorithms;

import entities.book.Book;
import java.util.ArrayList;

class SortMethods extends Comparators {

    public static ArrayList<Book> sortTitleAscending(ArrayList<Book> list) {

        list.sort(titleAscendingComparator);
        return list;
    }

    public static ArrayList<Book> sortTitleDescending(ArrayList<Book> list){

        list.sort(titleDescendingComparator);
        return list;
    }

    public static ArrayList<Book> sortAuthorAscending(ArrayList<Book> list) {

        list.sort(authorAscendingComparator);
        return list;
    }

    public static ArrayList<Book> sortAuthorDescending(ArrayList<Book> list){
        
        list.sort(authorDescendingComparator);
        return list;
    }

    public static ArrayList<Book> sortRatingAscending(ArrayList<Book> list){
        return list;
    }

    public static ArrayList<Book> sortRatingDescending(ArrayList<Book> list){
        return list;
    }

    public static ArrayList<Book> sortDateAscending(ArrayList<Book> list){
        return list;
    }

    public static ArrayList<Book> sortDateDescending(ArrayList<Book> list){
        return list;
    }
}
