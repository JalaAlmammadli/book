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

public class LoginFrame {
    private static JLabel Userlabel;
    private static JLabel passwordlabel;
    private static JTextField userText;
    private static JPasswordField passwordText;
    private static JLabel registerLink;
    private static JPanel jpanel;
    private static JFrame jframe;
    private static JLabel registerText;
    private static JButton loginButton;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                login();
            }
        });
    }

    public static void login() {
        jpanel = new JPanel();
        jframe = new JFrame();
        jframe.setSize(350, 210);
        jframe.setTitle("Login");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setLocationRelativeTo(null);
        jframe.add(jpanel);
        jpanel.setLayout(null);

        Userlabel = new JLabel();
        Userlabel.setBounds(10, 20, 80, 25);
        Userlabel.setText("Username");
        jpanel.add(Userlabel);

        passwordlabel = new JLabel("Password");
        passwordlabel.setBounds(10, 70, 80, 25);
        jpanel.add(passwordlabel);

        registerText = new JLabel("Do not you have an account?");
        registerText.setBounds(10, 145, 200, 25);
        jpanel.add(registerText);

        registerLink = new JLabel("<html><u>Register here</u></html>");
        registerLink.setBounds(190, 145, 200, 25);
        registerLink.setForeground(Color.blue);
        registerLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        registerLink.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                RegisterFrame.openRegistrationForm(); // Open the registration form
            }
        });
        jpanel.add(registerLink);

        // ----------------------------
        if (registerLink.isEnabled()) {
            return;
        }

        userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);
        jpanel.add(userText);

        passwordText = new JPasswordField();
        passwordText.setBounds(100, 70, 165, 25);
        jpanel.add(passwordText);

        loginButton = new JButton("Login");
        loginButton.setBounds(130, 110, 100, 25);
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String user = userText.getText();
                String password = new String(passwordText.getPassword());
                System.out.println("Registered: " + user + ", Password: " + password);
                // Here you can also add code to go back to the login form or any other action
                // you want after registration.
            }
        });
        jpanel.add(loginButton);

        jframe.setVisible(true);
    }
}
