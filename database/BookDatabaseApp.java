import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookDatabaseApp {
    private JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;
    private GeneralDatabase generalDb;

    public BookDatabaseApp() {
        frame = new JFrame("General Database");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        tableModel = new DefaultTableModel(new String[]{"Title", "Author", "Rating", "Reviews"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setVisible(true);

        generalDb = new GeneralDatabase();
    }

    public void addBookToTable(Book book) {
        tableModel.addRow(new Object[]{book.getTitle(), book.getAuthor(),
                book.getRating() == -1 ? "No rating" : String.format("%.2f", book.getRating()),
                book.getReviewCount() == 0 ? "No review" : book.getReviewCount()});
    }

    public static void main(String[] args) {
        BookDatabaseApp app = new BookDatabaseApp();

        // Sample data
        Book book1 = new Book("Don Quixote", "Cervantes");
        book1.addReview("Ferdinandbəy", 4);
        app.addBookToTable(book1);
    }
}

class Book {
    private String title;
    private String author;
    private double rating;
    private List<String> reviews;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.rating = -1; // default value
        this.reviews = new ArrayList<>();
    }

    public void addReview(String user, double rating) {
        reviews.add(user);
        this.rating = calculateAverageRating(rating);
    }

    private double calculateAverageRating(double newRating) {
        double totalRating = rating * (reviews.size() - 1);
        return (totalRating + newRating) / reviews.size();
    }

    public String getTitle() {
        return title; }

    public String getAuthor() {
        return author;
    }

    public double getRating() {
        return rating;
    }

    public int getReviewCount() {
        return reviews.size();
    }
}

class GeneralDatabase {
    private List<Book> books;

    public GeneralDatabase() {
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public List<Book> getBooks() {
        return books;
    }
}

class PersonalDatabase {
    private Book book;
    private String status;
    private int timeSpent;
    private Date startDate;
    private Date endDate;
    private List<UserReview> userReviews;

    // Constructors, getters, setters, and methods
}

class UserReview {
    private Date date;
    private int userRating;
    private String userReview;

    // Constructors, getters, setters, and methods
}