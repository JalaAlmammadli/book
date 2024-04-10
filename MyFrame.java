import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class MyFrame extends JFrame{
    MyFrame(){
        this.setSize(500, 500);
        this.setTitle("JFrame");
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(0xFFFFFF));
        JLabel jlabel = new JLabel();
        jlabel.setText("Welcome Bilgeyis!");
        this.add(jlabel);
    }
}
