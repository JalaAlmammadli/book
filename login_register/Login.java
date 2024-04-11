package login_register;

import user_and_admin.Admin;
import user_and_admin.User;
import user_and_admin.Status;
import user_and_admin.CurrentStatus;

public class Login {
    private static boolean stay_logined = false;
    private static User user;

    public static void tryLogin(String username, String password, boolean stay_logined_arg) {
        if (stay_logined_arg == true) {
            stay_logined = true;
        } else {
            stay_logined = false;
        }

        if (Admin.login(username, password)) {
            CurrentStatus.current_user_status = Status.ADMIN;
        } else {

        }

    }
}
