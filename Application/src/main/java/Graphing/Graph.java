package Graphing;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.util.StringConverter;
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
    private int num_points_changed, numberOfPoints, points_added_whileStopped, windowSize;
    private int pointsThreshold, pointsDeleted;
    protected int series_pointer;
    private double delta;
    protected double data_point;

    private int where_zeros;
    private boolean add_zeros; //used for the scaling

    protected Value_Counter val_counter;


    public Graph(String colorGraph, Value_Counter obj, double sample_period, int time_shown) {
        series_pointer = 0; //This looks at which point in the series to add next
        numberOfPoints = 0; //How many points in the series
        points_added_whileStopped = 0;  //How many points added in the backend when the graph has stopped moving

        pointsThreshold = (int)(60/sample_period);  //When to start deleting points
        pointsDeleted = 1;  //After how many points to delete once the threshold has been passed

        delta = sample_period;
        lowerbound = 0;
        upperbound = time_shown;
        windowSize = time_shown;
        tick = windowSize/5;

        num_points_changed = 1;

        where_zeros = 0;
        add_zeros = false;

        val_counter = obj;

        graphpanel = new JFXPanel();
        xAxis = new NumberAxis("Time", lowerbound+ROUNDING_VALUE, tick+ROUNDING_VALUE, tick+ROUNDING_VALUE);   //creating the axes
        //yAxis = new NumberAxis("Values for Y-Axis", -1, 1, 1);
        yAxis = new NumberAxis();
        yAxis.setForceZeroInRange(false);

        //Paying with the axis values
//        xAxis.setTickLabelFormatter(new StringConverter<Number>() {
//            @Override
//            public String toString(Number number) {
//                for(int i=0; i<=(int)((upperbound-lowerbound)/tick); i+=1){
//                    if (number.intValue() == (int)(lowerbound + i*tick))
//                        return Integer.toString((int) (-(upperbound - lowerbound) + i * tick)) + "s";
//                }
//                return null;
//            }
//
//            @Override
//            public Number fromString(String s) { return null; }
//        });
        xAxis.setTickLabelFormatter(new StringConverter<Number>() {
            @Override
            public String toString(Number number) {
                int which_tick = (int)Math.round(number.doubleValue() - lowerbound);

                return Integer.toString(which_tick - windowSize);
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

//        for (double i = 0; i <= 5; i += delta) {
//            function.getData().add(new XYChart.Data<Number, Number>(i, data_points[point_pointer]));
//            val_counter.Count_bpm(data_points[point_pointer], point_pointer);
//            point_pointer += 1;
//        }

        function.getData().add(new XYChart.Data<Number, Number>(-60, 0));
        function.getData().add(new XYChart.Data<Number, Number>(upperbound, 0));
        series_pointer += upperbound/delta + 1;
        numberOfPoints += 2;

//        for (double i = 0; i <= upperbound; i += delta) {
////            function.getData().add(new XYChart.Data<Number, Number>(i, 0));
//            series_pointer += 1;
//            numberOfPoints += 1;
//        }

        chart.getData().add(function);
        chart.getStyleClass().add(colorGraph);
        chart.getStylesheets().add("file:" + System.getProperty("user.dir").toString().replace("\\", "/").replace(" ", "%20") + "/Application/src/main/java/CSS/graph.css");

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

    public void stopUpdating(){
        Platform.runLater(() ->{
            stopTheUpdate();
        });
    }

    public void restartUpdating(){
        Platform.runLater(() ->{
            restartTheUpdate();
        });
    }

    public void changeTimeWindow(int val){
        Platform.runLater(() ->{
            changeTheTimeWindow(val);
        });
    }

    private void updateTheGraph() {
        chart.setAnimated(false);

        for (int i=0; i<num_points_changed; i+=1) {
            double x = series_pointer*delta;
            Get_Next_Value();
            function.getData().add(new XYChart.Data<Number, Number>(x, data_point));
            Monitoring_Value();
            series_pointer += 1;
            numberOfPoints += 1;
        }
//        System.out.println(function.getData().size());

        xAxis.setAnimated(false);
        lowerbound += num_points_changed*delta;
        upperbound += num_points_changed*delta;
        xAxis.setLowerBound(lowerbound);
        xAxis.setUpperBound(upperbound);

//        System.out.println("lower"+lowerbound);
//        System.out.println("upper"+upperbound);
//        System.out.println("rouned:                  " + ((int)(upperbound)-(int)(lowerbound)) + "\n");

        if (numberOfPoints > pointsThreshold + pointsDeleted) {
            function.getData().remove(0, pointsDeleted);
            System.out.println(function.getData().size());
            numberOfPoints -= pointsDeleted;
            pointsDeleted = 30;
        }
        else
            scaling();

    }

    private void stopTheUpdate(){
        for (int i=0; i<num_points_changed; i+=1) {
            double x = series_pointer*delta;
            Get_Next_Value();
            function.getData().add(new XYChart.Data<Number, Number>(x, data_point));
            Monitoring_Value();
            series_pointer += 1;
            numberOfPoints += 1;
            points_added_whileStopped += 1;
        }
    }

    private void restartTheUpdate(){
        lowerbound += points_added_whileStopped*delta;
        upperbound += points_added_whileStopped*delta;
        xAxis.setUpperBound(upperbound);
        xAxis.setLowerBound(lowerbound);
        if (numberOfPoints > pointsThreshold){
            function.getData().remove(0, points_added_whileStopped);
            numberOfPoints -= points_added_whileStopped;
        }
        points_added_whileStopped = 0;
    }

    private void changeTheTimeWindow(int new_time_shown){
        lowerbound = upperbound - new_time_shown;
        xAxis.setLowerBound(lowerbound);
        windowSize = new_time_shown;
        tick = new_time_shown/5;
        xAxis.setTickUnit(tick+ROUNDING_VALUE);
    }

    public abstract void changeAbnormality (int newType);

    private void scaling(){
        if (((function.getData().size() - 2) > windowSize/0.006) && !add_zeros) {
            function.getData().remove(where_zeros, where_zeros + 2);
            numberOfPoints -= 2;
            add_zeros = true;
        }
        else if ((function.getData().size() <= windowSize/0.006) && add_zeros){
            function.getData().add(new XYChart.Data<Number, Number>(-60, 0));
            function.getData().add(new XYChart.Data<Number, Number>(5, 0));
            where_zeros = function.getData().size()-2;
            numberOfPoints += 2;
            add_zeros = false;
        }
    }

    protected abstract void Get_Next_Value();

    protected abstract void Monitoring_Value();

    public JFXPanel getGraph() {
        return graphpanel;
    }
}
