package GUI;

import GUI.Vital_Values_Display;
import Graphing.Graph;

public class Refresh implements Runnable{
    private Graph graph1, graph2, graph3, graph4;
    private Vital_Values_Display vital;
    private volatile boolean isUpdating, haveToRestart, whenToRestart;

    public Refresh(Graph graph1, Graph graph2, Graph graph3, Graph graph4, Vital_Values_Display vital) {
        this.graph1 = graph1;
        this.graph2 = graph2;
        this.graph3 = graph3;
        this.graph4 = graph4;
        this.vital = vital;
        isUpdating = true;
        haveToRestart = false;
        whenToRestart = true;
    }

    public void run(){

            try {
                while (1 < 2) {
                    if (haveToRestart){
                        graph1.restartUpdating();
                        graph2.restartUpdating();
                        graph3.restartUpdating();
                        graph4.restartUpdating();
                        haveToRestart = false;
                    }

                    if (isUpdating){
                        graph1.updateGraph();
                        graph2.updateGraph();
                        graph3.updateGraph();
                        graph4.updateGraph();
                        vital.Set_Displayed_Value();
                    }
                    else{
                        graph1.stopUpdating();
                        graph2.stopUpdating();
                        graph3.stopUpdating();
                        graph4.stopUpdating();
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