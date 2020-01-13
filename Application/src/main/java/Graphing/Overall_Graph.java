package Graphing;

import GUI.*;
import javafx.embed.swing.JFXPanel;
import simulation.*;

import java.awt.*;

public class Overall_Graph {
    private JFXPanel graph_panel;  //Overall panel with the four graphs
    private Graph graphECG, graphBPress, graphResp, graphTemp;  //These are the four different graphs
    private Value_Counter bpm_obj, press_counting_obj, resp_counting_obj, temp_counting_obj;

    private Refresh refreshing;
    private Thread thread_graphs;  //These creates a different thread for the graphs

    private ECG ecgdata;
    private ECG_Vitals ecg_vit;

    private Body_Temp tempdata;
    private TEMP_Vitals temp_vit; //HR is actually temperature

    private Blood_Pressure pressuredata;
    private BP_Vitals pressure_vit;

    private Resp_Rate respdata;
    private RR_Vitals resp_vit;

    private String ecg_type = "normal";

    public Overall_Graph(BPM ecg_obj_input, ECG_Vitals ecg_vit_input, Temperature_Counting temp_counting_obj_input, TEMP_Vitals temp_vit_input, Pressure_Counting press_counting_obj_input, BP_Vitals pressure_vit_input, Respiration_Counting resp_counting_obj_input, RR_Vitals resp_vit_input) {

        //Temperature
        tempdata = new Body_Temp(37, 0.01, 0.5);
        temp_vit = temp_vit_input;
        temp_counting_obj = temp_counting_obj_input;

        // ECG
        ecgdata = new ECG();
        ecg_vit = ecg_vit_input;
        bpm_obj = ecg_obj_input;

        //Pressure
        pressuredata = new Blood_Pressure(0.6, 0.15, 0.25, 100, 1);
        pressure_vit = pressure_vit_input;
        press_counting_obj = press_counting_obj_input;

        //Respiratory
        //Decrease the period to increase Respiratory Rate
        respdata = new Resp_Rate(50, 2.0, -0.95, 100);
        resp_vit = resp_vit_input;
        resp_counting_obj = resp_counting_obj_input;

        graph_panel = new JFXPanel();
        graph_panel.setLayout(new GridLayout(4, 1));

        graphECG = new Graph_ECG("chart-ECG", bpm_obj, 0.006, 5, ecgdata);
        graphBPress = new Graph_Pressure("chart-Pressure", press_counting_obj, 0.006, 5, pressuredata);
        graphResp = new Graph_Respiration("chart-Respiratory", resp_counting_obj, 0.006, 5, respdata);
        graphTemp = new Graph_Temperature("chart-Temperature", temp_counting_obj, 0.006, 5, tempdata);  //bpm object shouldn't be there

        graph_panel.add(graphECG.getGraph());
        graph_panel.add(graphBPress.getGraph());
        graph_panel.add(graphResp.getGraph());
        graph_panel.add(graphTemp.getGraph());

        graphECG.setGraph();
        graphBPress.setGraph();
        graphResp.setGraph();
        graphTemp.setGraph();

        //Instantiating the thread sub-class
        refreshing = new Refresh (graphECG, graphBPress, graphResp, graphTemp, ecg_vit, pressure_vit, resp_vit, temp_vit);

    }

    public void simulate() {
        //Running the four graphs on a separate thread
        thread_graphs = new Thread(refreshing);

        //This makes sure that the program doesn't have to wait for each thread to be run
//        Platform.runLater(new Runnable() {
//            @Override
//            public void run () {
                thread_graphs.start();
//            }
//        });
    }

    public void switchStopStart(){
//        Platform.runLater(new Runnable() {
//            @Override
//            public void run () {
                refreshing.switchRun();
//            }
//        });
    }

    public void stopTheThread(){
        refreshing.stopThread();
    }

    public void changeTimeWindow(int val){
        graphECG.changeTimeWindow(val);
        graphBPress.changeTimeWindow(val);
        graphResp.changeTimeWindow(val);
        graphTemp.changeTimeWindow(val);
    }

    public void changeECGAbnormality(int newType, int which_vital){
        if (which_vital == 1)
            graphECG.changeAbnormality(newType);
        if (which_vital == 2)
            graphBPress.changeAbnormality(newType);
        if (which_vital == 3)
            graphResp.changeAbnormality(newType);
        if (which_vital == 4)
            graphTemp.changeAbnormality(newType);
    }

    public JFXPanel getGraphPanel() {
        return graph_panel;
    }

}