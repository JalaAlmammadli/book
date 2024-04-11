package login_register;

import database.UserDataBase;
import user_and_admin.User;
import java.util.Scanner;

public class Register {

    /*
     * tryRegister() method works completely same as tryLogin()
     * method, except that it will require 2 passwords.
     */
    public static boolean tryRegister(String username, String password, String password2) {

        String end_prog = "n";
        Scanner scan = new Scanner(System.in);

        while (!registerProcess(username, password, password2) && end_prog.charAt(0) == 'n') {

            System.out.print("Exit(y/n): ");
            end_prog = scan.nextLine();
            System.out.print("Enter username: ");
            username = scan.nextLine();
            System.out.print("Enter password: ");
            password = scan.nextLine();
            System.out.print("Repeat password: ");
            password2 = scan.nextLine();
        }

        scan.close();
        return true;
    }

    private static boolean registerProcess(String username, String password, String password2) {

        if (!password.equals(password2)) {
            return false;
        }

        if (!UserDataBase.UserDataBase1.isInMap(username)) {
            UserDataBase.UserDataBase1.add(User.createUser(username, password));
            return true;
        }

        System.out.println("Account with username " + username + " already exists");
        return false;
    }
}
