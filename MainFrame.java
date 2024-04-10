import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainFrame{
    public static void main(String[] args) {
        JFrame jframe = new JFrame();
        jframe.setSize(500, 500);
        jframe.setTitle("JFrame");
        jframe.setVisible(true);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.getContentPane().setBackground(new Color(0xFFFFFF));
        JLabel jlabel = new JLabel();
        jlabel.setText("Welcome Bilgeyis!");  
        jframe.add(jlabel);
      }
}