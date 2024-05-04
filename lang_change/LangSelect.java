
package lang_change;

import app_runner.RunApp;
import gui_elements.Button;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class LangSelect extends JFrame{
    
    JFrame frame;
    JPanel panel;
    Button azButton, engButton;

    public LangSelect(){
        frame = new JFrame();
        frame.setTitle("Select language");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        
        centerFrame();

        panel = new JPanel();
        panel.setLayout(null); 
        panel.setVisible(true);

        JLabel azeIcon = new JLabel(new ImageIcon(getResizedImage("./azerbaijan.png", 35, 35)));
        JLabel engIcon = new JLabel(new ImageIcon(getResizedImage("./united-kingdom.png", 35, 35)));

        azeIcon.setBounds(80, 40, 48, 48);
        engIcon.setBounds(80, 100, 48, 48); 

        panel.add(azeIcon);
        panel.add(engIcon);

        frame.add(panel);

        Dimension buttonSize = new Dimension(100, 30); 
        centerButtons(buttonSize);

        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }

    private void centerFrame() {
        int frameWidth = 350; 
        int frameHeight = 230;
        frame.setSize(frameWidth, frameHeight);
    }

    private void centerButtons(Dimension buttonSize) {
        azButton = new Button(135, 50, buttonSize.width, buttonSize.height, "AZE", panel);
        engButton = new Button(135, 110, buttonSize.width, buttonSize.height, "ENG", panel);
    
        azButton.getObject().setBackground(new Color(0xF1F0E8));
        engButton.getObject().setBackground(new Color(0xF1F0E8));
        azButton.getObject().setFocusable(false); 
        engButton.getObject().setFocusable(false); 

    
        azButton.getObject().addActionListener((ActionEvent e) -> {
            Lang.change(Language.AZE);
            RunApp run = new RunApp();
            run.start();
            try {
                run.join();
            } catch (InterruptedException ex){
            }
            frame.dispose();
        });
    
        engButton.getObject().addActionListener((ActionEvent e) -> {
            Lang.change(Language.ENG);
            RunApp run = new RunApp();
            run.start();
            try {
                run.join();
            } catch (InterruptedException ex){
            }
            frame.dispose();
        });
    }
    

    private Image getResizedImage(String imagePath, int width, int height) {
        ImageIcon icon = new ImageIcon(imagePath);
        Image image = icon.getImage();
        Image resizedImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return resizedImage;
    }
}