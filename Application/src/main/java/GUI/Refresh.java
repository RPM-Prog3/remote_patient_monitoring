package GUI;

import Graphing.Graph;

public class Refresh implements Runnable{
    private Graph graph_ecg, graph_press, graph_resp, graph_temp;
    private Vital_Values_Display vital_ecg, vital_press, vital_resp, vital_temp;
    private volatile boolean isUpdating, haveToRestart, whenToRestart;

    public Refresh(Graph graph1, Graph graph2, Graph graph3, Graph graph4, Vital_Values_Display vital1, Vital_Values_Display vital2, Vital_Values_Display vital3, Vital_Values_Display vital4) {
        this.graph_ecg = graph1;
        this.graph_press = graph2;
        this.graph_resp = graph3;
        this.graph_temp = graph4;
        this.vital_ecg = vital1;
        this.vital_press = vital2;
        this.vital_resp = vital3;
        this.vital_temp = vital4;
        isUpdating = true;
        haveToRestart = false;
        whenToRestart = true;
    }

    public void run(){

            try {
                while (1 < 2) {
                    if (haveToRestart){
                        graph_ecg.restartUpdating();
                        graph_press.restartUpdating();
                        graph_resp.restartUpdating();
                        graph_temp.restartUpdating();
                        haveToRestart = false;
                    }

                    if (isUpdating){
                        graph_ecg.updateGraph();
                        graph_press.updateGraph();
                        graph_resp.updateGraph();
                        graph_temp.updateGraph();
                        vital_ecg.Set_Displayed_Value();
                        vital_press.Set_Displayed_Value();
                        vital_resp.Set_Displayed_Value();
                        vital_temp.Set_Displayed_Value();
                    }
                    else{
                        graph_ecg.stopUpdating();
                        graph_press.stopUpdating();
                        graph_resp.stopUpdating();
                        graph_temp.stopUpdating();
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