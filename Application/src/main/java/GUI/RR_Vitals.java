package GUI;

import javafx.application.Platform;
import simulation.Respiration_Counting;

import java.awt.*;

public class RR_Vitals extends Vital_Values_Display{
    private Respiration_Counting resp_val_counter;

    public RR_Vitals(Dimension vitals_panel_dim, Respiration_Counting obj){
        super(vitals_panel_dim, "label-Respiratory");
        super.status_msg.setText("STABLE");
        super.vital_type.setText("RR");
        super.vital_value.setText("VALUE");

        resp_val_counter = obj;
    }

    protected void Set_Displayed_Value(){
        Platform.runLater(() ->{
            super.vital_value.setText(String.valueOf((int)(60/(resp_val_counter.Index_Difference()*0.006))) + " breaths/min");
        });
    }
}