import GUI.BP_Vitals;
import GUI.ECG_Vitals;
import GUI.RR_Vitals;
import GUI.TEMP_Vitals;
import org.junit.Assert;
import org.junit.Test;
import simulation.BPM;
import simulation.Pressure_Counting;
import simulation.Respiration_Counting;
import simulation.Temperature_Counting;

import java.awt.*;

public class Test_Status {
    Dimension rand_dim = new Dimension(100,100);;
    ECG_Vitals ecg_vitals;
    BPM bpm;
    BP_Vitals bp_vitals;
    Pressure_Counting bp_counter;
    RR_Vitals rr_vitals;
    Respiration_Counting rr_counter;
    TEMP_Vitals temp_vitals;
    Temperature_Counting temp_counter;

    int stable_status, warning_status, urgent_status;


    @Test
    public void Stable_Status_Test(){
        ecg_vitals = new ECG_Vitals(rand_dim, bpm);
        bp_vitals = new BP_Vitals(rand_dim, bp_counter);
        rr_vitals = new RR_Vitals(rand_dim, rr_counter);
        temp_vitals = new TEMP_Vitals(rand_dim, temp_counter);
        warning_status = 1;
        urgent_status = 2;
        stable_status = 3;
        if (ecg_vitals.getBPM() == 0 && (bp_vitals.get_d_pressure() == 0 && bp_vitals.get_s_pressure() == 0) && rr_vitals.getRR_value() == 0 && temp_vitals.get_temp_val() == 0){}
        else {
            Assert.assertTrue("The status should be STABLE if the values are within normal range.", ecg_vitals.getStatus() == 3 && ecg_vitals.getStatus_msg().getText().equals("STABLE"));
        }
    }

    @Test
    public void Warning_Status_Test(){
        ecg_vitals = new ECG_Vitals(rand_dim, bpm);
        bp_vitals = new BP_Vitals(rand_dim, bp_counter);
        rr_vitals = new RR_Vitals(rand_dim, rr_counter);
        temp_vitals = new TEMP_Vitals(rand_dim, temp_counter);
        warning_status = 1;
        urgent_status = 2;
        stable_status = 3;
        if (ecg_vitals.getBPM() == 0 && (bp_vitals.get_d_pressure() == 0 && bp_vitals.get_s_pressure() == 0) && rr_vitals.getRR_value() == 0 && temp_vitals.get_temp_val() == 0){}
        else{
            Assert.assertTrue("The status should be WARNING if the values are within the warning range", ecg_vitals.getStatus() == 1 && ecg_vitals.getStatus_msg().getText().equals("WARNING"));
        }
    }

    @Test
    public void Urgent_Status_Test() {
        ecg_vitals = new ECG_Vitals(rand_dim, bpm);
        bp_vitals = new BP_Vitals(rand_dim, bp_counter);
        rr_vitals = new RR_Vitals(rand_dim, rr_counter);
        temp_vitals = new TEMP_Vitals(rand_dim, temp_counter);
        warning_status = 1;
        urgent_status = 2;
        stable_status = 3;
        if (ecg_vitals.getBPM() == 0 && (bp_vitals.get_d_pressure() == 0 && bp_vitals.get_s_pressure() == 0) && rr_vitals.getRR_value() == 0 && temp_vitals.get_temp_val() == 0) {
        } else {
            Assert.assertTrue("The status should be URGENT if the vaues are within the urgent range", ecg_vitals.getStatus() == 2 && ecg_vitals.getStatus_msg().getText().equals("URGENT"));
        }
    }
}
