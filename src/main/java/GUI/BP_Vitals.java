package GUI;

import java.awt.*;

public class BP_Vitals extends Vital_Values_Display {

    public BP_Vitals(Dimension vitals_panel_dim){
        super(vitals_panel_dim);
        super.status_msg.setText("STATUS");
        super.vital_type.setText("TYPE");
        super.vital_value.setText("VALUE");
    }
}
