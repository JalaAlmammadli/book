/*


 * Created by Orkhan
 * 
 * 
 */

package login_register;

import database.UserDataBase;
import user_and_admin.Admin;
import program_settings.Status;
import program_settings.Parametres;
import login_register.login_exceptions.WrongUserException;

public class Login {
    private static boolean stay_logined = false;

    public boolean stayLogined() {
        return stay_logined;
    }

    /*
     * This method is used for calling loginProcess() method,
     * because user can make many attemps while logging in
     * so tryLogin method will can loginProcess() method
     * until user will able to login and it will prevent
     * the chance of occuring StackOverflow error. It also
     * will return true if login process finished successfully.
     * In the future user will able to prevent logging in by finishing
     * the program.
     */

    public static boolean tryLogin(String username, String password, boolean stay_logined_arg)
            throws WrongUserException {

        if (stay_logined_arg == true) {
            stay_logined = true;
            System.out.println("Stay logined");
        } else {
            stay_logined = false;
        }

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {

        }

        if (loginProcess(username, password)) {
            return true;
        }

        return false;

    }

    private static boolean loginProcess(String username, String password) throws WrongUserException {

        if (Admin.login(username, password)) {
            Parametres.setUserStatus(Status.ADMIN);
            return true;

        } else {
            if (UserDataBase.UserDataBase1.checkUserForLogin(username, password)) {

                System.out.println("Success");
                Parametres.setActiveUser(UserDataBase.UserDataBase1.getMember(username));
                Parametres.setUserStatus(Status.USER);
                return true;

            } else {
                throw new WrongUserException("Wrong username or password");
            }
        }

    }
}
