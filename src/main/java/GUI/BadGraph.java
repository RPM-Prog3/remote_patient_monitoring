//package GUI;
//
//import javafx.application.Platform;
//import javafx.embed.swing.JFXPanel;
//import javafx.scene.Scene;
//import javafx.scene.chart.LineChart;
//import javafx.scene.chart.NumberAxis;
//import javafx.scene.chart.XYChart;
//
//import java.util.LinkedList;
//
//
//public class Graph extends JFXPanel{
//    private JFXPanel graphpanel;
//    private Scene scene;
//    private NumberAxis xAxis, yAxis;
//    private XYChart.Series<Number, Number> function;
//    private LineChart<Number, Number> chart;
//    private LinkedList<Double> elementList;
//
//    private int lowerbound, upperbound;
//
//    public Graph(String colorGraph) {
//        lowerbound = 0;
//        upperbound = 100;
//
//        graphpanel = new JFXPanel();
//        xAxis = new NumberAxis("Values for X-Axis", lowerbound, upperbound, 1);   //creating the axes
//        yAxis = new NumberAxis("Values for Y-Axis", -2, 2, 1);
//
//        chart = new LineChart<Number, Number>(xAxis, yAxis); //creating the chart skeleton
//
//        scene = new Scene(chart);
//        function = new XYChart.Series();
//
//        elementList = new LinkedList<Double>();
//
//        for (double i = 0; i <= 100; i += 0.1) {
//            elementList.add(i);
//        }
//
//        for (int i = 0; i <= elementList.size()-1; i += 1) {
//            double x = elementList.get(i);
//            function.getData().add(new XYChart.Data(x, Math.sin(x)));
//            //function.getData().add( i, Math.sin(x) );
//        }
//
//        chart.getData().add(function);
//
//        //chart.getStyleClass().add(colorGraph);
//        //chart.getStylesheets().add("file:/" + System.getProperty("user.dir").toString().replace("\\", "/").replace(" ", "%20") + "/src/main/java/GUI/graph.css");
//
//        Platform.runLater(new Runnable() {
//            @Override
//            public void run() {
//                initFX(graphpanel, scene);
//            }
//        });
//
//    }
//
//    public void setSeries () {
//        xAxis.setLowerBound(lowerbound);
//        xAxis.setUpperBound(upperbound);
//        function.getData().clear();
//
//        //function.setName("Trying out");
//        for (int i = 0; i <= elementList.size()-1; i += 1) {
//            double x = elementList.get(i);
//            function.getData().add(new XYChart.Data(x, Math.sin(x)));
//            //function.getData().add( i, Math.sin(x) );
//        }
//
//    }
//
//    public void updateGraph() {
//        System.out.println(elementList.size());
//        double limit = elementList.element();
//        lowerbound += 1;
//        upperbound += 1;
//        elementList.subList(0, 11).clear();
//        for (double i = limit + 0.1; i <= limit + 1; i += 0.1) {
//            elementList.add(i);
//        }
//        setSeries();
//    }
//
//    private static void initFX(JFXPanel fxPanel, Scene scena) {
//        // This method is invoked on the JavaFX thread
//        fxPanel.setScene(scena);
//    }
//
//    public JFXPanel getGraph() {
//        return graphpanel;
//    }
//}
//
////Look at updateLegend !! might be important when updating the graph in the simulation