package Graphing;

import simulation.Resp_Rate;
import simulation.Value_Counter;

public class Graph_Respiration extends Graph {
    private Resp_Rate respdata;
    private int resp_rate_type;

    public Graph_Respiration(String colorGraph, Value_Counter obj, double sample_period, int time_shown, Resp_Rate respdata_input){
        super(colorGraph, obj, sample_period, time_shown);
        respdata = respdata_input;
        resp_rate_type = 0;
    }

    public void changeAbnormality(int newType) {

    }

    protected void Get_Next_Value(){
        super.data_point = respdata.get_next_value(resp_rate_type);
    }

    protected void Monitoring_Value(){
        super.val_counter.Count_bpm(super.data_point, super.series_pointer);
    }
}
