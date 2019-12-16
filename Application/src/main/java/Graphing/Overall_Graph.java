package Graphing;

import GUI.*;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import simulation.*;

import java.awt.*;

public class Overall_Graph {
    private JFXPanel graph_panel;  //Overall panel with the four graphs
    private Graph graphECG, graphBPress, graphResp, graphTemp;  //These are the four different graphs
    private Value_Counter bpm_obj, press_counting_obj, resp_counting_obj, temp_counting_obj;

    private Refresh refreshing1,refreshing2, refreshing3, refreshing4;
    private Thread thread_ecg, thread_press, thread_resp, thread_temp;  //These creates four different threads

    private ECG ecgdata;
    private ECG_Vitals ecg_vit;

    private Body_Temp tempdata;
    private HR_Vitals temp_vit; //HR is actually temperature

    private Blood_Pressure pressuredata;
    private BP_Vitals pressure_vit;

    private Resp_Rate respdata;
    private RR_Vitals resp_vit;

    public Overall_Graph(BPM ecg_obj_input, ECG_Vitals ecg_vit_input,Temperature_Counting temp_counting_obj_input, HR_Vitals temp_vit_input, Pressure_Counting press_counting_obj_input, BP_Vitals pressure_vit_input, Respiration_Counting resp_counting_obj_input, RR_Vitals resp_vit_input) {

        //Temperature
        tempdata = new Body_Temp(37, 0.01, 0.5);
        temp_vit = temp_vit_input;
        temp_counting_obj = temp_counting_obj_input;

        // ECG
        ecgdata = new ECG();
        ecg_vit = ecg_vit_input;
        bpm_obj = ecg_obj_input;

        //Pressure
        pressuredata = new Blood_Pressure();
        pressure_vit = pressure_vit_input;
        press_counting_obj = press_counting_obj_input;

        //Respiratory
        respdata = new Resp_Rate(50, 2.0, -0.95, 20);
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
        refreshing1 = new Refresh (graphECG, ecg_vit);
        refreshing2 = new Refresh (graphBPress, pressure_vit);
        refreshing3 = new Refresh (graphResp, resp_vit);
        refreshing4 = new Refresh (graphTemp, temp_vit);

    }

    public void updatePanel() {
        //Running four separate threads, one for each graph
        thread_ecg = new Thread(refreshing1);
        thread_press = new Thread(refreshing2);
        thread_resp = new Thread(refreshing3);
        thread_temp = new Thread(refreshing4);

        //This makes sure that the program doesn't have to wait for each thread to be run
        Platform.runLater(new Runnable() {
            @Override
            public void run () {
                thread_ecg.start();
                thread_press.start();
                thread_resp.start();
                thread_temp.start();
            }
        });
    }

    public void stopUpdating(){
        Platform.runLater(new Runnable() {
            @Override
            public void run () {
                refreshing1.switchRun();
                refreshing2.switchRun();
                refreshing3.switchRun();
                refreshing4.switchRun();
            }
        });
    }

    public JFXPanel getGraphPanel() {
        return graph_panel;
    }

}