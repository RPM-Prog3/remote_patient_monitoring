package simulation;

public class Blood_Pressure {

    int period;
    int counter;
    double t1, t2, t3;

    public Blood_Pressure(){
        t1 = 0.60;
        t2 = 0.15;
        t3 = 0.25;
        counter = 0;
        period = 100;
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

}
