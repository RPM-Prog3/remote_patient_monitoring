package GUI;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;

public class Graph extends JFXPanel {
    private JFXPanel graphpanel;
    private Scene scene;
    private String colorGraph;

    private NumberAxis xAxis, yAxis;
    private LineChart<Number, Number> chart;
    private XYChart.Series<Number, Number> function;
    private int point_pointer;
    private double lowerbound, upperbound;

    //used for axis
    private double tick;

    public Graph(String colorGraph) {
        point_pointer = 0;  //This looks at which index must be added next
        lowerbound = 0.5;
        upperbound = 100.5;
        tick = 10.5;

        graphpanel = new JFXPanel();
        xAxis = new NumberAxis("Time", lowerbound, upperbound, tick);   //creating the axes
        System.out.println("sunshine!");
        yAxis = new NumberAxis("Values for Y-Axis", -2, 2, 1);

        //Paying with the axis values
        xAxis.setTickLabelFormatter(new StringConverter<Number>() {
            @Override
            public String toString(Number number) {
                System.out.println(number);
                for(int i=0; i<=(upperbound-lowerbound)/10; i+=1){
                    if (number.doubleValue() == lowerbound + i*tick)
                        return Integer.toString((int)(-(upperbound-lowerbound) + i*tick)) + "s";
                }
                return null;
            }

            @Override
            public Number fromString(String s) { return null; }
        });

        chart = new LineChart<Number, Number>(xAxis, yAxis); //creating the chart skeleton
        scene = new Scene(chart);
        function = new XYChart.Series<Number, Number>();

        this.colorGraph = colorGraph;
    }

    public void setGraph() {
        //function.setName("Trying out");
        for (double i = 0; i <= 100; i += 0.1) {
            function.getData().add(new XYChart.Data<Number, Number>(i, Math.sin(i)));
            point_pointer += 1;
        }

        chart.getData().add(function);
        chart.getStyleClass().add(colorGraph);
        chart.getStylesheets().add("file:/" + System.getProperty("user.dir").toString().replace("\\", "/").replace(" ", "%20") + "/src/main/java/GUI/graph.css");

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                setTheScene(graphpanel, scene);
                System.out.println("rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
            }
        });
    }

    private static void setTheScene(JFXPanel fxPanel, Scene scena) {
        // This method is invoked on the JavaFX thread
        fxPanel.setScene(scena);
    }

    public void updateGraph() {
        Platform.runLater(() ->{
            updateTheGraph();
        });
    }

    private void updateTheGraph() {
        chart.setAnimated(false);

        for (int i=0; i<15; i+=1) {
            double x = point_pointer*0.1;
            function.getData().add(new XYChart.Data<Number, Number>(x, Math.sin(x)));
            point_pointer += 1;
        }

        xAxis.setAnimated(false);
        lowerbound += 1.5;
        upperbound += 1.5;
        xAxis.setLowerBound(lowerbound);
        xAxis.setUpperBound(upperbound);
        System.out.println("nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn");

        function.getData().remove(0, 15);

    }

    public JFXPanel getGraph() {
        return graphpanel;
    }
}