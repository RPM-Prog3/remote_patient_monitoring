package GUI;

import javafx.application.Platform;
import javafx.geometry.Pos;
import simulation.Pressure_Counting;

import java.awt.*;
import java.util.concurrent.ExecutorService;

public class BP_Vitals extends Vital_Values_Display {
    private Pressure_Counting press_val_counter;
    private String s_value, d_value;
    private int s_pressure, d_pressure;
    private boolean a;

    public BP_Vitals(Dimension vitals_panel_dim, Pressure_Counting obj, ExecutorService exe){
        super(vitals_panel_dim, "label-Pressure", exe);
        super.status_msg.setText("STABLE");
        super.vital_type.setText("BP");
        super.units_label.setText("mmHg");
        super.units_label.setAlignment(Pos.CENTER_RIGHT);

        press_val_counter = obj;
        a = false;
    }

    public void Set_Displayed_Value(){
        Platform.runLater(() ->{
            s_value = String.valueOf(press_val_counter.Max_Min()[0]);
            d_value = String.valueOf(press_val_counter.Max_Min()[1]);
            s_pressure = Integer.parseInt(s_value);
            d_pressure = Integer.parseInt(d_value);
            if (s_pressure != 0 && d_pressure != 0)
                a = true;
            if(a)
                CheckStatus();
            super.vital_value.setText(s_value + "/" + d_value);
        });
    }

    public double[] getMean(){
        double[] array = new double[] {press_val_counter.getMean(1), press_val_counter.getMean(2)};
        return array;
    }

    protected void CheckStatus(){
        if ((s_pressure >= 130 && s_pressure < 139) || (d_pressure > 80 && d_pressure <= 90)){
            warning();
        }
        else if ((s_pressure > 100 && s_pressure < 110) || (d_pressure > 70 && d_pressure < 80)){
            warning();
        }
        else if ((s_pressure >= 139) || (d_pressure > 90 )){
            urgent();
        }
        else if ((s_pressure <= 100) || (d_pressure <= 70 )){
            urgent();
        }
        else {
            stable();
        }
    }

    public int get_s_pressure(){
        if (a){ }
        return s_pressure;
    }

    public int get_d_pressure(){
        if (a) { }
        return d_pressure;
    }
}