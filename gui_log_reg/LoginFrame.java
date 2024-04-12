/*


 * Created by Bilgeyis
 * 
 * 
 */

package gui_log_reg;

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
import javax.swing.SwingUtilities;

// Project classes
import login_register.Login;
import login_register.login_exceptions.WrongUserException;

public class LoginFrame {

    // Declearing objects
    private static JLabel Userlabel;
    private static JLabel passwordlabel;
    private static JTextField userText;
    private static JPasswordField passwordText;
    private static JLabel registerLink;
    private static JPanel jpanel;
    private static JFrame jframe;
    private static JLabel registerText;
    private static JButton loginButton;

    // Added by Orkhan
    private static JLabel infoForUser;

    public static void Login() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                login();
            }
        });
    }

    // Added by Orkhan
    // By calling this method we can close login page
    public static void closeFrame() {
        jframe.dispose();
    }

    protected static void login() {

        // Login frame
        jpanel = new JPanel();
        jframe = new JFrame();
        jframe.setSize(350, 230);
        jframe.setTitle("Login");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setLocationRelativeTo(null);
        jframe.add(jpanel);
        // Added by Orkhan
        jframe.setResizable(false);
        jpanel.setLayout(null);

        // Username label
        Userlabel = new JLabel();
        Userlabel.setBounds(10, 20, 80, 25);
        Userlabel.setText("Username");
        jpanel.add(Userlabel);

        // Password label
        passwordlabel = new JLabel("Password");
        passwordlabel.setBounds(10, 70, 80, 25);
        jpanel.add(passwordlabel);

        // Label near register button
        registerText = new JLabel("Do not you have an account?");
        registerText.setBounds(10, 165, 200, 25);
        jpanel.add(registerText);

        // Register button
        registerLink = new JLabel("<html><u>Register here</u></html>");
        registerLink.setBounds(190, 165, 200, 25);
        registerLink.setForeground(Color.blue);
        registerLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        registerLink.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                RegisterFrame.openRegistrationForm(); // Open the registration form

                // Added by Orkhan
                jframe.dispose();
            }
        });
        jpanel.add(registerLink);

        // Username textfield
        userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);
        jpanel.add(userText);

        // Password textfield
        passwordText = new JPasswordField();
        passwordText.setBounds(100, 70, 165, 25);
        jpanel.add(passwordText);

        // Added by Orkhan
        infoForUser = new JLabel();
        infoForUser.setBounds(100, 100, 300, 25);
        infoForUser.setForeground(Color.RED);
        jpanel.add(infoForUser);

        // Login button
        loginButton = new JButton("Login");
        loginButton.setBounds(130, 130, 100, 25);
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String user = userText.getText();
                String password = new String(passwordText.getPassword());

                try {
                    if (Login.tryLogin(user, password, false)) {
                        jframe.dispose();
                    }
                } catch (WrongUserException ex) {
                    infoForUser.setText(ex.getMessage());
                }
                // Here you can also add code to go back to the login form or any other action
                // you want after registration.
            }
        });
        jpanel.add(loginButton);

        jframe.setVisible(true);
    }
}
