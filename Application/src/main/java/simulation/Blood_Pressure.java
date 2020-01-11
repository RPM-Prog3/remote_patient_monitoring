package simulation;

public class Blood_Pressure {

    double []array;
    int period;
    int counter;
    double t1, t2, t3;

    public Blood_Pressure(double t1, double t2, double t3, int period){
        //t1 = 0.60;
        //t2 = 0.15;
        //t3 = 0.25;
        this.t1 = t1;
        this.t2 = t2;
        this.t3 = t3;
        this.period = period;

        counter = 0;
    }

    public double get_next_value(){
        double counter_period = counter % period;
        counter += 1;
        if (counter_period < (period * t1)){
            double start = counter_period;
            return (-Math.cos(start/14)*20+100);
        } else if (counter_period < (period * (t1+t2))) {
            double start = counter_period - period * t1;
            return -Math.sin(start/2)*1+109;
        } else {
            double start = counter_period - period * (t1+t2);
            return -Math.sin(start/15)*27+107;
        }
    }

    private void fill_array(){
        for (int i = 0; i < array.length - 1; i++){
            array[i] = get_next_value();
        }
    }

    public double[] get_array(){
        fill_array();
        return array;
    }
}
