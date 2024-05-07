package database_system;

import entities.book.Book;
import entities.rating.Rating;
import entities.user_and_admin.User;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import program_settings.Parametres;

public class RatingDataBase {

     public static void addRating(User user, Book book, double rate){
        Rating.createRating(user.getUsername(), book.getTitle(), rate);
        
        File file = new File(Parametres.RATING_PATH + "rating" + Rating.getGeneralIndex() + Parametres.FILE_FORMAT);

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))){
            file.createNewFile();

            bw.write(user.getUsername() + "|" + book.getTitle() + "|" + rate);
        }catch(IOException e){
            System.out.println("Error while creating a rating");
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
    public static Rating findRating(int ratingIndex){

        File review_folder = new File(Parametres.REVIEW_PATH);
        for(File file : review_folder.listFiles()){
            if(file.getName().equals("rating" + ratingIndex)){
                return fileToRating(file);
            }
        }
        return null;
    }

    // Converts file data to Review object
    static Rating fileToRating(File file){

        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            
            String data[] = br.readLine().split("|", -1);

            return Rating.createRating(data[0], data[1], Double.parseDouble(data[2]));
        } catch (Exception e) {
        }

        return null;
    }


    // This method will return specific data of review
    private static String getRatingData(Rating r, int dataIndex){

        try(BufferedReader br = new BufferedReader(new FileReader(Parametres.REVIEW_PATH + "rating" + r.getIndex() + Parametres.FILE_FORMAT));){

            String line = br.readLine();

            String[] data = line.split("|", -1);

            return  data[dataIndex];
        }catch(IOException ex){

        }

        return null;
    }

    // Return user by whom review was written
    static String getRatingAuthor(Rating r){
        return getRatingData(r, 0);
    }

    // Return book that was reviewed
    static String getRatingBook(Rating r){
        return getRatingData(r, 1);
    }

    // Return content of the review
    static String getRatingContent(Rating r){
        return getRatingData(r, 2);
    }
}
