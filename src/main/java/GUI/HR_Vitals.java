package GUI;

import java.awt.*;

public class HR_Vitals extends Vital_Values_Display {

    public HR_Vitals(Dimension vitals_panel_dim){
        super(vitals_panel_dim, "label-Temperature");
        super.status_msg.setText("STATUS");
        super.vital_type.setText("TYPE");
        super.vital_value.setText("VALUE");
    }
}
