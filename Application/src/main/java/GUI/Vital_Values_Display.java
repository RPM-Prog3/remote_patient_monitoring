package GUI;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextAlignment;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public abstract class Vital_Values_Display {
    protected JFXPanel status, type, value, vitals_value_display, units, top_panel;
    protected Dimension overall_display_dim, sub_display_dim, value_dim, type_dim;
    protected int sd_od_ratio_num, sd_od_ratio_den , v_sd_ratio_num, v_sd_ratio_den;
    protected Label status_msg, vital_type, vital_value, units_label;
    protected Border border;
    protected Scene status_scene, value_scene, type_scene, units_scene;
    protected javafx.scene.text.Font status_font, type_font, value_font, units_font;

    public Vital_Values_Display(Dimension vitals_panel_dim, String colorLabels){
        // Instantiating different panels;
        vitals_value_display = new JFXPanel();
        status = new JFXPanel();
        type = new JFXPanel();
        value = new JFXPanel();
        units = new JFXPanel();
        top_panel = new JFXPanel();
        top_panel.setLayout(new GridLayout(1,2));

        // Setting aspect ration of th various panels
        this.overall_display_dim = vitals_panel_dim;
        sd_od_ratio_num = 8;
        sd_od_ratio_den = 10;
        v_sd_ratio_num = 8;
        v_sd_ratio_den = 10;

        // Instantiating different labels
        status_msg = new Label();
        vital_type = new Label();
        vital_value = new Label();
        units_label = new Label();

        // Instantiating scenes for labels
        status_scene = new Scene(status_msg);
        value_scene = new Scene(vital_value);
        type_scene = new Scene(vital_type);
        units_scene = new Scene(units_label);

        // Setting various panels to be visible
        status.setVisible(true);
        type.setVisible(true);
        value.setVisible(true);

        // Setting the various labels to be visible
        status_msg.setVisible(true);
        vital_type.setVisible(true);
        vital_value.setVisible(true);
        units_label.setVisible(true);
        units_label.setTextAlignment(TextAlignment.RIGHT);


        //Applying the css
        applyCSS(colorLabels);

        // Setting labels font
        status_font = new javafx.scene.text.Font("Arial", 15);
        type_font = new javafx.scene.text.Font("Arial", 20);
        value_font = new javafx.scene.text.Font("Arial", 50);
        units_font = new javafx.scene.text.Font("Arial", 20);
        status_msg.setFont(status_font);
        vital_type.setFont(type_font);
        vital_value.setFont(value_font);
        units_label.setFont(units_font);

        // Adding sub panels to main panel to construct main panel
        vitals_value_display.setLayout(new BorderLayout());
        vitals_value_display.add(value, BorderLayout.CENTER);
        vitals_value_display.add(status, BorderLayout.PAGE_END);


        Platform.runLater (new Runnable() {
            @Override
            public void run() { settingPanels(status, value, type, units, status_scene, value_scene, type_scene, units_scene);}
        });
        top_panel.add(type);
        top_panel.add(units);
        vitals_value_display.add(top_panel, BorderLayout.PAGE_START);
    }

    private static void settingPanels (JFXPanel status,JFXPanel value, JFXPanel type, JFXPanel units, Scene status_scene, Scene value_scene, Scene type_scene, Scene units_scene ) {
        // Adding labels to corresponding Panels
        status.setScene(status_scene);
        value.setScene(value_scene);
        type.setScene(type_scene);
        units.setScene(units_scene);
    }

    public JFXPanel getVitalsPanel() {
        // Setting Vitals panel
        vitals_value_display.setVisible(true);
        return vitals_value_display;
    }

    public void setVitals_value_displaySize(Dimension vitals_panel_dim){
        // Setting dimensions and proportions of the panels
        overall_display_dim = vitals_panel_dim;

        // Setting maximum size for vital type labels
        type_dim = new Dimension();
        type_dim.height = (int)((overall_display_dim.height* sd_od_ratio_num)/sd_od_ratio_den); ;
        type_dim.width = overall_display_dim.width;
    }

    private void applyCSS(String colorLabels){
        String style_location = "file:" + System.getProperty("user.dir").toString().replace("\\", "/").replace(" ", "%20") + "/Application/src/main/java/CSS/Scenes.css";

        status_msg.getStyleClass().add(colorLabels);
        vital_value.getStyleClass().add(colorLabels);
        vital_type.getStyleClass().add(colorLabels);
        units_label.getStyleClass().add(colorLabels);

        status_msg.getStyleClass().add("label-status");
        vital_value.getStyleClass().add("label-value");
//        vital_type.getStyleClass().add(colorLabels);
        units_label.getStyleClass().add("label-units");

        status_scene.getStylesheets().add(style_location);
        value_scene.getStylesheets().add(style_location);
        type_scene.getStylesheets().add(style_location);
        units_scene.getStylesheets().add(style_location);
    }

    abstract protected void Set_Displayed_Value();

}