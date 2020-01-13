package simulation;

public class Temperature_Counting extends Value_Counter {
    private double avrg_temp, temp_displayed;
    private int temp_counter;

    public void Pressure_Values(double val){}

    public Temperature_Counting(){
        temp_counter = 0;
        temp_displayed = 0;
        avrg_temp = 0;
    }

    public void Current_Temp(double val){
        super.counter_time += 1;

        temp_counter += 1;
        if (temp_counter <= 166)
            avrg_temp += val;
        if (temp_counter == 167){
            temp_displayed = avrg_temp/166;
            super.minuteSum_dou += rounded_temperature();
            super.counter_values += 1;
            temp_counter = 1;
            avrg_temp = val;
        }

        mean_counter(1.0);
    }

    public double rounded_temperature(){
        return Math.round(temp_displayed * 1000.0)/1000.0;
    }

    public void Count_bpm(double val, int index){}

//    public double Double_Value(){
//        return temp_displayed/166;
//    }

}
