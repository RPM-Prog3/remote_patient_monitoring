package GUI;

import javafx.embed.swing.JFXPanel;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Panel_Controller {
    private JFXPanel mainPanel, simulationPanel, tuningPanel, vitalsPanel;
    private JFXPanel graphPanel;
    private Overall_Graph graphs;
    private BP_Vitals BP_panel;
    private RR_Vitals RR_panel;
    private ECG_Vitals ECG_panel;
    private HR_Vitals HR_panel;
    private Border border;
    private int s_v_ratio_num, s_v_ratio_den, s_t_ratio_num, s_t_ratio_den, simulation_panel_width;
    private Dimension graph_panel_dim, simulation_panel_dim, main_panel_dim;

    public Panel_Controller(Dimension main_panel_dim) {
        // Instantiating main panel and the several sub panels
        mainPanel = new JFXPanel();
        tuningPanel = new JFXPanel();
        simulationPanel = new JFXPanel();
        vitalsPanel = new JFXPanel();
        graphs = new Overall_Graph();
        graphPanel = graphs.getGraphPanel();

        BP_panel = new BP_Vitals();
        RR_panel = new RR_Vitals();
        ECG_panel = new ECG_Vitals();
        HR_panel = new HR_Vitals();

        this.main_panel_dim = main_panel_dim;

        // Setting the layout of the main panel and of the several sub panels
        mainPanel.setLayout(new GridLayout(2, 1));
        simulationPanel.setLayout(new GridLayout(1, 2));
        graphPanel.setLayout(new GridLayout(4, 1));
        vitalsPanel.setLayout(new GridLayout(4, 1));

        // Making the various visible
        mainPanel.setVisible(true);
        tuningPanel.setVisible(true);
        simulationPanel.setVisible(true);
        vitalsPanel.setVisible(true);
        graphPanel.setVisible(true);

        border = BorderFactory.createLineBorder(Color.black);

        // Setting contours of the different panels
        simulationPanel.setBorder(border);
        tuningPanel.setBorder(border);
        vitalsPanel.setBorder(border);
//        BP_panel.setBorder(border);
//        RR_panel.setBorder(border);
//        HR_panel.setBorder(border);
//        ECG_panel.setBorder(border);

        System.out.println ("SUCAAAAA");

        // Adding vital sign (BP, RR, HR, ECG) panels to vitalsPanel
        vitalsPanel.add(BP_panel.getVitalsPanel());
        vitalsPanel.add(HR_panel.getVitalsPanel());
        vitalsPanel.add(ECG_panel.getVitalsPanel());
        vitalsPanel.add(RR_panel.getVitalsPanel());

        // Adding the vitalsPanel and graphPanel to simulationPanel
        simulationPanel.add(graphPanel);
        simulationPanel.add(vitalsPanel);

        // Setting dimensions and proportions of the panels
        s_v_ratio_num = 7;
        s_v_ratio_den = 10;
        System.out.println("girotondo: ");
        System.out.println(main_panel_dim);

        simulation_panel_dim = simulationPanel.getPreferredSize();
        simulation_panel_dim.width = main_panel_dim.width;
        simulation_panel_dim.height = (int)((main_panel_dim.height*8)/10);
        simulationPanel.setMaximumSize(simulation_panel_dim);
        System.out.println("Simulation Panel Dimensions");
        System.out.println(simulation_panel_dim);

        graph_panel_dim = graphPanel.getPreferredSize();
        graph_panel_dim.width = (int)((simulation_panel_dim.width*s_v_ratio_num)/s_v_ratio_den);
        graph_panel_dim.height = simulation_panel_dim.height;
        System.out.println("Graph Panel Width: ");
        System.out.println(graph_panel_dim.height);
        graphPanel.setMaximumSize(graph_panel_dim);

        // Composing the main panel
        mainPanel.add(simulationPanel);
        mainPanel.add(tuningPanel);

        simulationPanel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Simulation Panel size: ");
                System.out.println(simulationPanel.getPreferredSize());
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

    public JFXPanel getMainPanel() {
        return mainPanel;
    }

    /*public void setDesiredSize(Rectangle main_panel_dim){
        // Setting dimensions and proportions of the panels
        s_v_ratio_num = 7;
        s_v_ratio_den = 10;
        System.out.println("girotondo: ");
        System.out.println(main_panel_dim);
        simulation_panel_dim = simulationPanel.getBounds();
        simulation_panel_dim.width = main_panel_dim.width;
        simulation_panel_dim.height = (int)((main_panel_dim.height*8)/10);
        System.out.println("Simulation Panel Dimensions");
        System.out.println(simulation_panel_dim);
        graph_panel_dim = graphPanel.getBounds();
        graph_panel_dim.width = (int)((simulation_panel_dim.width*s_v_ratio_num)/s_v_ratio_den);
        System.out.println("Graph Panel Width: ");
        System.out.println(graph_panel_dim.width);
        graphPanel.setBounds(graph_panel_dim);
    }*/
    }


