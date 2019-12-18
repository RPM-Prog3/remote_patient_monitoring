package GUI;

import GUI.Vital_Values_Display;
import Graphing.Graph;

public class Refresh implements Runnable{
    private Graph graph;
    private Vital_Values_Display vital;
    private volatile boolean isUpdating, haveToRestart, whenToRestart;

    public Refresh(Graph graph, Vital_Values_Display vital) {
        this.graph = graph;
        this.vital = vital;
        isUpdating = true;
        haveToRestart = false;
        whenToRestart = true;
    }

    public void run(){

            try {
                while (1 < 2) {
                    if (haveToRestart){
                        graph.restartUpdating();
                        haveToRestart = false;
                    }

                    if (isUpdating){
                        graph.updateGraph();
                        vital.Set_Displayed_Value();
                    }
                    else{
                        graph.stopUpdating();
                        Thread.sleep(6);
                    }
                    Thread.sleep(6);
                }
            } catch (Exception e) {};
    }

    public void switchRun(){
        System.out.println("seeee");
        isUpdating = !isUpdating;
        haveToRestart = !whenToRestart;
        whenToRestart = !whenToRestart;
    }
}