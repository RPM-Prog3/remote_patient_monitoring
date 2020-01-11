package Graphing;

import simulation.Blood_Pressure;
import simulation.Value_Counter;

public class Graph_Pressure extends Graph {
    private Blood_Pressure pressuredata;

    public Graph_Pressure(String colorGraph, Value_Counter obj, double sample_period, int time_shown, Blood_Pressure pressuredata_input){
        super(colorGraph, obj, sample_period, time_shown);
        pressuredata = pressuredata_input;
    }

    public void changeAbnormality(int newType) {

    }

    protected void Get_Next_Value(){
        super.data_point = pressuredata.get_next_value();
    }

    protected void Monitoring_Value(){
        super.val_counter.Pressure_Values(super.data_point);
    }
}
