package GUI;

import javafx.application.Platform;
import simulation.BPM;
import simulation.Value_Counter;

import javax.swing.*;
import java.awt.*;

public class ECG_Vitals extends Vital_Values_Display {
    private BPM val_counter;

    public ECG_Vitals(Dimension vitals_panel_dim, BPM obj){
        super(vitals_panel_dim, "label-ECG");
        super.status_msg.setText("STABLE");
        super.vital_type.setText("E\nC\nG");
        super.vital_value.setText("VALUE");

        val_counter = obj;
    }

    public void Set_Displayed_Value(){
        Platform.runLater(() ->{
            super.vital_value.setText(String.valueOf((int)(60/(val_counter.Index_Difference()*0.006))));
        });
    }
}
