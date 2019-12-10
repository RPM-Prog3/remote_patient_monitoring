package GUI;

import java.awt.*;

public class RR_Vitals extends Vital_Values_Display{

    public RR_Vitals(Dimension vitals_panel_dim){
        super(vitals_panel_dim, "label-Respiratory");
        super.status_msg.setText("STABLE");
        super.vital_type.setText("RESPIRATORY RATE");
        super.vital_value.setText("VALUE");
    }
}
