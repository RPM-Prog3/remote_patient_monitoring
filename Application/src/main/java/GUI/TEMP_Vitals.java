package GUI;

import javafx.application.Platform;
import javafx.geometry.Pos;
import simulation.Temperature_Counting;

import java.awt.*;

public class TEMP_Vitals extends Vital_Values_Display {
    private Temperature_Counting temp_val_counter;

    public TEMP_Vitals(Dimension vitals_panel_dim, Temperature_Counting obj){
        super(vitals_panel_dim, "label-Temperature");
        super.status_msg.setText("STABLE");
        super.vital_type.setText("TEMP");
        String units = "C";

        super.units_label.setText("\u00B0" + units);
        super.units_label.setAlignment(Pos.CENTER_RIGHT);

        temp_val_counter = obj;
    }

    protected void Set_Displayed_Value(){
        Platform.runLater(() ->{
            super.vital_value.setText(String.valueOf(temp_val_counter.rounded_temperature()));
        });
    }

    protected void CheckStatus() {

    }
}