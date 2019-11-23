package GUI;

import javafx.embed.swing.JFXPanel;

import javax.swing.*;
import java.awt.*;

public class Overall_Graph {
    private JFXPanel graph_panel;
    private Graph graph1, graph2, graph3, graph4;

    public Overall_Graph() {
        graph_panel = new JFXPanel();
        graph_panel.setLayout(new GridLayout(4, 1));

        graph1 = new Graph();
        graph2 = new Graph();
        graph3 = new Graph();
        graph4 = new Graph();

        graph_panel.add(graph1.getGraph());
        graph_panel.add(graph2.getGraph());
        graph_panel.add(graph3.getGraph());
        graph_panel.add(graph4.getGraph());

    }

    public JFXPanel getGraphPanel() {
        return graph_panel;
    }

}
