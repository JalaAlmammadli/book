package entities.book;

import java.util.ArrayList;

public class BasicBook {

    private String name;
    private String author;
    private double totalRating;

    public BasicBook(String movieName, String author) {
        setName(movieName);
        setAuthor(author);
    }

    public BasicBook(String movieName) {
        setName(movieName);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.equals("")) {
            this.name = "Unknown";
        } else if (name.length() < 64) {
            this.name = name;
        }

    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        if (author == null) {
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
