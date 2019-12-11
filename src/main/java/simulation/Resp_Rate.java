package simulation;

import java.util.Random;

public class Resp_Rate {

    double target_mean_resp_rate;
    double resp_variance;
    double random_threshold;
    int period;
    int counter;
    Random r;

    public Resp_Rate(double target_mean_resp_rate, double resp_variance, double random_threshold, int period){
        this.target_mean_resp_rate = target_mean_resp_rate;
        this.resp_variance = resp_variance;
        this.random_threshold = random_threshold;
        this.period = period;
        counter = 0;
        Random r = new Random();
    }

    public double get_next_value(){
        counter += 1;
        double sin = Math.sin(counter / period);
        if (sin < random_threshold){
            counter -= Math.abs(r.nextGaussian() * resp_variance);
            sin = Math.sin(counter / period);
        }
        return sin + target_mean_resp_rate;
    }
}
