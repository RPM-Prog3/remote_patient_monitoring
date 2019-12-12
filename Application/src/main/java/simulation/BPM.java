package simulation;

public class BPM extends Value_Counter{
    private int[] peak_idx;
    private boolean if_ok;
    private int which;
    private int diff_samples;

    public BPM(){
        peak_idx = new int[2];
        diff_samples = 100000;
        if_ok = false;
        which = 0;
    }

    public void Current_Temp(double val){}

    public void Count_bpm(double val, int index){
        if (!if_ok && val>=0.4) {
            peak_idx[which] = index;
            if_ok = true;
            which += 1;
        }
        else if (if_ok && val<0.4)
            if_ok = false;

        if (which == 2){
            diff_samples = peak_idx[1] - peak_idx[0];
            peak_idx[0] = peak_idx[1];
            which = 1;
        }
    }

    public int Index_Difference(){
        return diff_samples;
    }

    public double Double_Value(){ return 0; }
}
