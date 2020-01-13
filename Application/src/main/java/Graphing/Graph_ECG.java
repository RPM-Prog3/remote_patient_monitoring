package Graphing;

import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
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

//    protected void scaling(int size, NumberAxis yAxis, int windowSize){
//        if ((size-2) <= windowSize/0.006)
//            yAxis.setAutoRanging(true);
//        else {
//            yAxis.setAutoRanging(false);
//            yAxis.setLowerBound(-0.5);
//            yAxis.setUpperBound(1);
//        }
//    }

    protected void Get_Next_Value(){
        data_point = ecgdata.get_next_value(heart_rate_type);
    }

    protected void Monitoring_Value(){
        super.val_counter.Count_bpm(super.data_point, super.series_pointer);
    }
}
