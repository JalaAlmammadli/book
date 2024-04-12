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
 
 public class RegisterFrame extends LoginFrame {
     private static JTextField newUsernameField;
     private static JPasswordField newPasswordField;
     private static JButton registerButton;
     private static JLabel loginText;
     private static JLabel loginLink;
     private static JFrame registrationFrame;
     private static JPanel registrationPanel;
     private static JLabel usernameLabel;
     private static JLabel passwordLabel;
 
     public static void openRegistrationForm() {
 
         // Close login page(Added by Orkhan)
         LoginFrame.closeFrame();
 
         registrationPanel = new JPanel();
         registrationFrame = new JFrame("Registration");
         registrationFrame.setSize(350, 210);
         registrationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         registrationFrame.setLocationRelativeTo(null);
         registrationFrame.add(registrationPanel);
 
         // Added by Orkhan. For some reason this page is can not be non-resizable
         registrationFrame.setResizable(false);
 
         registrationPanel.setLayout(null);
 
         usernameLabel = new JLabel("Username");
         usernameLabel.setBounds(10, 20, 80, 25);
         registrationPanel.add(usernameLabel);
 
         passwordLabel = new JLabel("Password");
         passwordLabel.setBounds(10, 60, 80, 25);
         registrationPanel.add(passwordLabel);
 
         newUsernameField = new JTextField(20);
         newUsernameField.setBounds(100, 20, 165, 25);
         registrationPanel.add(newUsernameField);
 
         newPasswordField = new JPasswordField();
         newPasswordField.setBounds(100, 60, 165, 25);
         registrationPanel.add(newPasswordField);
 
         registerButton = new JButton("Register");
         registerButton.setBounds(100, 100, 100, 25);
         registerButton.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                 String newUsername = newUsernameField.getText();
                 String newPassword = new String(newPasswordField.getPassword());
                 // Perform registration logic with newUsername and newPassword
                 System.out.println("Registered: " + newUsername + ", Password: " + newPassword);
                 // Here you can also add code to close the registration form or any other action
                 // you want after registration.
                 registrationFrame.dispose(); // Close the registration form after registration
             }
         });
         registrationPanel.add(registerButton);
 
         loginText = new JLabel("Already have an account?");
         loginText.setBounds(10, 140, 200, 25);
         registrationPanel.add(loginText);
 
         loginLink = new JLabel("<html><u>Login here</u></html>");
         loginLink.setBounds(170, 140, 100, 25);
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