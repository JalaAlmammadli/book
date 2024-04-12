/*


 * Created by Orkhan
 * 
 * 
 */

package database;

import java.util.HashMap;
import user_and_admin.User;

// This class is used for holding User oblects
public class UserDataBase implements DataBaseInterface<User> {

    // All users will be contained in this HashMap, and its key will be user's
    // username and User object itself
    // *(For now value just conatins user's password)
    private HashMap<String, User> user_map;

    // There will be only one exemplare of UserDataBase class
    public static UserDataBase UserDataBase1 = new UserDataBase();

    private UserDataBase() {
        user_map = new HashMap<String, User>();
    }

    public void add(User obj) {
        user_map.put(obj.getUsername(), obj);
    }

    // public void remove(User obj) {
    // user_map.remove(obj);
    // }

    // This method removes user from user_map by its username
    public void remove(String username) {
        if (UserDataBase1.isInMap(username)) {
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
    public boolean isInMap(String username) {
        if (user_map.get(username) == null) {
            return false;
        }

        return true;
    }

    // This method will return user
    public User getMember(String username) {
        return user_map.get(username);
    }

    /*
     * This method checks user for login class.
     * It first will check if user with entered username exists.
     * If user with this username does not exist it will suggest user to register.
     * But, if user with endered username exists, it will check if entered password
     * for this username is correct. If yes user Successfully logined, else
     * it will throw WrongPasswordException.
     */
    public boolean checkUserForLogin(String username, String password) {
        if (user_map.get(username) == null) {
            System.out.println("There is no such user. You can register");
            return false;
        }

        else if (user_map.get(username).getPassword().equals(password)) {
            return true;
        }

        return false;
    }
}
