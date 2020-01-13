package simulation;

public class Pressure_Counting extends Value_Counter {
    int max, min, val_position, ecglength;
    int[] max_min;

    public Pressure_Counting(){
        max = -10;
        min = 1500;
        val_position = 0;
        ecglength = 100;
        max_min = new int[2];
    }

    public void Pressure_Values(double val){
        super.counter_time += 1;

        if (val > max)
            max = (int)val;
        if (val < min)
            min = (int)val;
        val_position += 1;
        if (val_position == ecglength){
            max_min[0] = max;
            max_min[1] = min;
            super.minuteSum_int += max;
            super.minuteSum_int2 += min;
            super.counter_values += 1;
            max = -10;
            min = 1500;
            val_position = 0;
        }
        mean_counter(1, 1);
    }

    public int[] Max_Min(){
        return max_min;
    }

    public void Current_Temp(double val){}

    public void Count_bpm(double val, int index){}

}
