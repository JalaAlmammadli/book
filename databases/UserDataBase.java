package databases;

import java.util.HashMap;
import user_and_admin.User;

// This class is used for holding User oblects
public class UserDataBase {

    // All users will be contained in this HashMap, and its key will be user's
    // username and User object itself
    // *(For now value just conatins user's password)
    private static HashMap<String, String> user_map = new HashMap<String, String>();

    public static void add(String username, String password) {
        if (username != null || password != null) {
            user_map.put(username, password);
        }
    }

    public static void add(User obj) {
        user_map.put(obj.getUsername(), obj.getPassword());
    }

    // public void remove(User obj) {
    // user_map.remove(obj);
    // }

    // This method removes user from user_map by its username
    public static void remove(String username) {
        if (UserDataBase.isInMap(username)) {
            user_map.remove(username);
        }

        System.out.println("There is no such user in the map");
    }

    // public User search(String username) {
    // if (user_map.get(username) == null) {
    // return false;
    // }

    // return true;
    // }

    // returns true if user with username "some username"
    // exits in the user_map
    public static boolean isInMap(String username) {
        if (user_map.get(username) == null) {
            return false;
        }

        return true;
    }

    /*
     * This method checks user for login class.
     * It first will check if user with entered username exists.
     * If user with this username does not exist it will suggest user to register.
     * But, if user with endered username exists, it will check if entered password
     * for this username is correct. If yes user Successfully logined, else
     * it will throw WrongPasswordException.
     */
    public static boolean checkUserForLogin(String username, String password) {
        if (user_map.get(username) == null) {
            System.out.println("There is no such user. You can register");
            return false;
        }

        else if (user_map.get(username).equals(password)) {
            return true;
        }

        return false;
    }
}
