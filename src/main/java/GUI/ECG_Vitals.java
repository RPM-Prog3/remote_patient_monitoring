package GUI;

import javax.swing.*;
import java.awt.*;

public class ECG_Vitals extends Vital_Values_Display {

    public ECG_Vitals(Dimension vitals_panel_dim){
        super(vitals_panel_dim);
        super.status_msg.setText("STATUS");
        super.vital_type.setText("TYPE");
        super.vital_value.setText("VALUE");
    }
}
