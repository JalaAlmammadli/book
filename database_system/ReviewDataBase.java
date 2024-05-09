package database_system;

import entities.book.Book;
import entities.other.ControlOpinion;
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
        Review.createReview(user.getUsername(), book.getTitle(), book.getAuthor(), content);
        
        File file = new File(Parametres.REVIEW_PATH + "review" + Review.getGeneralIndex() + Parametres.FILE_FORMAT);

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))){
            file.createNewFile();

            bw.write(user.getUsername() + "|||" + book.getTitle() + "_" + book.getAuthor() + "|||" + content);
        }catch(IOException e){
            System.out.println("Error while creating a review");
        }
    }

    // removes review from reviews folder
    public static void removeReview(int reviewIndex){

        ControlOpinion.deleteReviewFromEntity(reviewIndex, Parametres.USER_REVIEW_PATH + getReviewAuthor(reviewIndex) + Parametres.FILE_FORMAT);
        ControlOpinion.deleteReviewFromEntity(reviewIndex, Parametres.BOOK_REVIEW_PATH + getReviewBook(reviewIndex) + Parametres.FILE_FORMAT);

        File review_folder = new File(Parametres.REVIEW_PATH);
        for(File file : review_folder.listFiles()){
            if(file.getName().equals("review" + reviewIndex)){

                file.delete();
                return;
            }
        }
    }

    // Finds review in the reviews folder
    public static Review findReview(int reviewIndex){

        File review_folder = new File(Parametres.REVIEW_PATH);
        for(File file : review_folder.listFiles()){
            if(file.getName().equals("review" + reviewIndex)){
                return fileToReview(file);
            }
        }
        return null;
    }

    // Converts file data to Review object
    static Review fileToReview(File file){

        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            
            String data[] = br.readLine().split("|||", -1);
            int index = Integer.parseInt(file.getName().split("review").toString().split(".txt").toString());

            return Review.readReview(data[0], data[1], data[2], index);
        } catch (Exception e) {
        }

        return null;
    }


    // This method will return specific data of review
    private static String getReviewData(int review_index, int dataIndex){

        try(BufferedReader br = new BufferedReader(new FileReader(Parametres.REVIEW_PATH + "review" + review_index + Parametres.FILE_FORMAT));){

            String line = br.readLine();
            String[] data = line.split("|||", -1);
            return  data[dataIndex];
        }catch(IOException ex){
        }
        return null;
    }


    // Return user by whom review was written
    public static String getReviewAuthor(int review_index){
        return getReviewData(review_index, 0);
    }

    // Return book(Title and author) that was reviewed
    public static String[] getReviewBook(int review_index){
        return getReviewData(review_index, 1).split("_");
    }

    // Return content of the review
    public static String getReviewContent(int review_index){
        return getReviewData(review_index, 2);
    }
}
