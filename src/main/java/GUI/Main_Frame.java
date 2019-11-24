package GUI;

import javafx.embed.swing.JFXPanel;
import javax.swing.*;
import java.awt.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Main_Frame {

    static GraphicsConfiguration gc; // Class field containing config info
    private JFrame mainPage;
    private JPanel mainPanel;
    private Panel_Controller controller;
    private int main_width, main_height;

    public Main_Frame(int main_width, int main_height) {
        this.main_width = main_width;
        this.main_height = main_height;

        controller = new Panel_Controller();
        mainPanel = controller.getMainPanel();

        mainPage = new JFrame("Main Frame", gc);

        mainPage.setSize(main_width, main_height);
        mainPage.setVisible(true);
        mainPage.setResizable(true);

        mainPage.add(mainPanel);
        mainPage.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  // This line closes the program when the frame is closed

        mainPanel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println(mainPanel.getBounds());
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

