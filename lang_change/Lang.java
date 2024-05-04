package lang_change;

import java.io.BufferedReader;
import java.io.FileReader;

public class Lang {

    // This will contain path of language file
    static String readDataFrom;

    // They will contain words in different languages
    
    // Login Register Page
    public static String loginTitle;
    public static String registerTitle;
    public static String usernameLabel;
    public static String passwordLabel;
    public static String repeatPasswordLabel;
    public static String haveAccount;
    public static String dontHaveAccount;
    public static String registerHere;
    public static String loginHere;

    // Login Register Exceptions
    public static String wrongUsernameOrPassword;
    public static String existingUsername;
    public static String passwordsUnmatch;
    public static String illegalPasswordLength;
    public static String illegalUsernameLength;

    // Table Page
    public static String tableTitle;
    public static String tableTitleAdmin;

    // General Database
    public static String bookTitle;
    public static String bookAuthor;
    public static String bookRating;
    public static String bookReviews;

    //Personal Database
    public static String bookStatus;
    public static String bookStartDate;
    public static String bookEndDate;
    
    // Database functionalities
    public static String addBookToPersonal;
    public static String removeBookFromPersonal;

    // Admin
    public static String addBook;
    public static String removeBook;
    public static String deleteUser;
    public  static String deleteReview;
    
    protected static void change(Language lang){

        switch (lang) {
            case Language.AZE:
                readDataFrom = "./lang_change/azerbaijani.csv";
                break;
            case Language.ENG:
                readDataFrom = "./lang_change/english.csv";
                break;
            default:
                readDataFrom = "./lang_change/english.csv";
        }

        
        // Reading data and assigning strings
        try(BufferedReader bf = new BufferedReader(new FileReader(readDataFrom))){
            
            loginTitle = bf.readLine();
            registerTitle = bf.readLine();
            usernameLabel = bf.readLine();
            passwordLabel = bf.readLine();
            repeatPasswordLabel = bf.readLine();
            haveAccount = bf.readLine();
            dontHaveAccount = bf.readLine();
            registerHere = bf.readLine();
            loginHere = bf.readLine();

            bf.readLine();

            wrongUsernameOrPassword = bf.readLine();
            existingUsername = bf.readLine();
            passwordsUnmatch = bf.readLine();
            illegalPasswordLength = bf.readLine();
            illegalUsernameLength = bf.readLine();

            bf.readLine();

            tableTitle = bf.readLine();
            tableTitleAdmin = bf.readLine();

            bf.readLine();

            bookTitle = bf.readLine();
            bookAuthor = bf.readLine();
            bookRating = bf.readLine();
            bookReviews = bf.readLine();

            bf.readLine();

            bookStatus = bf.readLine();
            bookStartDate = bf.readLine();
            bookEndDate = bf.readLine();

            bf.readLine();

            addBookToPersonal = bf.readLine();
            removeBookFromPersonal = bf.readLine();

            bf.readLine();
            
            addBook = bf.readLine();
            removeBook = bf.readLine();
            deleteUser = bf.readLine();
            deleteReview = bf.readLine();

        } catch (Exception e) {
        }
    }
}
