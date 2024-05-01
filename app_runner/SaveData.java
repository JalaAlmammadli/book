package app_runner;

import database_system.BookDataBase;
import database_system.UserDataBase;

public class SaveData {

    public static void save() {

        UserDataBase.MainUserList.writeData();
        BookDataBase.MainBookList.writeData();
    }
}
