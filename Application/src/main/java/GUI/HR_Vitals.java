package GUI;

import java.awt.*;

public class HR_Vitals extends Vital_Values_Display {

    public HR_Vitals(Dimension vitals_panel_dim){
        super(vitals_panel_dim, "label-Temperature");
        super.status_msg.setText("STABLE");
        super.vital_type.setText("H\nR");
        super.vital_value.setText("VALUE");
    }

    protected void Set_Displayed_Value(){

    }
}