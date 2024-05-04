package lang_change;

import app_runner.RunApp;
import gui_elements.Button;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
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
        frame.setBounds(600, 350, 250, 100);
        frame.setResizable(false);
        frame.setVisible(true);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setVisible(true);

        frame.add(panel);

        azButton = new Button(30, 25, 75, 25, "AZE", panel);
        engButton = new Button(135, 25, 75, 25, "ENG", panel);

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
}
