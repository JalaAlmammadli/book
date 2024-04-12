/*


 * Created by Orkhan
 * 
 * 
 */

package login_register;

import database.UserDataBase;
import user_and_admin.User;

public class Register {

    /*
     * tryRegister() method works completely same as tryLogin()
     * method, except that it will require 2 passwords.
     */
    public static boolean tryRegister(String username, String password, String password2) {

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {

        }

        if (!registerProcess(username, password, password2)) {
            return false;
        }

        return true;
    }

    private static boolean registerProcess(String username, String password, String password2) {

        if (!password.equals(password2)) {
            System.out.println("Passwords do not match");
            return false;
        }

        if (!UserDataBase.UserDataBase1.isInMap(username)) {
            System.out.println("Registered");
            UserDataBase.UserDataBase1.add(User.createUser(username, password));
            return true;
        }

        System.out.println("Account with username " + username + " already exists");
        return false;
    }
}
