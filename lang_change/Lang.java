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
    public static String generalDatabase;
    public static String bookTitle;
    public static String bookAuthor;
    public static String bookRating;
    public static String bookReviews;
    public static String noReviews;
    public static String noRating;

    //Personal Database
    public static String personalDatabase;
    public static String bookStatus;
    public static String bookSpentTime;
    public static String bookStartDate;
    public static String bookEndDate;
    public static String userReview;
    public static String userRating;
    public static String notStarted;
    public static String bookAlreadyAdded;
    
    // Database functionalities
    public static String bookAdd;
    public static String bookAdded;
    public static String removeBookFromPersonal;
    public static String addBook;
    public static String addedBook;
    public static String addReview;
    public static String addRating;
    public static String search;

    // Admin
    public static String editBook;
    public static String addNewBook;
    public static String deleteBook;
    public static String deleteUser;
    public static String removeReview;
    public static String adminIsReserved;
    public static String operation;

    // File errors
    public static String loadFailed;
    public static String readingFailed;
    public static String error;

    // User
    public static String logOut;
    
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
            
            // Login Register Page
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

            // Login Register Exceptions
            wrongUsernameOrPassword = bf.readLine();
            existingUsername = bf.readLine();
            passwordsUnmatch = bf.readLine();
            illegalPasswordLength = bf.readLine();
            illegalUsernameLength = bf.readLine();

            bf.readLine();

            // Table page
            tableTitle = bf.readLine();
            tableTitleAdmin = bf.readLine();

            bf.readLine();

            // General Database
            generalDatabase = bf.readLine();
            bookTitle = bf.readLine();
            bookAuthor = bf.readLine();
            bookRating = bf.readLine();
            bookReviews = bf.readLine();
            noReviews = bf.readLine();
            noRating = bf.readLine();

            bf.readLine();

            // Personal Database
            personalDatabase = bf.readLine();
            bookStatus = bf.readLine();
            bookSpentTime = bf.readLine();
            bookStartDate = bf.readLine();
            bookEndDate = bf.readLine();
            userReview = bf.readLine();
            userRating = bf.readLine();
            notStarted = bf.readLine();
            bookAlreadyAdded = bf.readLine();

            bf.readLine();

            // Database functionalities
            bookAdd = bf.readLine();
            bookAdded = bf.readLine();
            removeBookFromPersonal = bf.readLine();
            addBook = bf.readLine();
            addedBook = bf.readLine();
            addReview = bf.readLine();
            addRating = bf.readLine();
            search = bf.readLine();

            bf.readLine();
            
            // Admin
            editBook = bf.readLine();
            addNewBook = bf.readLine();
            deleteBook = bf.readLine();
            deleteUser = bf.readLine();
            removeReview = bf.readLine();
            adminIsReserved = bf.readLine();
            operation = bf.readLine();

            bf.readLine();

            // File errors
            loadFailed = bf.readLine();
            readingFailed = bf.readLine();
            error = bf.readLine();

            bf.readLine();

            // User
            logOut = bf.readLine();

        } catch (Exception e) {
        }
    }
}
