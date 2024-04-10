package login_register;

import database.UserDataBase;
import user_and_admin.Admin;
import user_and_admin.User;
import user_and_admin.Status;
import user_and_admin.CurrentStatus;

public class Login {
    private static boolean stay_logined = false;
    private static User active_user;

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
    public static boolean tryLogin(String username, String password, boolean stay_logined_arg) {
        if (stay_logined_arg == true) {
            stay_logined = true;
        } else {
            stay_logined = false;
        }

        while (!loginProcess(username, password)) {
        }

        return true;

    }

    private static boolean loginProcess(String username, String password) {

        if (Admin.login(username, password)) {
            CurrentStatus.current_user_status = Status.ADMIN;
            return true;

        } else {
            if (UserDataBase.UserDataBase1.checkUserForLogin(username, password)) {

                active_user = UserDataBase.UserDataBase1.getMember(username);
                CurrentStatus.current_user_status = Status.USER;
                return true;

            } else {
                System.out.println("Wrong username or password");
                return false;
            }
        }

    }
}
