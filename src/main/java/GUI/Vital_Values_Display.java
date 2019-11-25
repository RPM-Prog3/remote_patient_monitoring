package GUI;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.TextAlignment;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public abstract class Vital_Values_Display {
    protected JFXPanel sub_display, status, type, value, vitals_value_display;
    protected Dimension overall_display_dim, sub_display_dim, value_dim;
    protected int proportion_num, proportion_den , proportion_2_num, proportion_2_den;
    protected Label status_msg, vital_type, vital_value;
    protected Border border;
    protected Scene status_scene, value_scene, type_scene;

    public Vital_Values_Display(){
        // Instantiating different panels;
        sub_display = new JFXPanel();
        status = new JFXPanel();
        type = new JFXPanel();
        value = new JFXPanel();
        vitals_value_display = new JFXPanel();

        // Instantiating different labels
        status_msg = new Label();
        vital_type = new Label();
        vital_value = new Label();
        vital_type.setRotate(270);
        vital_type.setAlignment(Pos.CENTER);

        // Instantiating scenes for labels
        status_scene = new Scene(status_msg);
        value_scene = new Scene(vital_value);
        type_scene = new Scene(vital_type);

        // Setting Layouts of the main and sub panels
        sub_display.setLayout(new BorderLayout());

        // Adding sub panels to main panel to construct main panel
        sub_display.add(value, BorderLayout.PAGE_START);
        sub_display.add(status, BorderLayout.CENTER);

        // Instantiating border object(s)
        border = BorderFactory.createLineBorder(Color.black);

        // Setting various panels to be visible and adding contours to them
        sub_display.setVisible(true);
        sub_display.setBorder(border);
        status.setVisible(true);
        status.setBorder(border);
        type.setVisible(true);
        type.setBorder(border);
        value.setVisible(true);
        value.setBorder(border);

        // Setting the various labels to be visible
        status_msg.setVisible(true);
        vital_type.setVisible(true);
        vital_value.setVisible(true);

        Platform.runLater (new Runnable() {
            @Override
            public void run() { settingPanels(status, value, type, status_scene, value_scene, type_scene);}
        });
    }

    private static void settingPanels (JFXPanel status,JFXPanel value, JFXPanel type, Scene status_scene, Scene value_scene, Scene type_scene ) {
        // Adding labels to corresponding Panels
        status.setScene(status_scene);
        value.setScene(value_scene);
        type.setScene(type_scene);
    }

    public JFXPanel getVitalsPanel() {
        // Setting Vitals panel
        vitals_value_display.setLayout(new BorderLayout());
        vitals_value_display.setVisible(true);
        vitals_value_display.add(type, BorderLayout.LINE_START);
        vitals_value_display.add(sub_display, BorderLayout.CENTER);
        return vitals_value_display;
    }

}

