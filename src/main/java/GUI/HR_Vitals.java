package GUI;

public class HR_Vitals extends Vital_Values_Display {

    public HR_Vitals(){
        super();
        super.status_msg.setText("STATUS");
        super.vital_type.setText("VITAL SIGN ");
        super.vital_value.setText("VALUE");
    }
}
