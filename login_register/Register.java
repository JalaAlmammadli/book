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
 import user_and_admin.exceptions.*;;
 
 public class Register {
 
     /*
      * tryRegister() method works completely same as tryLogin()
      * method, except that it will require 2 passwords.
      */
     public static boolean tryRegister(String username, String password, String password2)
             throws PasswordsDontMatch, ExistingUserException, IllegalPasswordException, IllegalUsernameException {
 
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
             throws PasswordsDontMatch, ExistingUserException, IllegalPasswordException, IllegalUsernameException {
 
         if (!password.equals(password2)) {
             throw new PasswordsDontMatch("Passwords don't match");
         }
 
         if (!UserDataBase.UserDataBase1.isInMap(username)) {
             User u = User.createUser(username, password2);
             if (u != null) {
                 UserDataBase.UserDataBase1.add(u);
                 System.out.println("Registered");
                 return true;
             }
             return false;
         }
 
         throw new ExistingUserException("Account already exists");
     }
 }