package set_language;

import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageSelector {
    private Locale currentLocale;
    private ResourceBundle messages;

    public LanguageSelector(Locale initialLocale) {
        this.currentLocale = initialLocale;
        this.messages = ResourceBundle.getBundle("i18n.messages", currentLocale);
    }

    public void setLocale(Locale locale) {
        this.currentLocale = locale;
        this.messages = ResourceBundle.getBundle("i18n.messages", currentLocale);
    }

    public String getGreeting() {
        return messages.getString("greeting");
    }

    public String getWelcomeMessage() {
        return messages.getString("welcome_message");
    }
}
