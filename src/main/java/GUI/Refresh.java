package GUI;

import javafx.application.Application;
import javafx.application.Platform;

public class Refresh implements Runnable{
    private Graph graph;
    private ECG_Vitals ecg_vit;

    public Refresh(Graph graph, ECG_Vitals ecg_vit) {
        this.graph =graph;
        this.ecg_vit = ecg_vit;
    }

    public void run() {
        try{
            //Thread.sleep(1000);
            while (1<2) {
                graph.updateGraph();
                ecg_vit.Set_Displayed_Value();
                Thread.sleep(6);
            }

        }catch (Exception e) {};
    }
}
