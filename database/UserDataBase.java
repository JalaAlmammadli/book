/*


 * Created by Orkhan
 * 
 * 
 */

package database;

import java.util.HashMap;
import java.util.TreeMap;

import database.exceptions.IllegalMemberException;
import entities.user_and_admin.User;
import entities.user_and_admin.UserDataFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import program_settings.Parametres;

// This class is used for holding User oblects
public class UserDataBase {

    // All users will be contained in this HashMap, and its key will be user's
    // username and User object itself
    // *(For now value just conatins user's password)
    private static TreeMap<String, User> userMap = new TreeMap<>();

    // There will be only one exemplare of UserDataBase class

    public static void loadData() {

        File userFolder = new File(Parametres.USER_PATH);

        // Add all files form users folder to the map
        for (File file : userFolder.listFiles()) {
            userMap.put(file.getName(), User.ReadUser(file));
        }
    }

    // private static void listFiles() {

    // UserDataFile userFolder = new UserDataFile("../data/users");

    // // Add all files form users folder to the map
    // for (UserDataFile file : userFolder.listFiles()) {
    // userMap.put(file.getName(), file);
    // }
    // }

    public static void add(User user) {
        try {
            // Create new file for storing user data
            File newUser = new File(Parametres.USER_PATH + user.getUsername() + Parametres.FILE_FORMAT);
            newUser.createNewFile();

            // Write user data to the new file
            FileOutputStream fos = new FileOutputStream(newUser);
            fos.write((user.getUsername() + ";" + user.getPassword()).getBytes());

            // Add new user to the map
            userMap.put(user.getUsername(), user);

            fos.close();

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    // This method removes user from user_map by its username
    public static void remove(String username) {
        if (isInMap(username)) {
            userMap.remove(username);

            File file = new File(Parametres.USER_PATH + username + Parametres.FILE_FORMAT);
            file.delete();
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
        if (userMap.get(username) == null) {
            return false;
        }

        return true;
    }

    // This method will return user
    public static User getMember(String username) throws IllegalMemberException {

        // Creates a user according to read file data
        return User.ReadUser(username);
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
        if (userMap.get(username) == null) {
            System.out.println("There is no such user. You can register");
            return false;
        }

        else if (userMap.get(username).getPassword() == password.hashCode()) {
            return true;
        }

        return false;
    }
}