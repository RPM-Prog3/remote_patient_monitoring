package GUI;

import java.awt.*;

public class BP_Vitals extends Vital_Values_Display {

    public BP_Vitals(Dimension vitals_panel_dim){
        super(vitals_panel_dim, "label-Pressure");
        super.status_msg.setText("STABLE");
        super.vital_type.setText("BLOOD PRESSURE");
        super.vital_value.setText("VALUE");
    }
}
