
import java.util.ArrayList;

public class Book {
    String title;
    String author;
    double rating;
    ArrayList<String> reviews;

    public Book(String title, String author, double rating, ArrayList<String> reviews) {
        this.title = title;
        this.author = author;
        this.rating = rating;
        this.reviews = reviews;
    }

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.rating = -1; 
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
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public ArrayList<String> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<String> reviews) {
        this.reviews = reviews;
    }
    

}
