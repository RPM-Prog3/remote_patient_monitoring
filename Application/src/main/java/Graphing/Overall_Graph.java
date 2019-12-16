package Graphing;

import GUI.*;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import simulation.*;

import java.awt.*;

public class Overall_Graph {
    private JFXPanel graph_panel;  //Overall panel with the four graphs
    private Graph graphECG, graphBPress, graphResp, graphTemp;  //These are the four different graphs
    private Thread refreshing1,refreshing2, refreshing3, refreshing4;  //These creates four different threads
    private Value_Counter bpm_obj, press_counting_obj, resp_counting_obj, temp_counting_obj;

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
        tempdata = new Body_Temp(37, 0.01, 0.5, 300000);
        temp_vit = temp_vit_input;
        temp_counting_obj = temp_counting_obj_input;

        // ECG
        ecgdata = new ECG();
        ecg_vit = ecg_vit_input;
        bpm_obj = ecg_obj_input;

        //Pressure
        pressuredata = new Blood_Pressure(300000);
        pressure_vit = pressure_vit_input;
        press_counting_obj = press_counting_obj_input;

        //Respiratory
        respdata = new Resp_Rate(50, 2.0, -0.95, 20, 300000);
        resp_vit = resp_vit_input;
        resp_counting_obj = resp_counting_obj_input;

        //Just a sine used for check up and debugging, don't need it
//        double[] sin_array = new double[13000];
//        int index_counter = 0;
//        for (double i = 0; i < 1299.9; i += 0.1) {
//            sin_array[index_counter] = Math.sin(i);
//            index_counter += 1;
//        }

        graph_panel = new JFXPanel();
        graph_panel.setLayout(new GridLayout(4, 1));

        graphECG = new Graph_ECG("chart-ECG", bpm_obj, 0.006, 5);
        graphBPress = new Graph_Pressure("chart-Pressure", press_counting_obj, 0.006, 5);
        graphResp = new Graph_Respiration("chart-Respiratory", resp_counting_obj, 0.006, 5);
        graphTemp = new Graph_Temperature("chart-Temperature", temp_counting_obj, 0.006, 5);  //bpm object shouldn't be there

        graph_panel.add(graphECG.getGraph());
        graph_panel.add(graphBPress.getGraph());
        graph_panel.add(graphResp.getGraph());
        graph_panel.add(graphTemp.getGraph());

        graphECG.setGraph(ecgdata.Simulate(0));
        graphBPress.setGraph(pressuredata.get_array());
        graphResp.setGraph(respdata.get_array());
        graphTemp.setGraph(tempdata.get_array());
        //System.out.println(Arrays.toString(respdata.get_array()));

    }

    public void updatePanel() {
        //Running four separate threads, one for each graph
        refreshing1 = new Thread (new Refresh(graphECG, ecg_vit));
        refreshing2 = new Thread (new Refresh (graphBPress, pressure_vit));
        refreshing3 = new Thread (new Refresh (graphResp, resp_vit));
        refreshing4 = new Thread (new Refresh (graphTemp, temp_vit));

        //This makes sure that the program doesn't have to wait for each thread to be run
        Platform.runLater(new Runnable() {
            @Override
            public void run () {
                refreshing1.start();
                refreshing2.start();
                refreshing3.start();
                refreshing4.start();
            }
        });
    }

    public JFXPanel getGraphPanel() {
        return graph_panel;
    }

}