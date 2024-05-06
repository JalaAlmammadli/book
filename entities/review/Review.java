package entities.review;

import entities.other.UserOpinion;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import program_settings.Parametres;

public class Review extends UserOpinion {

    private int index;
    private static int generalIndex;
    private String content;

    Review(String user, String book, String content) {
        super(user, book);
        this.index = ++generalIndex;
        setContent(content);
    }

    Review(String user, String book, String content, int index) {
        super(user, book);
        this.index = index;
        setContent(content);
    }

    public static Review createReview(String username, String title, String content){
        Review review = new Review(username, title, content);

        write(review, Parametres.USER_REVIEW_PATH + username + Parametres.FILE_FORMAT);
        write(review, Parametres.BOOK_REVIEW_PATH + title + Parametres.FILE_FORMAT);

        return review;
    }

    private static void write(Review review, String path){

        try {

            BufferedReader br = new BufferedReader(new FileReader(path));
            String reviews = br.readLine();

            FileWriter writer = new FileWriter(path);

            if(reviews == null){
                writer.append(review.getIndex() + " ");
            }
            else{
                writer.append(reviews + review.getIndex() + " ");
            }
            br.close();
            writer.close(); 
        } catch (IOException e) {
        }
    }

    public static Review readReview(String username, String title, String content, int index){
        
        return new Review(username, title, content, index);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String newContent) {
        if (newContent != null) {
            this.content = newContent;
        }
    }

    public int getIndex(){
        return index;
    }

    public static int getGeneralIndex() {
        return generalIndex;
    }

    public static void setGeneralIndex(int newIndex) {
        if (newIndex >= 0) {
            generalIndex = newIndex;
        }
    }

    public static void writeLastIndex() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("./program_settings/review_index.txt"))) {
            bw.write(generalIndex);

        } catch (IOException e) {
            System.out.println("error during writing last review index");
        }
    }

    public static void readLastIndex() {
        try (BufferedReader br = new BufferedReader(new FileReader("./program_settings/review_index.txt"))) {

            generalIndex = Integer.parseInt(br.readLine());

        } catch (IOException e) {
            System.out.println("error during writing last review index");
        }
    }
}
