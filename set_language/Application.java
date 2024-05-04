package set_language;

import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.*;

public class Application {

    public static void launch(String language) {
        ResourceBundle bundle = ResourceBundle.getBundle("i18n.messages", new Locale(language));

        String greeting = bundle.getString("greeting");
        String welcomeMessage = bundle.getString("welcome_message");

        JOptionPane.showMessageDialog(
            null,
            greeting + "\n" + welcomeMessage,
            "Welcome",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    public static void main(String[] args) {
        // Default language is English
        launch("en");
    }
}


