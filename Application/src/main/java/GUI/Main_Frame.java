package GUI;

import javafx.embed.swing.JFXPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main_Frame {

    static GraphicsConfiguration gc; // Class field containing config info
    private JFrame mainPage;
    private JFXPanel mainPanel;
    private Panel_Controller controller;

    public Main_Frame(String P_name) {
        // Setting up the main frame
        mainPage = new JFrame(P_name, gc);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        mainPage.setBounds(0, 0, screenSize.width+10, screenSize.height);
        //System.out.println(screenSize);
        //mainPage.setExtendedState(6);
        Dimension default_dim = mainPage.getSize();
        System.out.println(default_dim);
        mainPage.setVisible(true);
        mainPage.setResizable(true);

        mainPage.getContentPane().addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                Component c = (Component) e.getSource();

                if (mainPage.getExtendedState() == 6){
                    mainPage.setSize(default_dim);
                    controller.setPanelControllerSize(default_dim);
                    mainPage.revalidate();
                    mainPage.repaint();
                    mainPage.setTitle("W: " + c.getWidth() + "H: " + c.getHeight());
                }
                else {
                    mainPage.setSize(mainPage.getSize());
                    controller.setPanelControllerSize(mainPage.getSize());
                    mainPage.revalidate();
                    mainPage.repaint();
                    mainPage.setTitle("W: " + c.getWidth() + "H: " + c.getHeight());
                }
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

        controller.startSimulation();

    }
}
