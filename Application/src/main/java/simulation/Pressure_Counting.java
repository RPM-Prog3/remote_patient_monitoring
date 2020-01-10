package simulation;

public class Pressure_Counting extends Value_Counter {
    int max, min, val_position, ecglength;
    int[] max_min;

    public Pressure_Counting(){
        max = 0;
        min = 150;
        val_position = 0;
        ecglength = 80;
        max_min = new int[2];
    }

    public void Pressure_Values(double val){
        if (val > max)
            max = (int)val;
        if (val < min)
            min = (int)val;
        val_position += 1;
        if (val_position == ecglength){
            max_min[0] = max;
            max_min[1] = min;
            max = 0;
            min = 150;
            val_position = 0;
        }
    }

    public void Current_Temp(double val){}

    public void Count_bpm(double val, int index){}

    public int Index_Difference(){ return 0; }

    public double Double_Value(){ return 0; }

    public int[] Max_Min(){
        return max_min;
    }

}
