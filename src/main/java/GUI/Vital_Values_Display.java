package GUI;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextAlignment;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public abstract class Vital_Values_Display {
    protected JFXPanel sub_display, status, type, value, vitals_value_display;
    protected Dimension overall_display_dim, sub_display_dim, value_dim;
    protected int sd_od_ratio_num, sd_od_ratio_den , v_sd_ratio_num, v_sd_ratio_den;
    protected Label status_msg, vital_type, vital_value;
    protected Border border;
    protected Scene status_scene, value_scene, type_scene;
    protected Pane vital_type_pane;

    public Vital_Values_Display(Dimension vitals_panel_dim){
        // Instantiating different panels;
        sub_display = new JFXPanel();
        status = new JFXPanel();
        type = new JFXPanel();
        value = new JFXPanel();
        vitals_value_display = new JFXPanel();
        vital_type_pane = new Pane();

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
        vital_type.setRotate(270);
       // vital_type.setAlignment(Pos.CENTER);
        vital_type_pane.getChildren().add(vital_type);
        vital_type.relocate(0,100);

        // Instantiating scenes for labels
        status_scene = new Scene(status_msg);
        value_scene = new Scene(vital_value);
        type_scene = new Scene(vital_type_pane);

        // Setting Layouts of the main and sub panels
        sub_display.setLayout(new BorderLayout());

        // Setting various panels to be visible
        sub_display.setVisible(true);
        status.setVisible(true);
        type.setVisible(true);
        value.setVisible(true);

        // Setting the various labels to be visible
        status_msg.setVisible(true);
        vital_type.setVisible(true);
        vital_value.setVisible(true);

        //Applying the css
        status_scene.getStylesheets().add("file:/" + System.getProperty("user.dir").toString().replace("\\", "/").replace(" ", "%20") + "/src/main/java/GUI/Scenes.css");
        value_scene.getStylesheets().add("file:/" + System.getProperty("user.dir").toString().replace("\\", "/").replace(" ", "%20") + "/src/main/java/GUI/Scenes.css");
        type_scene.getStylesheets().add("file:/" + System.getProperty("user.dir").toString().replace("\\", "/").replace(" ", "%20") + "/src/main/java/GUI/Scenes.css");

        // Instantiating border object(s)
        border = BorderFactory.createLineBorder(Color.black);

        // Setting contours of the different panels
        sub_display.setBorder(border);
        status.setBorder(border);
        type.setBorder(border);
        value.setBorder(border);

        // Adding sub panels to main panel to construct main panel
        sub_display.add(value, BorderLayout.PAGE_START);
        sub_display.add(status, BorderLayout.CENTER);

        // sub display panel:
        sub_display_dim = new Dimension();
        sub_display_dim.height = overall_display_dim.height;
        sub_display_dim.width = (int)((overall_display_dim.width * sd_od_ratio_num)/sd_od_ratio_den);
        sub_display.setPreferredSize(sub_display_dim);

        // value panel:
        value_dim = new Dimension();
        value_dim.width = sub_display_dim.width;
        value_dim.height = (int)((overall_display_dim.height * v_sd_ratio_num)/v_sd_ratio_den);
        value.setPreferredSize(value_dim);


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

