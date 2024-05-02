package app_runner;

import gui_log_reg.LoginFrame;
import gui_log_reg.RegisterFrame;
import login_register.Register;

public class RunApp extends Thread {

    public synchronized void run() {

        try {
            LoginFrame.Login();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
