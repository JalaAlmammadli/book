/*


 * Created by Orkhan
 * 
 * 
 */

package database_systems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import database_systems.exceptions.IllegalMemberException;
import entities.user_and_admin.User;
import entities.user_and_admin.UserDataFile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
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
    private static ArrayList<User> userList = new ArrayList<>();
    private static ArrayList<String> usernameList = new ArrayList<>();

    // There will be only one exemplare of UserDataBase class

    public static void loadData() {

        try (BufferedReader br = new BufferedReader(new FileReader("./data/user_list.csv"))) {

            String line;
            while ((line = br.readLine()) != null) {
                String data[] = line.split(";");
                userList.add(User.readUser(data[0], Integer.parseInt(data[1])));

                usernameList.add(data[0]);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void writeData() {

        new File("./data/user_list.csv").delete();

        try {
            File newFile = new File("./data/user_list.csv");
            newFile.createNewFile();

            BufferedWriter bw = new BufferedWriter(new FileWriter("./data/user_list.csv"));

            for (int i = 0; i < usernameList.size(); i++) {
                User u = userList.get(i);
                bw.write(u.getUsername() + ";" + u.getPassword());
                bw.newLine();
            }

            bw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    // private static void listFiles() {

    // UserDataFile userFolder = new UserDataFile("../data/users");

    // // Add all files form users folder to the map
    // for (UserDataFile file : userFolder.listFiles()) {
    // userMap.put(file.getName(), file);
    // }
    // }

    public static void add(User user) throws IllegalMemberException {

        if (contains(user.getUsername())) {
            throw new IllegalMemberException("User with username " + user.getUsername() + " already exists");
        }

        userList.add(user);
        usernameList.add(user.getUsername());
    }

    // This method removes user from user_map by its username
    public static void remove(String username) {
        if (contains(username)) {

            userList.remove(userList.get(usernameList.indexOf(username)));
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
    // public static boolean isInMap(String username) {
    // return usernameList.contains(username) ? true : false;
    // }

    // // This method will return user
    // public static User getMember(String username) {

    // return userList.get(usernameList.indexOf(username));
    // }

    /*
     * This method checks user for login class.
     * It first will check if user with entered username exists.
     * If user with this username does not exist it will suggest user to register.
     * But, if user with endered username exists, it will check if entered password
     * for this username is correct. If yes user Successfully logined, else
     * it will throw WrongPasswordException.
     */
    public static boolean checkUserForLogin(String username, String password) {
        if (!usernameList.contains(username)) {
            System.out.println("There is no such user. You can register");
            return false;
        }

        else if (userList.get(usernameList.indexOf(username)).getPassword() == password.hashCode()) {
            return true;
        }

        return false;
    }

    // returns true if user with username "some username"
    // exits in the user_map
    public static boolean contains(String username) {
        return usernameList.contains(username) ? true : false;
    }

    // This method will return user
    public static User getMember(String username) {

        return userList.get(usernameList.indexOf(username));
    }

    public static int size() {
        return userList.size();
    }
}