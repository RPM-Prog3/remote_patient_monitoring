package Graphing;

import simulation.ECG;
import simulation.Value_Counter;

public class Graph_ECG extends Graph {
    private ECG ecgdata;
    private int heart_rate_type;

    public Graph_ECG(String colorGraph, Value_Counter obj, double sample_period, int time_shown, ECG ecgdata_input){
        super(colorGraph, obj, sample_period, time_shown);
        ecgdata = ecgdata_input;
        heart_rate_type = 0;
    }

    public void changeAbnormality(int newType) {
        heart_rate_type = newType;
    }

    protected void Get_Next_Value(){
        data_point = ecgdata.get_next_value(heart_rate_type);
    }

    protected void Monitoring_Value(){
        super.val_counter.Count_bpm(super.data_point, super.series_pointer);
    }
}
