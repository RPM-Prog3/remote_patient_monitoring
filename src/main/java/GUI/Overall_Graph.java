package GUI;

import javafx.embed.swing.JFXPanel;

import javax.swing.*;
import java.awt.*;

public class Overall_Graph {
    private JFXPanel graph_panel;
    private Graph graphECG, graphBPress, graphResp, graphTemp;

    public Overall_Graph() {
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

        graphECG.setGraph();
        graphBPress.setGraph();
        graphResp.setGraph();
        graphTemp.setGraph();

    }

    public void updatePanel() {
        graphECG.updateGraph();
//        graphBPress.updateGraph();
//        graphResp.updateGraph();
//        graphTemp.updateGraph();
//        int timer = 0;
//        for (int i=0; i<=100000; i+=1){
//            timer += 1;
//            //System.out.println("ahahahahahahahahahahahahahahahahahahahhahahahahahhahahahahahahahahahahahahahahhaaha");
//        }
//       System.out.println("ahahahahahahahahahahahahahahahahahahahhahahahahahhahahahahahahahahahahahahahahhaaha");
    }

    public JFXPanel getGraphPanel() {
        return graph_panel;
    }

}
