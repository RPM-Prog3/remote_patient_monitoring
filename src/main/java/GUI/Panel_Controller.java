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
    private int s_v_ratio_num, s_v_ratio_den, s_t_ratio_num, s_t_ratio_den;
    private Dimension graph_panel_dim, simulation_panel_dim, main_panel_dim;

    public Panel_Controller(Dimension main_panel_dim) {
        // Instantiating main panel and the several sub panels
        mainPanel = new JFXPanel();
        tuningPanel = new JFXPanel();
        simulationPanel = new JFXPanel();
        vitalsPanel = new JFXPanel();
        graphs = new Overall_Graph();
        graphPanel = graphs.getGraphPanel();

        this.main_panel_dim = main_panel_dim;

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

        border = BorderFactory.createLineBorder(Color.black);

        // Setting contours of the different panels
        simulationPanel.setBorder(border);
        tuningPanel.setBorder(border);
        vitalsPanel.setBorder(border);

        BP_panel = new BP_Vitals();
        RR_panel = new RR_Vitals();
        ECG_panel = new ECG_Vitals();
        HR_panel = new HR_Vitals();

        // Adding vital sign (BP, RR, HR, ECG) panels to vitalsPanel
        vitalsPanel.add(BP_panel.getVitalsPanel());
        vitalsPanel.add(HR_panel.getVitalsPanel());
        vitalsPanel.add(ECG_panel.getVitalsPanel());
        vitalsPanel.add(RR_panel.getVitalsPanel());

        // Composing the main panel
        mainPanel.add(tuningPanel, BorderLayout.PAGE_END);
        mainPanel.add(simulationPanel, BorderLayout.PAGE_START);

        // Setting dimensions and proportions of the panels
        s_v_ratio_num = 8;
        s_v_ratio_den = 10;

        simulation_panel_dim = simulationPanel.getSize();
        simulation_panel_dim.width = main_panel_dim.width;
        simulation_panel_dim.height = (int)((main_panel_dim.height*8)/10);
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


