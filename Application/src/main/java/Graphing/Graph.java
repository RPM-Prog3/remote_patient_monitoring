package Graphing;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;
import simulation.BPM;
import simulation.ECG;
import simulation.Value_Counter;

public abstract class Graph extends JFXPanel {
    private JFXPanel graphpanel;
    private Scene scene;
    private String colorGraph;

    private NumberAxis xAxis, yAxis;
    private float tick;
    static final double ROUNDING_VALUE = 0.00001;
    private float lowerbound, upperbound;

    private LineChart<Number, Number> chart;
    private XYChart.Series<Number, Number> function;
    private int num_points_changed, series_pointer;
    protected int point_pointer;
    private double delta;
    protected double[] data_points;

    private int roundedNumTicks;

    protected Value_Counter val_counter;


    public Graph(String colorGraph, Value_Counter obj, double sample_period, float time_shown) {
        series_pointer = 0; //This looks at whcih point in the series to add next
        point_pointer = 0;  //This looks at which index in the input data must be added next

        delta = sample_period;
        lowerbound = 0;
        upperbound = time_shown;
        tick = upperbound/5;

        num_points_changed = 1;
        //RoundNumTicks();

        val_counter = obj;

        graphpanel = new JFXPanel();
        xAxis = new NumberAxis("Time", lowerbound+ROUNDING_VALUE, upperbound+ROUNDING_VALUE, tick+ROUNDING_VALUE);   //creating the axes
//        yAxis = new NumberAxis("Values for Y-Axis", -1, 1, 1);
        yAxis = new NumberAxis();
        yAxis.setForceZeroInRange(false);

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

//        for (double i = 0; i <= 5; i += delta) {
//            function.getData().add(new XYChart.Data<Number, Number>(i, data_points[point_pointer]));
//            val_counter.Count_bpm(data_points[point_pointer], point_pointer);
//            point_pointer += 1;
//        }

        for (double i = 0; i <= 5; i += delta) {
            function.getData().add(new XYChart.Data<Number, Number>(i, 0));
            series_pointer += 1;
        }

        System.out.println(point_pointer);

        chart.getData().add(function);
        chart.getStyleClass().add(colorGraph);
        chart.getStylesheets().add("file:" + System.getProperty("user.dir").toString().replace("\\", "/").replace(" ", "%20") + "/Application/src/main/java/Graphing/graph.css");

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
            double x = series_pointer*delta;
            function.getData().add(new XYChart.Data<Number, Number>(x, data_points[point_pointer]));
            Monitoring_Value();
            point_pointer += 1;
            series_pointer += 1;
        }

        xAxis.setAnimated(false);
        lowerbound += num_points_changed*delta;
        upperbound += num_points_changed*delta;
        xAxis.setLowerBound(lowerbound);
        xAxis.setUpperBound(upperbound);
        //RoundNumTicks();

//        System.out.println("lower"+lowerbound);
//        System.out.println("upper"+upperbound);
//        System.out.println("rouned:                  " + ((int)(upperbound)-(int)(lowerbound)) + "\n");

        function.getData().remove(0, num_points_changed);

    }

//    private void RoundNumTicks(){
//        roundedNumTicks = 0;
//        if (lowerbound-(int)lowerbound >= 0.5)
//            roundedNumTicks += (int)lowerbound + 1;
//        else
//            roundedNumTicks += (int)lowerbound;
//
//        if (upperbound-(int)upperbound >= 0.5)
//            roundedNumTicks += (int)upperbound + 1;
//        else
//            roundedNumTicks += (int)upperbound;
//
//        roundedNumTicks = roundedNumTicks/tick;
//    }

    protected abstract void Monitoring_Value();

    public JFXPanel getGraph() {
        return graphpanel;
    }
}
