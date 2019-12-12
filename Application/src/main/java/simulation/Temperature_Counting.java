package simulation;

public class Temperature_Counting extends Value_Counter {
    private double avrg_temp;
    private int temp_counter;

    public Temperature_Counting(){
        temp_counter = 1;
        avrg_temp = 0;
    }

    public void Current_Temp(double val){
        if (temp_counter <= 166)
            avrg_temp += val;
        if (temp_counter == 167){
            temp_counter = 1;
            avrg_temp = 0;
        }
    }

    public void Count_bpm(double val, int index){}

    public int Index_Difference(){ return 0; }

    public double Double_Value(){
        return avrg_temp/166;
    }
}
