package Graphing;

import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import simulation.Blood_Pressure;
import simulation.Value_Counter;

public class Graph_Pressure extends Graph {
    private Blood_Pressure pressuredata;
    private int bp_type;

    public Graph_Pressure(String colorGraph, Value_Counter obj, double sample_period, int time_shown, Blood_Pressure pressuredata_input){
        super(colorGraph, obj, sample_period, time_shown);
        pressuredata = pressuredata_input;
        bp_type = 0;
    }

    public void changeAbnormality(int newType) {
        bp_type = newType;
    }

    protected void scaling(int size, NumberAxis yAxis, int windowSize){
        if ((size-2) <= windowSize/0.006)
            yAxis.setAutoRanging(true);
        else {
            yAxis.setAutoRanging(false);
            yAxis.setLowerBound(70);
            yAxis.setUpperBound(130);
        }
    }

    protected void Get_Next_Value(){
        super.data_point = pressuredata.get_next_value(bp_type);
    }

    protected void Monitoring_Value(){
        super.val_counter.Pressure_Values(super.data_point);
    }
}
