package GUI;

import javafx.application.Application;
import javafx.application.Platform;

public class Refresh implements Runnable{
    private Graph graph;

    public Refresh(Graph graph) {
        this.graph = graph;
    }

    public void run() {
        try{
            //Thread.sleep(1000);
            while (1<2) {
                graph.updateGraph();
                Thread.sleep(150);
            }

        }catch (Exception e) {};
    }
}
