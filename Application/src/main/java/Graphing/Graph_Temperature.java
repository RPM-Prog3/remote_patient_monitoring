package Graphing;

import simulation.Value_Counter;

public class Graph_Temperature extends Graph {

    public Graph_Temperature(String colorGraph, Value_Counter obj, double sample_period, float time_shown){
        super(colorGraph, obj, sample_period, time_shown);
    }

    protected void Monitoring_Value(){
        super.val_counter.Current_Temp(super.data_points[super.point_pointer]);
    }
}
