package simulation;

public class Blood_Pressure {

    double []array;
    int period;
    int counter;
    double t1, t2, t3;
    double amplitude;

    public Blood_Pressure(double t1, double t2, double t3, int period, double amplitude){
        this.t1 = t1;
        this.t2 = t2;
        this.t3 = t3;
        this.period = period;
        this.amplitude = amplitude;
        counter = 0;
    }

    public double get_next_value(int bp_type){
        int systolic_pressure_change = bp_type * 20;
        int diastolic_pressure_change = 20*bp_type + Integer.signum(bp_type)*-10;

        double counter_period = counter % period;
        counter += 1;
        if (counter_period < (period * t1)){
            double start = counter_period;
            return (-Math.cos(start/14)*20+100+systolic_pressure_change);
        } else if (counter_period < (period * (t1+t2))) {
            double start = counter_period - period * t1;
            return -Math.sin(start/2)+109+systolic_pressure_change;
        } else {
            double start = counter_period - period * (t1+t2);
            return -Math.sin(start/15)*27+107+diastolic_pressure_change;
        }
    }

    /**
    private void fill_array(){
        for (int i = 0; i < array.length - 1; i++){
            array[i] = get_next_value();
        }
    }

    public double[] get_array(){
        fill_array();
        return array;
    }
     */
}
