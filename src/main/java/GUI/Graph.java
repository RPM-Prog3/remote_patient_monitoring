package GUI;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.util.LinkedList;


public class Graph extends JFXPanel{
    private JFXPanel graphpanel;
    private Scene scene;
    private String colorGraph;

    private NumberAxis xAxis, yAxis;
    private LineChart<Number, Number> chart;
    private XYChart.Series function;
    private int lowerbound, upperbound, point_pointer;

    private LinkedList<Double> elementList;

    public Graph(String colorGraph) {
        point_pointer = 0;
        lowerbound = 0;
        upperbound = 100;

        graphpanel = new JFXPanel();
        xAxis = new NumberAxis("Values for X-Axis", lowerbound, upperbound, 1);   //creating the axes
        yAxis = new NumberAxis("Values for Y-Axis", -2, 2, 1);

        chart = new LineChart<Number, Number>(xAxis, yAxis); //creating the chart skeleton
        scene = new Scene(chart);
        function = new XYChart.Series();

        elementList = new LinkedList<Double>();

        this.colorGraph = colorGraph;
    }

    public void setGraph() {

        for (double i = 0; i <= 100; i += 0.1) {
            elementList.add(i);
        }

        function.setName("Trying out");
        for (int i = 0; i <= elementList.size()-1; i += 1) {
            double x = elementList.get(i);
            function.getData().add(new XYChart.Data(x, Math.sin(x)));
            point_pointer += 1;
        }

        chart.getData().add(function);

        chart.getStyleClass().add(colorGraph);
        chart.getStylesheets().add("file:/" + System.getProperty("user.dir").toString().replace("\\", "/").replace(" ", "%20") + "/src/main/java/GUI/graph.css");
        //chart.getStylesheets().add("file:/H:/Year3/Prog3_proj/remote_patient_monitoring/src/main/java/GUI/graph.css");

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                initFX(graphpanel, scene);
            }
        });
    }

    private static void initFX(JFXPanel fxPanel, Scene scena) {
        // This method is invoked on the JavaFX thread
        fxPanel.setScene(scena);
    }

    public void updateGraph() {
        lowerbound += 1;
        upperbound += 1;
        xAxis.setLowerBound(lowerbound);
        xAxis.setUpperBound(upperbound);

        function.getData().remove(0, 9);
        for (int i=0; i<10; i+=1) {
            double x = point_pointer*0.1;
            function.getData().add(new XYChart.Data(x, Math.sin(x)));
            point_pointer += 1;
        }
    }

    public JFXPanel getGraph() {
        return graphpanel;
    }
}

//Look at updateLegend !! might be important when updating the graph in the simulation