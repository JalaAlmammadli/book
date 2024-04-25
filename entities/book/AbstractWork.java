package entities.book;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public abstract class AbstractWork {

    private String title;
    private String author;
    private double totalRating;
    protected static int index;
    protected int workIndex;

    {
        try (BufferedReader bf = new BufferedReader(new FileReader("./program_settings/book_index.txt"))) {

            index = Integer.parseInt(bf.readLine());
        } catch (IOException e) {
        }
    }

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

    public int getIndex() {
        return workIndex;
    }

    protected void setIndex(int index) {
        workIndex = index;
    }

    public void writeLastIndex() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("./program_settings/book_index.txt"))) {

        } catch (IOException e) {
            System.out.println("error during writing last books index");
        }
    }
}
