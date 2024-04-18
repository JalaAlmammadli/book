package movie;

import java.util.ArrayList;

public class Movie {

    private String name;
    private String author;
    private double totalRating;

    private ArrayList<Double> rates = new ArrayList<Double>();
    private ArrayList<String> reviews = new ArrayList<String>();

    public Movie(String movieName, String author) {
        setName(movieName);
        setAuthor(author);
    }

    public Movie(String movieName) {
        setName(movieName);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.length() < 64) {
            this.name = name;
        }
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        if (author.length() < 64) {
            this.author = author;
        }
    }
}
