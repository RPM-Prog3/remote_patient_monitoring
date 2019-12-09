package GUI;

import Temperature_Sim.RandomWalk;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import simulation.ECG;

import javax.swing.*;
import java.awt.*;

public class Overall_Graph {
    private JFXPanel graph_panel;  //Overall panel with the four graphs
    private Graph graphECG, graphBPress, graphResp, graphTemp;  //These are the four different graphs
    private Thread refreshing1,refreshing2, refreshing3, refreshing4;  //These creates four different threads

    private RandomWalk data_Temp;
    private ECG ecgdata = new ECG();

    public Overall_Graph() {
        data_Temp = new RandomWalk();


//        System.out.println(data_Temp.getPoints()[5]);

        double[] sin_array = new double[13000];
        int index_counter = 0;
        for (double i = 0; i < 1299.9; i += 0.1) {
            sin_array[index_counter] = Math.sin(i);
            index_counter += 1;
        }

        graph_panel = new JFXPanel();
        graph_panel.setLayout(new GridLayout(4, 1));

        graphECG = new Graph("chart-ECG");
        graphBPress = new Graph("chart-Pressure");
        graphResp = new Graph("chart-Respiratory");
        graphTemp = new Graph("chart-Temperature");

        graph_panel.add(graphECG.getGraph());
        graph_panel.add(graphBPress.getGraph());
        graph_panel.add(graphResp.getGraph());
        graph_panel.add(graphTemp.getGraph());

        graphECG.setGraph(data_Temp.getPoints());
        graphBPress.setGraph(ecgdata.Simulate());
        graphResp.setGraph(sin_array);
        graphTemp.setGraph(sin_array);

    }

    public void updatePanel() {
        //Running four separate threads, one for each graph
        refreshing1 = new Thread (new Refresh (graphECG));
        refreshing2 = new Thread (new Refresh (graphBPress));
        refreshing3 = new Thread (new Refresh (graphResp));
        refreshing4 = new Thread (new Refresh (graphTemp));

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
