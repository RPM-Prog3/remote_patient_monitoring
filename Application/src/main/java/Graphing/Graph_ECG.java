package Graphing;

import simulation.ECG;
import simulation.Value_Counter;

public class Graph_ECG extends Graph {
    private ECG ecgdata;

    public Graph_ECG(String colorGraph, Value_Counter obj, double sample_period, int time_shown, ECG ecgdata_input){
        super(colorGraph, obj, sample_period, time_shown);
        ecgdata = ecgdata_input;
    }

    protected void Get_Next_Value(){
        data_point = ecgdata.get_next_value(0);
    }

    protected void Monitoring_Value(){
        super.val_counter.Count_bpm(super.data_point, super.series_pointer);
    }
}
