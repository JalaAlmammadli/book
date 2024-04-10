
import user_and_admin.*;
import user_and_admin.exceptions.IllegalPasswordException;
import user_and_admin.exceptions.IllegalUsernameException;

import databases.UserDataBase;

class Main {
    public static void main(String[] args) {

        User user1;
        User user2;
        User user3;

        try {
            user1 = new User("Orkhan", "12345789");
            user2 = new User("JohnWick", "1234abcde");
            user3 = new User("ElonMusk", "123123123");
            System.out.println(user1.toString());
            System.out.println(user2.toString());
            System.out.println(user3.toString());

            UserDataBase.add(user1);

            System.out.println(UserDataBase.isInMap(user1.getUsername()));
        } catch (IllegalPasswordException | IllegalUsernameException e) {
            System.out.println(e);
        }
    }
}