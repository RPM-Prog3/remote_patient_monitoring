package GUI;

import GUI.Vital_Values_Display;
import Graphing.Graph;

public class Refresh implements Runnable{
    private Graph graph;
    private Vital_Values_Display vital;
    private boolean isUpdating;

    public Refresh(Graph graph, Vital_Values_Display vital) {
        this.graph = graph;
        this.vital = vital;
        isUpdating = true;
    }

    public void run(){
        if (isUpdating) {
            try {

                while (1 < 2) {
                    graph.updateGraph();
                    vital.Set_Displayed_Value();
                    Thread.sleep(6);
                }

            } catch (Exception e) {};
        }
        else {
            try {

                while (1 < 2) {
                    graph.stopUpdating();
                    Thread.sleep(6);
                }

            } catch (Exception e) {};
        }

    }

    public void switchRun(){
        isUpdating = !isUpdating;
    }
}