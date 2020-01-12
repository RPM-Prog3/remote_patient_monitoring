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
    private javafx.scene.control.Label time_axis_label, BPM_label, BP_label,TEMP_label, RR_label, padding_label;
    private GridPane gpane;
    private Scene tuning_scene;


    private Button switch_graph_motion, abnormalities_menu, patient_record;
    private Sliding timeWindow, BPMregulation, TempRegulation, BPRegulation, RRateRegulation;

    private Overall_Graph graphs_panel;

    public Tuning_Panel(Overall_Graph obj){
        // Instantiating and setting the Grid pane that will contain all the tuning pieces
        graphs_panel = obj;
        gpane = new GridPane();
        gpane.setPadding(new Insets(10, 10, 10, 10));  //this sets the distance from the borders of the grid we want to create

        tuning_panel = new JFXPanel();
        tuning_scene = new Scene(gpane);

        switch_graph_motion = new Button("Stop");
        abnormalities_menu = new Button("Select Abnormalities");
        patient_record = new Button("View Patient record");

        timeWindow = new Sliding("time_window", graphs_panel, 0);
        time_axis_label = new javafx.scene.control.Label();
        time_axis_label.setText("Time");

        BPMregulation = new Sliding("abnormality", graphs_panel, 1);
        BPM_label = new javafx.scene.control.Label();
        BPM_label.setText("BPM");

        BPRegulation = new Sliding ("abnormality", graphs_panel, 2);
        BP_label = new javafx.scene.control.Label();
        BP_label.setText("mmHg");

        RRateRegulation = new Sliding("abnormality", graphs_panel, 3);
        RR_label = new javafx.scene.control.Label();
        RR_label.setText("Breaths/Min");

        TempRegulation = new Sliding("abnormality", graphs_panel, 4);
        TEMP_label = new javafx.scene.control.Label();
        TEMP_label.setText("\u00B0" + "C");

        padding_label = new javafx.scene.control.Label();
        padding_label.setText("\t \t \t");

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
        gpane.add(padding_label, 0,1);
        gpane.add(time_axis_label, 2,0);
        gpane.add(timeWindow, 2, 2);

        gpane.add(BPM_label, 4, 0);
        gpane.add(BPMregulation, 4, 2);

        gpane.add(BP_label, 6,0);
        gpane.add(BPRegulation, 6,2);

        gpane.add(RR_label,8,0);
        gpane.add(RRateRegulation, 8,2);

        gpane.add(TEMP_label, 10,0);
        gpane.add(TempRegulation, 10,2);

        gpane.add(abnormalities_menu, 12,1);
        gpane.add(patient_record, 12,2);

        tuning_scene.getStylesheets().add("file:" + System.getProperty("user.dir").toString().replace("\\", "/").replace(" ", "%20") + "/Application/src/main/java/CSS/Scenes.css");
        tuning_panel.setScene(tuning_scene);
    }

    public void actionPerformed(Button butn, String command){
        if (command.equals("stop_or_start_graph")) {
            butn.setOnAction(ae ->{
                System.out.println("lala");
                graphs_panel.switchStopStart();
                if (switch_graph_motion.getText().equals("Stop"))
                    switch_graph_motion.setText("Restart");
                else if (switch_graph_motion.getText().equals("Restart"))
                    switch_graph_motion.setText("Stop");
            });
        }
    }

    public JFXPanel getTuningPanel(){
        return tuning_panel;
    }

}
