/*


 * Created by Orkhan
 * 
 * 
 */

package login_register;

import database.UserDataBase;
import user_and_admin.User;
import login_register.login_exceptions.PasswordsDontMatch;
import login_register.login_exceptions.ExistingUserException;

public class Register {

    /*
     * tryRegister() method works completely same as tryLogin()
     * method, except that it will require 2 passwords.
     */
    public static boolean tryRegister(String username, String password, String password2)
            throws PasswordsDontMatch, ExistingUserException {

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {

        }

        if (!registerProcess(username, password, password2)) {
            return false;
        }

        return true;
    }

    private static boolean registerProcess(String username, String password, String password2)
            throws PasswordsDontMatch, ExistingUserException {

        if (!password.equals(password2)) {
            throw new PasswordsDontMatch("Passwords don't match");
        }

        if (!UserDataBase.UserDataBase1.isInMap(username)) {
            System.out.println("Registered");
            UserDataBase.UserDataBase1.add(User.createUser(username, password));
            return true;
        }

        throw new ExistingUserException("Account already exists");
    }
}
