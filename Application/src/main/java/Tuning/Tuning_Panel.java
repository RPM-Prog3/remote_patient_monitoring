package Tuning;

import GUI.BP_Vitals;
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
import javax.swing.border.Border;
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
    private Sliding timeWindow, BPMregulation, BreathsRegulation;

    private Overall_Graph graphs_panel;

    public Tuning_Panel(Overall_Graph obj){
        // Instantiating and setting the Grid pane that will contain all the tuning pieces
        graphs_panel = obj;
        gpane = new GridPane();
        gpane.setPadding(new Insets(10, 10, 10, 10));  //this sets the distance from the borders of the grid we want to create

        tuning_panel = new JFXPanel();
        tuning_scene = new Scene(gpane);

        switch_graph_motion = new Button("Stop Graph");

        timeWindow = new Sliding("time_window", graphs_panel);
        time_axis_label = new javafx.scene.control.Label();
        time_axis_label.setText("Time");

        BPMregulation = new Sliding("abnormality", graphs_panel);
//        BreathsRegulation = new Sliding("abnormality", graphs_panel);

        tuning_scene.getStylesheets().add("file:" + System.getProperty("user.dir").toString().replace("\\", "/").replace(" ", "%20") + "/Application/src/main/java/CSS/Tune.css");

        Platform.runLater (new Runnable() {
            @Override
            public void run() { setPanel();}
        });
    }

    private void setPanel(){
        actionPerformed(switch_graph_motion, "stop_or_start_graph");
        switch_graph_motion.setVisible(true);

        gpane.add(switch_graph_motion, 0, 0);
        gpane.add(time_axis_label, 0,1);
        gpane.add(timeWindow, 0, 2);
        gpane.add(BPMregulation, 2, 0);
//        gpane.add(BreathsRegulation, 2, 2);
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
