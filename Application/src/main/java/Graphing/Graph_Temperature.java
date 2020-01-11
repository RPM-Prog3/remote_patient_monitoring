package Graphing;

import simulation.Body_Temp;
import simulation.Resp_Rate;
import simulation.Value_Counter;

public class Graph_Temperature extends Graph {
    private Body_Temp tempdata;
    private int temp_type;

    public Graph_Temperature(String colorGraph, Value_Counter obj, double sample_period, int time_shown, Body_Temp tempdata_input){
        super(colorGraph, obj, sample_period, time_shown);
        tempdata = tempdata_input;
        temp_type = 0;
    }

    public void changeAbnormality(int newType) {
        temp_type = newType;
    }

    protected void Get_Next_Value(){
        super.data_point = tempdata.get_next_value(temp_type);
    }

    protected void Monitoring_Value(){
        super.val_counter.Current_Temp(super.data_point);
    }
}
