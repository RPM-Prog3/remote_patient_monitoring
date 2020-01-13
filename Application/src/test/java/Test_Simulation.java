import GUI.BP_Vitals;
import GUI.ECG_Vitals;
import GUI.RR_Vitals;
import GUI.TEMP_Vitals;
import org.junit.Assert;
import org.junit.Test;
import simulation.*;

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
        ECG ecg = new ECG();
        int zeros_ = ecg.getPadZeros(166-20);
        Assert.assertTrue("Wrong wave length for mean 166", zeros_ <= 152 && zeros_ >= 140);
        zeros_ =  ecg.getPadZeros(92-20);
        Assert.assertTrue("Wrong wave length for mean 92",zeros_ <= 78 && zeros_ >= 66);
        zeros_ =  ecg.getPadZeros(218-20);
        Assert.assertTrue("Wrong wave length for mean 218",zeros_ <= 204 && zeros_ >= 192);
        zeros_ =  ecg.getPadZeros(244-20);
        Assert.assertTrue("Wrong wave length for mean 244",zeros_ <= 230 && zeros_ >= 218);
        zeros_ =  ecg.getPadZeros(66-20);
        Assert.assertTrue("Wrong wave length for mean 66",zeros_ <= 52 && zeros_ >= 40);
        System.out.println("**********Passed Test ECG**********");
    }

    @Test
    public void Test_BP(){
        Blood_Pressure bp = new Blood_Pressure(0.6, 0.15, 0.25, 100, 1);
        double val;
        int max_p = -10;
        int min_p = 1500;

        for(int j=-2; j<=2; j+=1) {
            for (int i = 0; i < 300; i++) {
                val = bp.get_next_value(j);
                if (max_p < val)
                    max_p = (int)val;
                if (min_p > val)
                    min_p = (int)val;
            }
            if (j == -2) {
                Assert.assertTrue("Wrong pressure for low urgent s: " + max_p, max_p <= 100);
                Assert.assertTrue("Wrong pressure for low urgent d: " + min_p, min_p < 70);
            }
            if (j == -1) {
                Assert.assertTrue("Wrong pressure for low warning s: " + max_p, max_p <= 110 && max_p > 100);
                Assert.assertTrue("Wrong pressure for low warning d: " + min_p, min_p >= 70 && min_p < 77);
            }
            if (j == 0) {
                Assert.assertTrue("Wrong pressure for stable s: " + max_p, max_p < 130 && max_p > 110);
                Assert.assertTrue("Wrong pressure for stable d: " + min_p, min_p >= 77 && min_p <= 83);
            }
            if (j == 1) {
                Assert.assertTrue("Wrong pressure for high warning s: " + max_p, max_p >= 130 && max_p < 139);
                Assert.assertTrue("Wrong pressure for high warning d: " + min_p, min_p > 83 && min_p <= 90);
            }
            if (j == 2) {
                Assert.assertTrue("Wrong pressure for high urgent s: " + max_p, max_p >= 139);
                Assert.assertTrue("Wrong pressure for high urgent d: " + min_p, min_p > 90);
            }
            max_p = -10;
            min_p = 1500;
        }
        System.out.println("**********Passed Test Blood Pressure**********");
    }

    @Test
    public void Test_RR(){
        Resp_Rate rr = new Resp_Rate(50, 1, -0.95, 125);
        int breath_counter = 0;
        int threshold = 60;
        boolean over = false;
        double val;

        for(int j=-2; j<=2; j+=1) {
            for (int i = 0; i < 10000; i++) {
                val = rr.get_next_value(j);

                if (val > 60 && !over) {
                    breath_counter += 1;
                    over = true;
                }

                if (val < 60 && over)
                    over = false;
            }
            if (j == -2)
                Assert.assertTrue("Wrong resp rate for low urgent: " + breath_counter, breath_counter < 9);
            if (j == -1)
                Assert.assertTrue("Wrong resp rate for low warning: " + breath_counter, breath_counter >= 9 && breath_counter <= 12);
            if (j == 0)
                Assert.assertTrue("Wrong resp rate for stable: " + breath_counter, breath_counter > 12 && breath_counter < 24);
            if (j == 1)
                Assert.assertTrue("Wrong resp rate for high warning: " + breath_counter, breath_counter >= 24 && breath_counter <= 30);
            if (j == 2)
                Assert.assertTrue("Wrong resp rate for high urgent: " + breath_counter, breath_counter > 30);
            over = false;
            breath_counter = 0;
        }
        System.out.println("**********Passed Test Respiratory Rate**********");
    }

    @Test
    public void Test_TEMP() {
        Body_Temp temp = new Body_Temp(37, 0.01, 0.5);
        double avg, sum = 0;

        for(int j=-2; j<=2; j+=1) {
            for (int i = 0; i < 50; i++)
                sum += temp.get_next_value(j);

            avg = sum/50;

            if (j == -2)
                Assert.assertTrue("Wrong temperature for low urgent: " + avg, avg < 34);
            if (j == -1)
                Assert.assertTrue("Wrong temperature for low warning: " + avg, avg >= 34 && avg < 36.5);
            if (j == 0)
                Assert.assertTrue("Wrong temperature for stable: " + avg, avg >= 36.5 && avg <= 38.5);
            if (j == 1)
                Assert.assertTrue("Wrong temperature for high warning: " + avg, avg > 38.5 && avg < 40);
            if (j == 2)
                Assert.assertTrue("Wrong temperature for high urgent: " + avg, avg >= 40);
            sum = 0;
        }
        System.out.println("**********Passed Test Temp**********");
    }
}