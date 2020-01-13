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
import java.util.concurrent.ExecutorService;

import java.awt.*;
import java.util.concurrent.Executors;

public class Test_Simulation {
    Dimension rand_dim = new Dimension(100,100);;
    ECG_Vitals ecg_vitals;
    BPM bpm;
    int max_BPM, min_BPM;

    BP_Vitals bp_vitals;
    Pressure_Counting bp_counter;
    int max_s_p, min_s_p, max_d_p, min_d_p;

    RR_Vitals rr_vitals;
    Respiration_Counting rr_counter;
    int max_rr, min_rr;

    TEMP_Vitals temp_vitals;
    Temperature_Counting temp_counter;
    double max_tmp, min_tmp;

    private ExecutorService exe = Executors.newFixedThreadPool(1);

    @Test
    public void Test_ECG(){
        ecg_vitals = new ECG_Vitals(rand_dim, bpm,exe);
        max_BPM = 250;
        min_BPM = 0;
        Assert.assertTrue("BPM should be lower than " + max_BPM, ecg_vitals.getBPM() < max_BPM);
        Assert.assertTrue("BPM should be greater or equal to " + min_BPM, ecg_vitals.getBPM() >= min_BPM);
    }

    @Test
    public void Test_BP(){
        bp_vitals = new BP_Vitals(rand_dim, bp_counter,exe);
        max_s_p = 250;
        min_s_p = 0;
        max_d_p = 200;
        min_d_p = 0;
        Assert.assertTrue("Systolic pressure should be lower than " + max_s_p, bp_vitals.get_s_pressure() < max_s_p);
        Assert.assertTrue("Systolic pressure should be greater or equal to " + min_s_p, bp_vitals.get_s_pressure() >= min_s_p);
        Assert.assertTrue("Diastolic pressure should be lower than " + max_d_p, bp_vitals.get_d_pressure() < max_d_p);
        Assert.assertTrue("Systolic pressure should be greater or equal to " + max_s_p, bp_vitals.get_d_pressure() >= min_d_p);
        if (bp_vitals.get_s_pressure() == 0 && bp_vitals.get_d_pressure() == 0){}
        else {
            Assert.assertTrue("Systolic pressure should be greater than Diastolic pressure", bp_vitals.get_s_pressure() > bp_vitals.get_d_pressure());
        }
    }

    @Test
    public void Test_RR(){
        rr_vitals = new RR_Vitals(rand_dim, rr_counter,exe);
        max_rr = 200;
        min_rr = 0;
        Assert.assertTrue("The respiratory rate cannot be greater than " +  max_rr, rr_vitals.getRR_value() < max_rr );
        Assert.assertTrue("The respiratory rate cannot be lower or equal than " +  min_rr, rr_vitals.getRR_value() >= min_rr );
    }

    @Test
    public void Test_TEMP() {
        temp_vitals = new TEMP_Vitals(rand_dim, temp_counter,exe);
        max_tmp = 50;
        min_tmp = 30;
        Assert.assertTrue("Body temperature rate cannot be greater than " + max_tmp, temp_vitals.get_temp_val() < max_tmp);
        if (temp_vitals.get_temp_val() == 0) { }
        else {
            Assert.assertTrue("Body temperature rate cannot be lower or equal than " + min_tmp, temp_vitals.get_temp_val() >= min_tmp);
        }
    }
}