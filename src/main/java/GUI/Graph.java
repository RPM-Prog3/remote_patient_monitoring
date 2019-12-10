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
    private double tick;
    static final double ROUNDING_VALUE = 0.00001;
    private double lowerbound, upperbound;

    private LineChart<Number, Number> chart;
    private XYChart.Series<Number, Number> function;
    private int num_points_changed, point_pointer;
    private double delta;
    private double[] data_points;


    public Graph(String colorGraph) {
        point_pointer = 0;  //This looks at which index must be added next
        lowerbound = 0;
        upperbound = 50;
        tick = 10;

        delta = 0.1;
        num_points_changed = 15;

        graphpanel = new JFXPanel();
        xAxis = new NumberAxis("Time", lowerbound+ROUNDING_VALUE, upperbound+ROUNDING_VALUE, tick+ROUNDING_VALUE);   //creating the axes
//        yAxis = new NumberAxis("Values for Y-Axis", 35, 40, 1);
        yAxis = new NumberAxis();

        //Paying with the axis values
        xAxis.setTickLabelFormatter(new StringConverter<Number>() {
            @Override
            public String toString(Number number) {
                for(int i=0; i<=(int)((upperbound-lowerbound)/tick); i+=1){
                    if (number.intValue() == (int)(lowerbound + i*tick))
                        return Integer.toString((int) (-(upperbound - lowerbound) + i * tick)) + "s";
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

    public void setGraph(double[] input_data) {

        data_points = input_data;

        for (int i = 0; i<100; i+=1)
            for (int ii=0; ii<30; ii+=1){
                System.out.println(data_points[i+ii]);
            }

        for (double i = 0; i <= 50; i += delta) {
            function.getData().add(new XYChart.Data<Number, Number>(i, data_points[point_pointer]));
            point_pointer += 1;
        }

        chart.getData().add(function);
        chart.getStyleClass().add(colorGraph);
        chart.getStylesheets().add("file:/" + System.getProperty("user.dir").toString().replace("\\", "/").replace(" ", "%20") + "/src/main/java/GUI/graph.css");

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                setTheScene(graphpanel, scene);
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

        for (int i=0; i<num_points_changed; i+=1) {
            double x = point_pointer*delta;
            function.getData().add(new XYChart.Data<Number, Number>(x, data_points[point_pointer]));
            point_pointer += 1;
        }

        xAxis.setAnimated(false);
        lowerbound += num_points_changed*delta;
        upperbound += num_points_changed*delta;
        xAxis.setLowerBound(lowerbound);
        xAxis.setUpperBound(upperbound);

        function.getData().remove(0, num_points_changed);

    }

    public JFXPanel getGraph() {
        return graphpanel;
    }
}