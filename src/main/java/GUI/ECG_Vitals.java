package GUI;

import javax.swing.*;
import java.awt.*;

public class ECG_Vitals extends Vital_Values_Display {

    public ECG_Vitals(){
        super();
        super.status_msg.setText("STATUS");
        super.vital_type.setText("VITAL SIGN ");
        super.vital_value.setText("VALUE");
    }
}
