package algorithms;

import java.util.Comparator;

import entities.book.Book;

class Comparators {
    
    static Comparator<Book> titleAscendingComparator = new Comparator<Book>() {

        @Override
        public int compare(Book b1, Book b2) {
            if (b1.getTitle().compareTo(b2.getTitle()) == 1) {
                return 1;
            }
            return -1;
        }
    };
    static Comparator<Book> titleDescendingComparator = new Comparator<Book>() {

        @Override
        public int compare(Book b1, Book b2) {
            if (b1.getTitle().compareTo(b2.getTitle()) == 1) {
                return -1;
            }
            return 1;
        }
    };

    static Comparator<Book> authorAscendingComparator = new Comparator<Book>() {

        @Override
        public int compare(Book b1, Book b2) {
            if (b1.getAuthor().compareTo(b2.getAuthor()) == 1) {
                return 1;
            }
            return -1;
        }
    };
    static Comparator<Book> authorDescendingComparator = new Comparator<Book>() {

        @Override
        public int compare(Book b1, Book b2) {
            if (b1.getAuthor().compareTo(b2.getAuthor()) == 1) {
                return -1;
            }
            return 1;
        }
    };
}
