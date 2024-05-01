package entities.review;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import entities.other.UserOpinion;

public class Review extends UserOpinion {

    private int index;
    private static int usedIndex;
    private String content;

    public Review(String user, String book, String content) {
        super(user, book);
        this.index = usedIndex++;
        setContent(content);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String newContent) {
        if (newContent != null) {
            this.content = newContent;
        }
    }

    public static int getIndex() {
        return usedIndex;
    }

    public static void setIndex(int newIndex) {
        if (newIndex >= 0) {
            usedIndex = newIndex;
        }
    }

    public static void writeLastIndex() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("./program_settings/review_index.txt"))) {
            bw.write(usedIndex);

        } catch (IOException e) {
            System.out.println("error during writing last review index");
        }
    }

    public static void readLastIndex() {
        try (BufferedReader br = new BufferedReader(new FileReader("./program_settings/review_index.txt"))) {

            usedIndex = Integer.parseInt(br.readLine());

        } catch (IOException e) {
            System.out.println("error during writing last review index");
        }
    }
}
