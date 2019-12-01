package GUI;

import java.awt.*;

public class HR_Vitals extends Vital_Values_Display {

    public HR_Vitals(Dimension vitals_panel_dim){
        super(vitals_panel_dim);
        super.status_msg.setText("STABLE");
        super.vital_type.setText("HEART RATE");
        super.vital_value.setText("VALUE");
    }
}
