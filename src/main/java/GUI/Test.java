package GUI;

import javafx.embed.swing.JFXPanel;
import javax.swing.*;
import java.awt.*;

public class Test {

    static GraphicsConfiguration gc; // Class field containing config info
    private JFrame mainPage;
    private JPanel mainPanel;
    private Overall_Graph graph_panel;
    private Panel_Controller controller;
    private int main_width, main_height;

    public Test(int main_width, int main_height) {
        this.main_width = main_width;
        this.main_height = main_height;

        controller = new Panel_Controller();
        graph_panel = new Overall_Graph();
        mainPanel = controller.getMainPanel();


        mainPage = new JFrame("Main Frame", gc);
        //mainPage.setLayout(new GridLayout(2, 1));
        mainPage.setSize(main_width, main_height);
        mainPage.setVisible(true);

        //mainPage.add(mainPanel);
        mainPage.add(graph_panel.getGraphPanel());

        mainPage.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  // This line closes the program when the frame is closed

    }
}
