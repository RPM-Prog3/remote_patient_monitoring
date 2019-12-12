package GUI;

import javafx.application.Application;
import javafx.application.Platform;

public class Refresh implements Runnable{
    private Graph graph;
    private Vital_Values_Display vital;

    public Refresh(Graph graph, Vital_Values_Display vital) {
        this.graph = graph;
        this.vital = vital;
    }

//    public Refresh(Graph graph) {
//        this.graph =graph;
//    }

    public void run() {
        try{

            while (1<2) {
                graph.updateGraph();
                vital.Set_Displayed_Value();
                Thread.sleep(6);
            }

        }catch (Exception e) {};
    }
}