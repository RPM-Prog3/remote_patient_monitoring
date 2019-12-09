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
    private Dimension graph_panel_dim, simulation_panel_dim, main_panel_dim_, sub_vitals_panel_dim;

    public Panel_Controller(Dimension main_panel_dim) {
        // Instantiating main panel and the several sub panels
        mainPanel = new JFXPanel();
        tuningPanel = new JFXPanel();
        simulationPanel = new JFXPanel();
        vitalsPanel = new JFXPanel();
        graphs = new Overall_Graph();
        graphPanel = graphs.getGraphPanel();

        this.main_panel_dim_ = main_panel_dim;
        s_v_ratio_num = 8; //simulation panel to vitals panel numerator value of ratio (in width)
        s_v_ratio_den = 10; //simulation panel to vitals panel denominator value of ratio (in width)
        s_t_ratio_num = 8; //simulation panel to tuning  panel numerator value of ratio (in height)
        s_t_ratio_den = 10; //simulation panel to tuning panel denominator value of ratio (in height)

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

        // Calculating the size of the sub panels of vitals panel that will contain the values
        // of the vital signs. This is then fed into the constructor of the various vital signs classes.
        sub_vitals_panel_dim = vitalsPanel.getSize();
        sub_vitals_panel_dim.width = (int)((main_panel_dim_.width*(s_v_ratio_den - s_v_ratio_num))/(s_v_ratio_den));
        sub_vitals_panel_dim.height = (int)((main_panel_dim_.height*s_t_ratio_num)/(s_v_ratio_den*4));

        BP_panel = new BP_Vitals(sub_vitals_panel_dim);
        RR_panel = new RR_Vitals(sub_vitals_panel_dim);
        ECG_panel = new ECG_Vitals(sub_vitals_panel_dim);
        HR_panel = new HR_Vitals(sub_vitals_panel_dim);

        // Adding vital sign (BP, RR, HR, ECG) panels to vitalsPanel
        vitalsPanel.add(ECG_panel.getVitalsPanel());
        ECG_panel.setVitals_value_displaySize(sub_vitals_panel_dim);
        vitalsPanel.add(BP_panel.getVitalsPanel());
        BP_panel.setVitals_value_displaySize(sub_vitals_panel_dim);
        vitalsPanel.add(RR_panel.getVitalsPanel());
        RR_panel.setVitals_value_displaySize(sub_vitals_panel_dim);
        vitalsPanel.add(HR_panel.getVitalsPanel());
        HR_panel.setVitals_value_displaySize(sub_vitals_panel_dim);

        // Adding the vitalsPanel and graphPanel to simulationPanel
        simulationPanel.add(graphPanel, BorderLayout.LINE_START);
        simulationPanel.add(vitalsPanel, BorderLayout.CENTER);

        // Adding simulationPanel and tuningPanel the main panel
        mainPanel.add(simulationPanel, BorderLayout.PAGE_START);
        mainPanel.add(tuningPanel, BorderLayout.PAGE_END);

    }

    public JFXPanel getMainPanel() {
        return mainPanel;
    }

    public void setPanelControllerSize(Dimension main_panel_dim) {
        // Setting dimensions and proportions of the panels
        // Simulation panel:
        main_panel_dim_ = main_panel_dim;
        simulation_panel_dim = simulationPanel.getSize();
        simulation_panel_dim.width = main_panel_dim_.width;
        simulation_panel_dim.height = (int) ((main_panel_dim_.height * s_t_ratio_num) / s_t_ratio_den);
        simulationPanel.setPreferredSize(simulation_panel_dim);

        // Graph panel:
        graph_panel_dim = graphPanel.getPreferredSize();
        graph_panel_dim.width = (int) ((simulation_panel_dim.width * s_v_ratio_num) / s_v_ratio_den);
        graph_panel_dim.height = simulation_panel_dim.height;
        graphPanel.setPreferredSize(graph_panel_dim);

        // Setting dimensions for the panels belonging to sub panels of the main panel
        sub_vitals_panel_dim = vitalsPanel.getSize();
        sub_vitals_panel_dim.width = (int) ((main_panel_dim_.width * (s_v_ratio_den - s_v_ratio_num)) / (s_v_ratio_den));
        sub_vitals_panel_dim.height = (int) ((main_panel_dim_.height * s_t_ratio_num) / (s_v_ratio_den * 4));

        BP_panel.setVitals_value_displaySize(sub_vitals_panel_dim);
        HR_panel.setVitals_value_displaySize(sub_vitals_panel_dim);
        ECG_panel.setVitals_value_displaySize(sub_vitals_panel_dim);
        RR_panel.setVitals_value_displaySize(sub_vitals_panel_dim);
    }

    public void updateController() {
        graphs.updatePanel();
        graphPanel = graphs.getGraphPanel();
    }
}


