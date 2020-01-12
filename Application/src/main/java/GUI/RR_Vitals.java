package GUI;

import javafx.application.Platform;
import javafx.geometry.Pos;
import simulation.Respiration_Counting;

import java.awt.*;

public class RR_Vitals extends Vital_Values_Display{
    private Respiration_Counting resp_val_counter;
    private String value;
    private int resp_rate_value;
    private boolean a;

    public RR_Vitals(Dimension vitals_panel_dim, Respiration_Counting obj){
        super(vitals_panel_dim, "label-Respiratory");
        super.status_msg.setText("STABLE");
        super.vital_type.setText("RR");
        super.units_label.setText("Breaths/Min");
        super.units_label.setAlignment(Pos.CENTER_RIGHT);

        resp_val_counter = obj;
        a = false;
    }

    protected void Set_Displayed_Value(){
        Platform.runLater(() ->{
            value = String.valueOf(resp_val_counter.breaths_number());
            super.vital_value.setText(value);
            resp_rate_value = Integer.parseInt(value);
            if (resp_rate_value != 0)
                a = true;
            if(a)
                CheckStatus();
        });
    }

    public int getMean(){
        return (int)resp_val_counter.getMean(0);
    }

    protected void CheckStatus() {
        if ((resp_rate_value  > 25 && resp_rate_value  < 30) || (resp_rate_value  < 15 && resp_rate_value  > 10)){
            warning();
        }
        else if ((resp_rate_value > 30) || (resp_rate_value < 10)){
            urgent();
        }
        else{
            stable();
        }
    }
}