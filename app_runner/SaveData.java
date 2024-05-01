package app_runner;

import database_systems.BookDataBase;
import database_systems.UserDataBase;

public class SaveData {

    public static void save() {

        UserDataBase.MainUserList.writeData();
        BookDataBase.MainBookList.writeData();
    }
}
