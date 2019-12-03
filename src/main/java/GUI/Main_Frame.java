package GUI;

import javafx.embed.swing.JFXPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Main_Frame {

    static GraphicsConfiguration gc; // Class field containing config info
    private JFrame mainPage;
    private JFXPanel mainPanel;
    private Panel_Controller controller;

    public Main_Frame() {
        // Setting up the main frame
        mainPage = new JFrame("Main Frame", gc);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        mainPage.setBounds(0,0,screenSize.width, screenSize.height);
        mainPage.setVisible(true);
        mainPage.setResizable(false);

        System.out.println("Main Frame Size : ");
        System.out.println(mainPage.getSize());

        // Setting up main panel
        controller = new Panel_Controller(mainPage.getSize());
        mainPanel = controller.getMainPanel();

        // Adding main panel to main frame
        mainPage.add(mainPanel);

        // Closing program when when main frame is closed
        mainPage.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        while (mainPage.isVisible()) {
            int timer =0;
            for (int i=0; i<=1000; i+=1){
                timer += 1;
                System.out.println(timer);
            }
            controller.updateController();
            mainPanel = controller.getMainPanel();
        }

    }
}

