package GUI;

import javafx.application.Platform;
import simulation.Pressure_Counting;

import java.awt.*;

public class BP_Vitals extends Vital_Values_Display {
    private Pressure_Counting press_val_counter;

    public BP_Vitals(Dimension vitals_panel_dim, Pressure_Counting obj){
        super(vitals_panel_dim, "label-Pressure");
        super.status_msg.setText("STABLE");
        super.vital_type.setText("BP");
        super.vital_value.setText("VALUE");

        press_val_counter = obj;
    }

    protected void Set_Displayed_Value(){
        Platform.runLater(() ->{
            super.vital_value.setText(String.valueOf(press_val_counter.Max_Min()[0]) + "/" + String.valueOf(press_val_counter.Max_Min()[1]));
        });
    }
}