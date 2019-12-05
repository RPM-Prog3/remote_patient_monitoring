package GUI;

import javafx.application.Application;
import javafx.application.Platform;

public class Refresh implements Runnable{
    private Graph graph;

    public Refresh(Graph graph) {
        this.graph =graph;
    }

    public void run() {
        try{
            //Thread.sleep(1000);
            while (1<2) {
                //System.out.println("adelante");
                graph.updateGraph();
                //System.out.println("beguante");
                Thread.sleep(50);
                //System.out.println("caliente");
//                System.gc();
            }

        }catch (Exception e) {System.out.println("casseruola");};
    }
}
