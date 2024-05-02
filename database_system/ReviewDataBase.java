package database_system;

import entities.book.Book;
import entities.review.Review;
import entities.user_and_admin.User;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import program_settings.Parametres;

public class ReviewDataBase {

    public static void addReview(User user, Book book, String content){
        Review.createReview(user.getUsername(), book.getTitle(), content);
        
        File file = new File(Parametres.REVIEW_PATH + "review" + Review.getGeneralIndex() + Parametres.FILE_FORMAT);

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))){
            file.createNewFile();

            bw.write(user.getUsername() + "|" + book.getTitle() + "|" + content);
        }catch(IOException e){
            System.out.println("Error while creating a review");
        }
    }

    public static void removeReview(Review r){

        File review_folder = new File(Parametres.REVIEW_PATH);
        for(File file : review_folder.listFiles()){
            if(file.getName().equals("review" + r.getIndex())){
                file.delete();
                return;
            }
        }
    }

    public static Review findReview(String r){

        File review_folder = new File(Parametres.REVIEW_PATH);
        for(File file : review_folder.listFiles()){
            if(file.getName().equals(r)){
                return fileToReview(file);
            }
        }
        return null;
    }

    static Review fileToReview(File file){

        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            
            String data[] = br.readLine().split("|", -1);

            return Review.createReview(data[0], data[1], data[2]);
        } catch (Exception e) {
        }

        return null;
    }
}
