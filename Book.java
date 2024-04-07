import java.util.List;

public class Book {
    private String title;
    private String author;
    private double rating;
    private int reviewCount;
    private List<String> reviewers;

    public Book(String title, String author, double rating, int reviewCount, List<String> reviewers) {
        this.title = title;
        this.author = author;
        this.rating = rating;
        this.reviewCount = reviewCount;
        this.reviewers = reviewers;
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

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public List<String> getReviewers() {
        return reviewers;
    }

    public void setReviewers(List<String> reviewers) {
        this.reviewers = reviewers;
    }
}
