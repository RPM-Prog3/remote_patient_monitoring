package Graphing;

import simulation.Value_Counter;

public class Graph_ECG extends Graph {

    public Graph_ECG(String colorGraph, Value_Counter obj, double sample_period, float time_shown){
        super(colorGraph, obj, sample_period, time_shown);
    }

    protected void Monitoring_Value(){
        super.val_counter.Count_bpm(super.data_points[super.point_pointer], super.point_pointer);
    }
}
