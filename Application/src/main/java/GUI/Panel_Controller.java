package GUI;

import Graphing.Overall_Graph;
import Tuning.Tuning_Panel;
import javafx.embed.swing.JFXPanel;
import simulation.*;

import javax.swing.border.Border;
import java.awt.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Panel_Controller {
    private JFXPanel mainPanel, simulationPanel, tuningPanel, vitalsPanel;
    private JFXPanel graphPanel;
    private Overall_Graph graphs;
    private Tuning_Panel tunings;

    private BP_Vitals BP_panel;
    private RR_Vitals RR_panel;
    private ECG_Vitals ECG_panel;
    private TEMP_Vitals HR_panel;

    private Border border;
    private int s_v_ratio_num, s_v_ratio_den, s_t_ratio_num, s_t_ratio_den;
    private Dimension graph_panel_dim, simulation_panel_dim, main_panel_dim_, sub_vitals_panel_dim;

    private BPM bpm_counter;
    private Pressure_Counting press_counter;
    private Respiration_Counting resp_counter;
    private Temperature_Counting temp_counter;

    private ExecutorService exe;

    public Panel_Controller(Dimension main_panel_dim) {
        // Instatiating executor which will contain refresh and blinking runnables
        exe = Executors.newFixedThreadPool(5);

        // Instantiating objects to display value
        bpm_counter = new BPM();
        press_counter = new Pressure_Counting();
        resp_counter = new Respiration_Counting();
        temp_counter = new Temperature_Counting();

        // Instantiating main panel and the several sub panels
        mainPanel = new JFXPanel();
        tuningPanel = new JFXPanel();
        simulationPanel = new JFXPanel();
        vitalsPanel = new JFXPanel();

        this.main_panel_dim_ = main_panel_dim;
        s_v_ratio_num = 8; //simulation panel to vitals panel numerator value of ratio (in width)
        s_v_ratio_den = 10; //simulation panel to vitals panel denominator value of ratio (in width)
        s_t_ratio_num = 8; //simulation panel to tuning  panel numerator value of ratio (in height)
        s_t_ratio_den = 10; //simulation panel to tuning panel denominator value of ratio (in height)

        // Setting the layout of the main panel and of the several sub panels
        mainPanel.setLayout(new BorderLayout(0,0));
        simulationPanel.setLayout(new BorderLayout(0,0));
        vitalsPanel.setLayout(new GridLayout(4, 1, 0,0));

        // Making the various visible
        mainPanel.setVisible(true);
        tuningPanel.setVisible(true);
        simulationPanel.setVisible(true);
        vitalsPanel.setVisible(true);

        // Instantiating border object(s)
        //border = BorderFactory.createLineBorder(Color.black);

        // Setting contours of the different panels
        //simulationPanel.setBorder(border);
        //tuningPanel.setBorder(border);
        //vitalsPanel.setBorder(border);

        // Calculating the size of the sub panels of vitals panel that will contain the values
        // of the vital signs. This is then fed into the constructor of the various vital signs classes.
        sub_vitals_panel_dim = vitalsPanel.getSize();
        sub_vitals_panel_dim.width = (int)((main_panel_dim_.width*(s_v_ratio_den - s_v_ratio_num))/(s_v_ratio_den));
        sub_vitals_panel_dim.height = (int)((main_panel_dim_.height*s_t_ratio_num)/(s_v_ratio_den*4));

        BP_panel = new BP_Vitals(sub_vitals_panel_dim, press_counter, exe);
        RR_panel = new RR_Vitals(sub_vitals_panel_dim, resp_counter, exe);
        ECG_panel = new ECG_Vitals(sub_vitals_panel_dim, bpm_counter, exe);
        HR_panel = new TEMP_Vitals(sub_vitals_panel_dim, temp_counter, exe);

        // Instantiating graphs and Setting graphPanel
        graphs = new Overall_Graph(bpm_counter, ECG_panel, temp_counter, HR_panel,press_counter, BP_panel, resp_counter, RR_panel, exe);
        graphPanel = graphs.getGraphPanel();
        graphPanel.setLayout(new GridLayout(4, 1,0,0));
        graphPanel.setVisible(true);

        // Instantiating tunings and Setting tuningPanel
        tunings = new Tuning_Panel(graphs);
        tuningPanel = tunings.getTuningPanel();

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
        mainPanel.add(tuningPanel, BorderLayout.CENTER);
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

//        BP_panel.setVitals_value_displaySize(sub_vitals_panel_dim);
//        HR_panel.setVitals_value_displaySize(sub_vitals_panel_dim);
//        ECG_panel.setVitals_value_displaySize(sub_vitals_panel_dim);
//        RR_panel.setVitals_value_displaySize(sub_vitals_panel_dim);
    }

    public double[] getMeans(){
        double[] array = new double[5];
        array[0] = ECG_panel.getMean();
        array[1] = BP_panel.getMean()[0];
        array[2] = BP_panel.getMean()[1];
        array[3] = RR_panel.getMean();
        array[4] = HR_panel.getMean();
        return array;
    }

    public int[] getStatus(){
        int[] array = new int[4];
        array[0] = ECG_panel.getMeanStatus();
        array[1] = BP_panel.getMeanStatus();
        array[2] = RR_panel.getMeanStatus();
        array[3] = HR_panel.getMeanStatus();
        return array;
    }

    public void startSimulation() {
        graphs.simulate();
//        graphPanel = graphs.getGraphPanel();
    }

    public void closeProgram(){
        graphs.stopTheThread();
        ECG_panel.stopThread();
        BP_panel.stopThread();
        RR_panel.stopThread();
        HR_panel.stopThread();
        exe.shutdown();
        try {
            if (!exe.awaitTermination(10, TimeUnit.MINUTES))
                exe.shutdownNow();
        }catch (InterruptedException e){
            System.out.println("Executer failed to wait termination");
        };
        if (exe.isShutdown()) {
            System.exit(0);
        }
    }
}