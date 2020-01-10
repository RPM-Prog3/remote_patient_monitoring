package Data;

import java.time.LocalDateTime;

public class Patient_Value {
    private LocalDateTime time;
    private int patient_id, bpm, resp_rate, blood_pressure_upper, blood_pressure_lower;
    private double body_temp;
    private String abnormality;

    public Patient_Value(int patient_id, LocalDateTime time,
                         int bpm, int resp_rate, double body_temp,
                         int blood_pressure_upper, int blood_pressure_lower,
                         String abnormality){
        this.patient_id = patient_id;
        this.time = time;
        this.bpm = bpm;
        this.resp_rate = resp_rate;
        this.body_temp = body_temp;
        this.blood_pressure_upper = blood_pressure_upper;
        this.blood_pressure_lower = blood_pressure_lower;
        this.abnormality = abnormality;
    }

    public Patient_Value(int patient_id, LocalDateTime time,
                         int bpm, int resp_rate, double body_temp,
                         int blood_pressure_upper, int blood_pressure_lower){
        this.patient_id = patient_id;
        this.time = time;
        this.bpm = bpm;
        this.resp_rate = resp_rate;
        this.body_temp = body_temp;
        this.blood_pressure_upper = blood_pressure_upper;
        this.blood_pressure_lower = blood_pressure_lower;
        this.abnormality = "";
    }

    public void print_patient

    public int get_patient_id(){
        return patient_id;
    }

    public LocalDateTime get_time(){
        return time;
    }

    public int get_bpm(){
        return bpm;
    }

    public int get_resp_rate(){
        return resp_rate;
    }

    public double get_body_temp(){
        return body_temp;
    }

    public int get_blood_pressure_upper(){
        return blood_pressure_upper;
    }

    public int get_blood_pressure_lower(){
        return blood_pressure_lower;
    }

    public int[] get_blood_pressure(){
        return new int[]{blood_pressure_upper, blood_pressure_lower};
    }

    public String get_abnormality(){
        return abnormality;
    }

}
