package simulation;

public class Respiration_Counting extends Value_Counter{
    private int[] peak_idx;
    private boolean if_ok;
    private int which;
    private int diff_samples;

    public Respiration_Counting(){
        peak_idx = new int[2];
        diff_samples = 100000;
        if_ok = false;
        which = 0;
    }

    public void Pressure_Values(double val){}

    public void Current_Temp(double val){}

    public void Count_bpm(double val, int index){
        super.counter_time += 1;

        if (!if_ok && val>=60) {
            peak_idx[which] = index;
            if_ok = true;
            which += 1;
        }
        else if (if_ok && val<60)
            if_ok = false;

        if (which == 2){
            diff_samples = peak_idx[1] - peak_idx[0];
            super.minuteSum_int += breaths_number();
            super.counter_values += 1;
            peak_idx[0] = peak_idx[1];
            which = 1;
        }

        mean_counter(1);
    }

    public int breaths_number(){
        return (int)(60/(diff_samples*0.006));
    }

//    public int Index_Difference(){
//        return diff_samples;
//    }

}
