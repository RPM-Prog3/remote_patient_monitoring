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
        if (Math.abs(bp_type) > 2){
            throw new IllegalArgumentException("Blood pressure type must be -2, -1, 0, 1, or 2");
        }

        int pressure_change = bp_type * 10;

        double counter_period = counter % period;
        counter += 1;
        if (counter_period < (period * t1)) {
            double start = counter_period;
            return (-Math.cos(start / 14) * 20 + 101 + pressure_change);
        } else if (counter_period < (period * (t1 + t2))) {
            double start = counter_period - period * t1;
            return -Math.sin(start / 2) + 110 + pressure_change;
        } else {
            double start = counter_period - period * (t1 + t2);
            return -Math.sin(start / 15) * 27 + 107 + pressure_change;
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
