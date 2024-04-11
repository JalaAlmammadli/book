package login_register;

import java.util.Scanner;

import database.UserDataBase;
import user_and_admin.Admin;
import program_settings.Status;
import program_settings.Parametres;

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

    public static boolean tryLogin(String username, String password, boolean stay_logined_arg) {

        String end_prog = "n";
        Scanner scan = new Scanner(System.in);

        if (stay_logined_arg == true) {
            stay_logined = true;
        } else {
            stay_logined = false;
        }

        while (!loginProcess(username, password)) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {

            }

            System.out.println("Press any key to repeat...");

            scan.nextLine();

            System.out.print("Exit(y/n): ");
            end_prog = scan.nextLine();
            if (end_prog.charAt(0) == 'y') {
                return false;
            }

            System.out.print("Enter username: ");
            username = scan.nextLine();
            System.out.print("Enter password: ");
            password = scan.nextLine();

        }

        scan.close();
        return true;

    }

    private static boolean loginProcess(String username, String password) {

        if (Admin.login(username, password)) {
            Parametres.setUserStatus(Status.ADMIN);
            return true;

        } else {
            if (UserDataBase.UserDataBase1.checkUserForLogin(username, password)) {

                Parametres.setActiveUser(UserDataBase.UserDataBase1.getMember(username));
                Parametres.setUserStatus(Status.USER);
                return true;

            } else {
                System.out.println("Wrong username or password");
                return false;
            }
        }

    }
}
