package GUI;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;

import javax.swing.*;
import java.awt.*;

public class Overall_Graph {
    private JFXPanel graph_panel;  //Overall panel with the four graphs
    private Graph graphECG, graphBPress, graphResp, graphTemp;  //These are the four different graphs
    private Thread refreshing1,refreshing2, refreshing3, refreshing4;  //These creates four different threads

    public Overall_Graph() {
        graph_panel = new JFXPanel();
        graph_panel.setLayout(new GridLayout(4, 1));

        graphECG = new Graph("chart-ECG");
//        graphBPress = new Graph("chart-Pressure");
//        graphResp = new Graph("chart-Respiratory");
//        graphTemp = new Graph("chart-Temperature");

        graph_panel.add(graphECG.getGraph());
//        graph_panel.add(graphBPress.getGraph());
//        graph_panel.add(graphResp.getGraph());
//        graph_panel.add(graphTemp.getGraph());

        graphECG.setGraph();
//        graphBPress.setGraph();
//        graphResp.setGraph();
//        graphTemp.setGraph();

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
