package GUI;

import java.awt.*;

public class RR_Vitals extends Vital_Values_Display{

    public RR_Vitals(Dimension vitals_panel_dim){
        super(vitals_panel_dim, "label-Respiratory");
        super.status_msg.setText("STATUS");
        super.vital_type.setText("TYPE");
        super.vital_value.setText("VALUE");
    }
}
