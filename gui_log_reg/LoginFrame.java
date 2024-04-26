package gui_log_reg;
/*


 * Created by Bilgeyis
 * 
 * 
 */

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import database_systems.exceptions.IllegalMemberException;

import javax.swing.JCheckBoxMenuItem;

// Project classes
import login_register.Login;
import login_register.login_exceptions.WrongUserException;
import gui_elements.*;
import gui_library.DatabaseLib;

public class LoginFrame {
        // Frame objects
        private static JPanel jpanel;
        private static JFrame jframe;

        // Additional objects
        private static JCheckBoxMenuItem stayLoginedBox;
        private static Label Userlabel;
        private static Label passwordlabel;
        private static TextField userText;
        private static PasswordField passwordText;
        private static Label registerLink;
        private static Label registerText;
        private static Button loginButton;
        private static Label infoForUser;

        public static void Login() {
                SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                                login();
                                return;
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
                jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                jframe.setLocationRelativeTo(null);
                jframe.add(jpanel);
                jpanel.setLayout(null);

                jframe.setResizable(false);
                jpanel.setLayout(null);

                /* CheckBox **************************************************************/
                stayLoginedBox = new JCheckBoxMenuItem("Stay logined");
                stayLoginedBox.setBounds(10, 130, 100, 25);
                jpanel.add(stayLoginedBox);

                /* Labels*************************************************************** */
                // Username label
                Userlabel = new Label(10, 20, 80, 25, "Username:", jpanel);

                // Password label
                passwordlabel = new Label(10, 70, 80, 25, "Password:", jpanel);

                // Label near register button
                registerText = new Label(10, 165, 200, 25, "Do not you have an account?", jpanel);

                // Information for user
                infoForUser = new Label(100, 100, 300, 25, null, jpanel);
                infoForUser.getObject().setForeground(Color.RED);
                /*********************************************************************** */

                /* Text fields*********************************************************** */
                userText = new TextField(100, 20, 165, 25, 20, jpanel);

                passwordText = new PasswordField(100, 70, 165, 25, 20, jpanel);
                /*********************************************************************** */

                // Register link***********************************************************
                registerLink = new Label(190, 165, 200, 25, "<html><u>Register here</u></html>", jpanel);
                registerLink.getObject().setForeground(Color.blue);
                registerLink.getObject().setCursor(new Cursor(Cursor.HAND_CURSOR));
                registerLink.getObject().addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                                jframe.dispose();
                                RegisterFrame.Register(true); // Open the registration form
                                // Added by Orkhan
                        }
                });
                // **************************************************************************

                /* Login button************************************************************** */
                loginButton = new Button(130, 130, 100, 25, "Login", jpanel);
                loginButton.getObject().addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                String user = userText.getObject().getText();
                                String password = new String(passwordText.getObject().getPassword());

                                try {
                                        if (Login.tryLogin(user, password, stayLoginedBox.getState())) {
                                                jframe.dispose();
                                                new DatabaseLib();
                                        }
                                } catch (WrongUserException | IllegalMemberException ex) {
                                        infoForUser.getObject().setText(ex.getMessage());
                                }
                                // Here you can also add code to go back to the login form or any other action
                                // you want after registration.
                        }
                });
                /*************************************************************************** */

                // Visibility
                jframe.setVisible(true);
        }
}