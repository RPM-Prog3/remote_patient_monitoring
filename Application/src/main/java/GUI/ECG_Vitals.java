package GUI;

import javafx.application.Platform;
import javafx.geometry.Pos;
import simulation.BPM;
import simulation.Value_Counter;

import javax.swing.*;
import java.awt.*;

public class ECG_Vitals extends Vital_Values_Display {
    private BPM ecg_val_counter;
    private String value;
    private int i_value;
    private Blinking blinking_state;

    public ECG_Vitals(Dimension vitals_panel_dim, BPM obj){
        super(vitals_panel_dim, "label-ECG");
        super.status_msg.setText("STABLE");
        super.vital_type.setText("ECG");
        super.units_label.setText("BPM");
        super.units_label.setAlignment(Pos.CENTER_RIGHT);

        ecg_val_counter = obj;
    }

    public void Set_Displayed_Value(){
        Platform.runLater(() ->{
            super.vital_value.setText(String.valueOf(ecg_val_counter.BPM_number()));
        });
    }

    protected void CheckStatus(){

    }
}
