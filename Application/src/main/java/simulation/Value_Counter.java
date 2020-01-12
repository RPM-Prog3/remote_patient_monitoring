package simulation;

public abstract class Value_Counter {
    protected int counter_time, counter_values;
    protected int minuteSum_int, minuteSum_int2, minuteMean_int;
    protected int[] minuteMean_array;
    protected double minuteSum_dou, minuteMean_dou;
    private double[] all_mean_array;

    public Value_Counter(){
        counter_time = 0;
        counter_values = 0;

        minuteSum_int = 0;
        minuteSum_int2 = 0;
        minuteSum_dou = 0;

        minuteMean_int = 0;
        minuteMean_dou = 0;
        minuteMean_array = new int[] {0,0};
        all_mean_array = new double[] {0, 0, 0, 0};
    }

    protected int mean_counter(int a){
        if (counter_time == 60/0.006) {
            minuteMean_int = minuteSum_int / counter_values;
            minuteSum_int = 0;
            counter_values = 0;
            System.out.println(minuteMean_int);
        }

        return minuteMean_int;
    }

    protected double mean_counter(double a){
        if (counter_time == 60/0.006) {
            minuteMean_dou = minuteSum_dou / counter_values;
            minuteSum_dou = 0;
            counter_values = 0;
            System.out.println( Math.round((minuteMean_dou/166) * 1000.0)/1000.0);
        }

        return minuteMean_dou;
    }

    protected int[] mean_counter(int a, int b){
        if (counter_time == 60/0.006) {
            minuteMean_array[0] = minuteSum_int / counter_values;
            minuteMean_array[1] = minuteSum_int2 / counter_values;
            minuteSum_int = 0;
            minuteSum_int2 = 0;
            counter_values = 0;
            System.out.println(minuteMean_array[0]);
            System.out.println(minuteMean_array[1]);
        }

        return minuteMean_array;
    }

    public double getMean(int index){
        all_mean_array[0] = minuteMean_int;  //This is the mean for bpm and resp
        all_mean_array[1] = minuteMean_array[0];  //This is the mean for the max of the pressure
        all_mean_array[2] = minuteMean_array[1];  //This is the mean for the min of the pressure
        all_mean_array[4] = minuteMean_dou;  //This is the mean for the temp

        return all_mean_array[index];
    }

    public abstract void Pressure_Values(double val);

    public abstract void Current_Temp(double val);

    public abstract void Count_bpm(double val, int index);

}