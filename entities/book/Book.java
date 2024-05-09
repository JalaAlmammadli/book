package entities.book;

import database_system.BookDataBase;
import database_system.RatingDataBase;
import entities.other.ControlOpinion;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import program_settings.Parametres;

public class Book extends AbstractWork {

    private Book(String bookTitle, String author) {
        super(bookTitle, author);

        try {
        } catch (Exception e) {
        }
    }

    public static Book readBook(File file) {

        try (BufferedReader br = new BufferedReader(new FileReader(file.getPath()))) {

            String data[] = br.readLine().split(";", -1);

            return new Book(data[0], data[1]);
        } catch (IOException e) {
            System.out.println(e);
        }

        return null;
    }

    public int[] getAllReviews(){
        return ControlOpinion.getAllOpinion(Parametres.BOOK_REVIEW_PATH + super.title + Parametres.FILE_FORMAT);
    }

    public static Book readBook(String title, String author) {

        return new Book(title, author);
    }

    public static Book createBook(String bookTitle, String author) {
        Book book = new Book(bookTitle, author);
        return book;
    }

    public static Book deleteBook(String bookTitle, String author) {
        return BookDataBase.MainBookList.deleteBook(bookTitle, author);
    }

    public double countTotalRating(){

        double count = 0;
        int ratings[] = readRatings();

        if(ratings == null) return 0;
        
        for(int rating : ratings){
            count += RatingDataBase.getRatingContent(rating);
        }

        return count/ratings.length;
    }

    private int[] readRatings(){
        try(BufferedReader br = new BufferedReader(new FileReader(Parametres.BOOK_RATING_PATH + getTitle() + "_" + getAuthor() + Parametres.FILE_FORMAT))){

            String ratings[] = br.readLine().split(" ");
            int rating_indexes[] = new int[ratings.length];

            for(int i = 0; i < ratings.length; i++){
                rating_indexes[i] = Integer.parseInt(ratings[i]);
            }

            return rating_indexes;
        }catch(IOException e){

        }

        return null;
    }
}
