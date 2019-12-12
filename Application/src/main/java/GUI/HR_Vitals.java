package GUI;

import javafx.application.Platform;
import simulation.Temperature_Counting;

import java.awt.*;

public class HR_Vitals extends Vital_Values_Display {
    private Temperature_Counting temp_val_counter;

    public HR_Vitals(Dimension vitals_panel_dim, Temperature_Counting obj){
        super(vitals_panel_dim, "label-Temperature");
        super.status_msg.setText("STABLE");
        super.vital_type.setText("HR");
        super.vital_value.setText("VALUE");

        temp_val_counter = obj;
    }

    protected void Set_Displayed_Value(){
        Platform.runLater(() ->{
            super.vital_value.setText(String.valueOf((int)(temp_val_counter.Double_Value())));
        });
    }
}