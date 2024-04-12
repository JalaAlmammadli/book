/*


 * Created by Orkhan
 * 
 * 
 */

package program_settings;

import user_and_admin.User;

public class Parametres {
    /*
     * current_user-status will hold mode of program ADMIN or USER
     * it will depend how user is logined
     * For example Admin can login by entering username: "admin", and password:
     * "admin",
     * current_user_status will be ADMIN
     */
    private static Status current_user_status;

    /*
     * This object will hold the user who is active in the program
     * so when user will make changes all data will be made on active_user
     */
    private static User active_user;

    public static Status getUserStatus() {
        return current_user_status;
    }

    public static void setUserStatus(Status s) {
        current_user_status = s;
    }

    public static User getActiveUser() {
        return active_user;
    }

    public static void setActiveUser(User u) {
        active_user = u;
    }
}
