package database_system;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import entities.book.Book;
import entities.rating.Rating;
import entities.review.Review;
import entities.user_and_admin.User;
import program_settings.Parametres;

public class RatingDataBase {

     public static void addRating(User user, Book book, int content){
        Rating.createRating(user.getUsername(), book.getTitle(), content);
        
        File file = new File(Parametres.RATING_PATH + "rating" + Rating.getGeneralIndex() + Parametres.FILE_FORMAT);

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))){
            file.createNewFile();

            bw.write(user.getUsername() + "|" + book.getTitle() + "|" + content);
        }catch(IOException e){
            System.out.println("Error while creating a review");
        }
    }

    // removes review from reviews folder
    public static void removeRating(int ratingIndex){

        File rating_folder = new File(Parametres.RATING_PATH);
        for(File file : rating_folder.listFiles()){
            if(file.getName().equals("Rating" + ratingIndex)){
                file.delete();
                return;
            }
        }
    }

    // Finds review in the reviews folder
    public static Review findRating(int reviewIndex){

        File review_folder = new File(Parametres.REVIEW_PATH);
        for(File file : review_folder.listFiles()){
            if(file.getName().equals("review" + reviewIndex)){
                return fileToRating(file);
            }
        }
        return null;
    }

    // Converts file data to Review object
    static Review fileToRating(File file){

        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            
            String data[] = br.readLine().split("|", -1);

            return Review.createReview(data[0], data[1], data[2]);
        } catch (Exception e) {
        }

        return null;
    }


    // This method will return specific data of review
    private static String getRatingData(Review r, int dataIndex){

        try(BufferedReader br = new BufferedReader(new FileReader(Parametres.REVIEW_PATH + "review" + r.getIndex() + Parametres.FILE_FORMAT));){

            String line = br.readLine();

            String[] data = line.split("|", -1);

            return  data[dataIndex];
        }catch(IOException ex){

        }

        return null;
    }

    // Return user by whom review was written
    static String getRatingAuthor(Review r){
        return getRatingData(r, 0);
    }

    // Return book that was reviewed
    static String getRatingBook(Review r){
        return getRatingData(r, 1);
    }

    // Return content of the review
    static String getRatingContent(Review r){
        return getRatingData(r, 2);
    }
}
