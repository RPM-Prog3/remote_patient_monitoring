package GUI;

import javafx.embed.swing.JFXPanel;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Panel_Controller {
    private JFXPanel mainPanel, simulationPanel, tuningPanel, vitalsPanel;
    private JFXPanel graphPanel;
    private Overall_Graph graphs;
    private BP_Vitals BP_panel;
    private RR_Vitals RR_panel;
    private ECG_Vitals ECG_panel;
    private HR_Vitals HR_panel;
    private Border border;
    private int s_v_ratio_num, s_v_ratio_den, s_t_ratio_num, s_t_ratio_den;
    private Dimension graph_panel_dim, simulation_panel_dim, main_panel_dim, vitals_panel_dim;

    public Panel_Controller(Dimension main_panel_dim) {
        // Instantiating main panel and the several sub panels
        mainPanel = new JFXPanel();
        tuningPanel = new JFXPanel();
        simulationPanel = new JFXPanel();
        vitalsPanel = new JFXPanel();
        graphs = new Overall_Graph();
        graphPanel = graphs.getGraphPanel();

        this.main_panel_dim = main_panel_dim;
        s_v_ratio_num = 8;
        s_v_ratio_den = 10;
        s_t_ratio_num = 8;
        s_t_ratio_den = 10;

        // Setting the layout of the main panel and of the several sub panels
        mainPanel.setLayout(new BorderLayout());
        simulationPanel.setLayout(new BorderLayout());
        graphPanel.setLayout(new GridLayout(4, 1));
        vitalsPanel.setLayout(new GridLayout(4, 1));

        // Making the various visible
        mainPanel.setVisible(true);
        tuningPanel.setVisible(true);
        simulationPanel.setVisible(true);
        vitalsPanel.setVisible(true);
        graphPanel.setVisible(true);

        // Instantiating border object(s)
        border = BorderFactory.createLineBorder(Color.black);

        // Setting contours of the different panels
        simulationPanel.setBorder(border);
        tuningPanel.setBorder(border);
        vitalsPanel.setBorder(border);

        vitals_panel_dim = vitalsPanel.getSize();
        vitals_panel_dim.width = (int)((main_panel_dim.width*(s_v_ratio_den - s_v_ratio_num))/ (s_v_ratio_den));
        vitals_panel_dim.height = (int)((main_panel_dim.height*s_t_ratio_num)/(s_v_ratio_den*4));

        BP_panel = new BP_Vitals(vitals_panel_dim);
        RR_panel = new RR_Vitals(vitals_panel_dim);
        ECG_panel = new ECG_Vitals(vitals_panel_dim);
        HR_panel = new HR_Vitals(vitals_panel_dim);

        // Adding vital sign (BP, RR, HR, ECG) panels to vitalsPanel
        vitalsPanel.add(BP_panel.getVitalsPanel());
        vitalsPanel.add(HR_panel.getVitalsPanel());
        vitalsPanel.add(ECG_panel.getVitalsPanel());
        vitalsPanel.add(RR_panel.getVitalsPanel());

        // Composing the main panel
        mainPanel.add(tuningPanel, BorderLayout.PAGE_END);
        mainPanel.add(simulationPanel, BorderLayout.PAGE_START);

        // Setting dimensions and proportions of the panels
        simulation_panel_dim = simulationPanel.getSize();
        simulation_panel_dim.width = main_panel_dim.width;
        simulation_panel_dim.height = (int)((main_panel_dim.height*s_t_ratio_num)/s_t_ratio_den);
        simulationPanel.setPreferredSize(simulation_panel_dim);

        graph_panel_dim = graphPanel.getPreferredSize();
        graph_panel_dim.width = (int)((simulation_panel_dim.width*s_v_ratio_num)/s_v_ratio_den);
        graph_panel_dim.height = simulation_panel_dim.height;
        graphPanel.setPreferredSize(graph_panel_dim);

        // Adding the vitalsPanel and graphPanel to simulationPanel
        simulationPanel.add(graphPanel, BorderLayout.LINE_START);
        simulationPanel.add(vitalsPanel, BorderLayout.CENTER);
    }

    public JFXPanel getMainPanel() {
        return mainPanel;
    }

    }


