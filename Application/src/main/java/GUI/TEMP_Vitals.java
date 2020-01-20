package GUI;

import javafx.application.Platform;
import javafx.geometry.Pos;
import simulation.Temperature_Counting;

import java.awt.*;
import java.util.concurrent.ExecutorService;

public class TEMP_Vitals extends Vital_Values_Display {
    private Temperature_Counting temp_val_counter;
    private String value;
    private double i_value;
    private boolean a;
    private int status;

    public TEMP_Vitals(Dimension vitals_panel_dim, Temperature_Counting obj, ExecutorService exe){
        super(vitals_panel_dim, "label-Temperature", exe);
        super.status_msg.setText("STABLE");
        super.vital_type.setText("TEMP");
        String units = "C";

        super.units_label.setText("\u00B0" + units);
        super.units_label.setAlignment(Pos.CENTER_RIGHT);

        temp_val_counter = obj;
        a = false;
    }

    protected void Set_Displayed_Value(){
        Platform.runLater(() ->{
            value = String.valueOf(temp_val_counter.rounded_temperature());
            super.vital_value.setText(value);
            i_value = Double.parseDouble(value);
            if (i_value != 0)
                a = true;
            if(a) {
                CheckStatus();
                statusInMinute(getStatus());
                minuteClock(getMean());
            }
        });
    }

    public double getMean(){
        return temp_val_counter.getMean(3);
    }

    protected void CheckStatus() {
        if ((i_value > 38.5 && i_value < 40) || (i_value < 36.5 && i_value > 34)) {
            warning();
            status = 2;
        }
        else if ((i_value >= 40) || (i_value <= 34)){
            urgent();
            status = 3;
        }
        else{
            stable();
            status = 1;
        }
    }

    public int getStatus(){
        return status;
    }

}