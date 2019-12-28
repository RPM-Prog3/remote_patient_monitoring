package GUI;

import Graphing.Overall_Graph;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.util.StringConverter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

public class Tuning_Panel{
    private JFXPanel tuning_panel;
    private javafx.scene.control.Label time_axis_label;
    private GridPane gpane;
    private Scene tuning_scene;

    private Button switch_graph_motion;
    private Slider timeWindow;

    private Overall_Graph graphs_panel;

    public Tuning_Panel(Overall_Graph obj){
        // Instantiating and setting the Grid pane that will contain all the tuning pieces
        gpane = new GridPane();
        gpane.setPadding(new Insets(10, 10, 10, 10));  //this sets the distance from the borders of the grid we want to create

        tuning_panel = new JFXPanel();
        tuning_scene = new Scene(gpane);

        switch_graph_motion = new Button("Stop Graph");
        timeWindow = new Slider();
        time_axis_label = new javafx.scene.control.Label();
        time_axis_label.setText("Slider to change window size");

        graphs_panel = obj;

        Platform.runLater (new Runnable() {
            @Override
            public void run() { setPanel();}
        });

    }

    private void setPanel(){
        actionPerformed(switch_graph_motion, "stop_or_start_graph");
        switch_graph_motion.setVisible(true);

        timeWindow.setMin(5);
        timeWindow.setMax(60);
        timeWindow.setValue(5);
        timeWindow.setShowTickLabels(true);
        timeWindow.setShowTickMarks(false);
        timeWindow.setMajorTickUnit(1);
        timeWindow.setLabelFormatter(new StringConverter<Double>() {
            @Override
            public String toString(Double aDouble) {
                if (aDouble == 20 || aDouble == 40 || aDouble == 5 || aDouble == 60)
                    return Integer.toString((int)aDouble.doubleValue()) ;
                else
                    return null;
            }

            @Override
            public Double fromString(String s) {
                return null;
            }
        });
        timeWindow.valueProperty().addListener((obs, oldval, newval) -> {
            if (oldval.intValue() < 13)
                timeWindow.setValue(5);
            else if (oldval.intValue()<30)
                timeWindow.setValue(20);
            else if (oldval.intValue()<50)
                timeWindow.setValue(40);
            else
                timeWindow.setValue(60);
            graphs_panel.changeTimeWindow((int)timeWindow.getValue());
        });
//        slider.setMinorTickCount(5);
//        timeWindow.setBlockIncrement(10);

        gpane.add(switch_graph_motion, 0, 0);
        gpane.add(time_axis_label, 0,1);
        gpane.add(timeWindow, 0, 2);
        tuning_scene.getStylesheets().add("file:" + System.getProperty("user.dir").toString().replace("\\", "/").replace(" ", "%20") + "/Application/src/main/java/CSS/Scenes.css");
        tuning_panel.setScene(tuning_scene);
    }

    public void actionPerformed(Button butn, String command){
        if (command.equals("stop_or_start_graph")) {
            butn.setOnAction(ae ->{
                System.out.println("lala");
                graphs_panel.switchStopStart();
                if (switch_graph_motion.getText().equals("Stop Graph"))
                    switch_graph_motion.setText("Restart Graph");
                else if (switch_graph_motion.getText().equals("Restart Graph"))
                    switch_graph_motion.setText("Stop Graph");
            });
        }
    }

    public JFXPanel getTuningPanel(){
        return tuning_panel;
    }

}
