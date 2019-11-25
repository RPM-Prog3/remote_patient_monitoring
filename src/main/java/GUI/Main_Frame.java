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
        mainPage.setVisible(true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        mainPage.setBounds(0,0,screenSize.width, screenSize.height);
        mainPage.setResizable(true);

        System.out.println("cacca 1 : ");
        System.out.println(mainPage.getPreferredSize());

        // Setting up main panel
        controller = new Panel_Controller(mainPage.getPreferredSize());
        mainPanel = controller.getMainPanel();

        // Adding main panel to main frame
        mainPage.add(mainPanel);

        // Closing program when when main frame is closed
        mainPage.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Action Listener
        mainPanel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Main Panel size: ");
                System.out.println(mainPanel.getPreferredSize());
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }
}

