package GUI;

import javafx.embed.swing.JFXPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main_Frame {

    static GraphicsConfiguration gc; // Class field containing config info
    private Thread refreshing;
    private JFrame mainPage;
    private JFXPanel mainPanel;
    private Panel_Controller controller;

    public Main_Frame(){
        // Setting up the main frame
        mainPage = new JFrame("Main Frame", gc);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        mainPage.setBounds(0, 0, screenSize.width, screenSize.height);
        mainPage.setVisible(true);
        mainPage.setResizable(true);

        mainPage.getContentPane().addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e){
                Component c = (Component)e.getSource();
                mainPage.setSize(mainPage.getSize());
                controller.setPanelControllerSize(mainPage.getSize());
                mainPage.revalidate();
                mainPage.repaint();
                mainPage.setTitle("W: " + c.getWidth() + "H: " + c.getHeight());
            }
        });

        // Setting up main panel
        controller = new Panel_Controller(mainPage.getSize());
        controller.setPanelControllerSize(mainPage.getSize());
        mainPanel = controller.getMainPanel();

        // Adding main panel to main frame
        mainPage.add(mainPanel);

        // Closing program when when main frame is closed
        mainPage.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        refreshing = new Thread (new Refresh (controller));
        refreshing.start();
//        while (mainPage.isVisible()) {
//            int timer =0;
//            for (int i=0; i<=1000; i+=1){
//                timer += 1;
//                System.out.println(timer);
//            }
//            controller.updateController();
//            mainPanel = controller.getMainPanel();
//        }
    }
}


