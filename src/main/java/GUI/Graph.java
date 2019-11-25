package GUI;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;


public class Graph extends JFXPanel{
    private JFXPanel graphpanel;
    private Scene scene;

    public Graph() {
        graphpanel = new JFXPanel();
        NumberAxis xAxis = new NumberAxis("Values for X-Axis", 0, 3, 1);   //creating the axes
        NumberAxis yAxis = new NumberAxis("Values for Y-Axis", -2, 2, 1);

        LineChart<Number, Number> chart = new LineChart<Number, Number>(xAxis, yAxis); //creating the chart skeleton
        scene = new Scene(chart);
        XYChart.Series function = new XYChart.Series();

        //function.setName("Trying out");
        for (double i = 0; i <= 3; i += 0.1) {
            function.getData().add(new XYChart.Data(i, (1.3 * i)-2));
        }
        chart.getData().add(function);
        //chart.setCreateSymbols(false);

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

    public JFXPanel getGraph() {
        return graphpanel;
    }
}

//Look at updateLegend !! might be important when updating the graph in the simulation