package Application_Server_Interface.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Patient_Value {
    static DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
    private int  bpm, resp_rate, blood_pressure_upper, blood_pressure_lower;
    private double body_temp;
    private String patient_id, time, abnormality;

    public Patient_Value(String patient_id, LocalDateTime time,
                         int bpm, int resp_rate, double body_temp,
                         int blood_pressure_upper, int blood_pressure_lower,
                         String abnormality){
        this.patient_id = patient_id;
        this.time = time.format(formatter);
        this.bpm = bpm;
        this.resp_rate = resp_rate;
        this.body_temp = body_temp;
        this.blood_pressure_upper = blood_pressure_upper;
        this.blood_pressure_lower = blood_pressure_lower;
        this.abnormality = abnormality;
    }

    public Patient_Value(String patient_id, LocalDateTime time,
                         int bpm, int resp_rate, double body_temp,
                         int blood_pressure_upper, int blood_pressure_lower){
        this.patient_id = patient_id;
        this.time = time.format(formatter);
        this.bpm = bpm;
        this.resp_rate = resp_rate;
        this.body_temp = body_temp;
        this.blood_pressure_upper = blood_pressure_upper;
        this.blood_pressure_lower = blood_pressure_lower;
        this.abnormality = "";
    }

    public Patient_Value(String patient_id, String time,
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

    public Patient_Value(String patient_id, String time,
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

    public void print_values(){
        System.out.println(String.format("%s, %s, %s, %s, %s, %s, %s",
                patient_id, time, bpm, resp_rate, body_temp, blood_pressure_upper, blood_pressure_lower, abnormality));
    }

    public String get_patient_id(){
        return patient_id;
    }

    public String get_time(){
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
