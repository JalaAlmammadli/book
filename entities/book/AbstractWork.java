package entities.book;

public abstract class AbstractWork {

    private String title;
    private String author;
    private double totalRating;

    public AbstractWork(String title, String author) {
        setTitle(title);
        setAuthor(author);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.strip().equals("")) {
            this.title = "Unknown";
        } else if (title.length() < 64) {
            this.title = title;
        }

    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        if (author == null || author.strip().equals("")) {
            this.author = "Unknown";
        }
        if (author.length() < 64) {
            this.author = author;
        }
    }

    public double getRating() {
        return totalRating;
    }

    public void setRating(double totalRating) {
        this.totalRating = totalRating;
    }
}
