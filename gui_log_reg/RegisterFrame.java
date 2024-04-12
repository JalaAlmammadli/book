<<<<<<< HEAD
package gui_log_reg;
=======
/*


 * Created by Bilgeyis
 * 
 * 
 */

package gui_log_reg;

>>>>>>> orkhan-branch
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

<<<<<<< HEAD
public class RegisterFrame extends LoginFrame {
    private static JTextField newUsernameField;
    private static JPasswordField newPasswordField;
=======
// Project classes
import login_register.Register;
import login_register.login_exceptions.ExistingUserException;
import login_register.login_exceptions.PasswordsDontMatch;

public class RegisterFrame extends LoginFrame {
    private static JTextField newUsernameField;
    private static JPasswordField newPasswordField;
    private static JPasswordField newPasswordField2;
>>>>>>> orkhan-branch
    private static JButton registerButton;
    private static JLabel loginText;
    private static JLabel loginLink;
    private static JFrame registrationFrame;
    private static JPanel registrationPanel;
    private static JLabel usernameLabel;
    private static JLabel passwordLabel;
<<<<<<< HEAD

    public static void openRegistrationForm() {
        registrationPanel = new JPanel();
        registrationFrame = new JFrame("Registration");
        registrationFrame.setSize(350, 210);
=======
    private static JLabel repeatPasswordLabel;
    private static JLabel infoForUser;

    // I made it protected because, I could call it from main
    // and it gave NullPointerException as Login page was not
    // created. So, I made it for security purposes.
    public static void openRegistrationForm() {

        registrationPanel = new JPanel();
        registrationFrame = new JFrame("Registration");
        registrationFrame.setSize(350, 280);
>>>>>>> orkhan-branch
        registrationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        registrationFrame.setLocationRelativeTo(null);
        registrationFrame.add(registrationPanel);

<<<<<<< HEAD
=======
        // Added by Orkhan. For some reason this page is can not be non-resizable
        registrationFrame.setResizable(false);

>>>>>>> orkhan-branch
        registrationPanel.setLayout(null);

        usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(10, 20, 80, 25);
        registrationPanel.add(usernameLabel);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 60, 80, 25);
        registrationPanel.add(passwordLabel);

<<<<<<< HEAD
=======
        // Added by Orkhan
        repeatPasswordLabel = new JLabel("Repeat Password");
        repeatPasswordLabel.setBounds(10, 100, 80, 25);
        registrationPanel.add(repeatPasswordLabel);

>>>>>>> orkhan-branch
        newUsernameField = new JTextField(20);
        newUsernameField.setBounds(100, 20, 165, 25);
        registrationPanel.add(newUsernameField);

        newPasswordField = new JPasswordField();
        newPasswordField.setBounds(100, 60, 165, 25);
        registrationPanel.add(newPasswordField);

<<<<<<< HEAD
        registerButton = new JButton("Register");
        registerButton.setBounds(100, 100, 100, 25);
=======
        // Added by Orkhan
        newPasswordField2 = new JPasswordField();
        newPasswordField2.setBounds(100, 100, 165, 25);
        registrationPanel.add(newPasswordField2);

        // Added by Orkhan
        infoForUser = new JLabel();
        infoForUser.setBounds(100, 110, 165, 60);
        infoForUser.setForeground(Color.RED);
        registrationPanel.add(infoForUser);

        registerButton = new JButton("Register");
        registerButton.setBounds(100, 170, 100, 25);
>>>>>>> orkhan-branch
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String newUsername = newUsernameField.getText();
                String newPassword = new String(newPasswordField.getPassword());
<<<<<<< HEAD
                // Perform registration logic with newUsername and newPassword
                System.out.println("Registered: " + newUsername + ", Password: " + newPassword);
                // Here you can also add code to close the registration form or any other action
                // you want after registration.
                registrationFrame.dispose(); // Close the registration form after registration
=======
                String newPassword2 = new String(newPasswordField2.getPassword());

                // Perform registration logic with newUsername and newPassword

                // Added by Orkhan
                try {
                    if (Register.tryRegister(newUsername, newPassword, newPassword2)) {
                        registrationFrame.dispose();
                    }
                } catch (ExistingUserException | PasswordsDontMatch ex) {
                    infoForUser.setText(ex.getMessage());
                }
                // Here you can also add code to close the registration form or any other action
                // you want after registration.
>>>>>>> orkhan-branch
            }
        });
        registrationPanel.add(registerButton);

        loginText = new JLabel("Already have an account?");
<<<<<<< HEAD
        loginText.setBounds(10, 140, 200, 25);
        registrationPanel.add(loginText);

        loginLink = new JLabel("<html><u>Login here</u></html>");
        loginLink.setBounds(170, 140, 100, 25);
=======
        loginText.setBounds(10, 210, 200, 25);
        registrationPanel.add(loginText);

        loginLink = new JLabel("<html><u>Login here</u></html>");
        loginLink.setBounds(170, 210, 100, 25);
>>>>>>> orkhan-branch
        loginLink.setForeground(Color.blue);
        loginLink.setCursor(new Cursor(Cursor.HAND_CURSOR));

        loginLink.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                registrationFrame.dispose();
                LoginFrame.login();
            }
        });

        registrationPanel.add(loginLink);

        registrationFrame.setVisible(true);
    }
}
