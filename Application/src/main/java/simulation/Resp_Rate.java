package simulation;

import java.util.Random;

public class Resp_Rate {

    private double target_mean_resp_rate;
    private double resp_variance;
    private double random_threshold;
    private int period;
    private double counter;
    private Random r;

    public Resp_Rate(double target_mean_resp_rate, double resp_variance, double random_threshold, int period){
        this.target_mean_resp_rate = target_mean_resp_rate;
        this.resp_variance = resp_variance;
        this.random_threshold = random_threshold;
        this.period = period;
        counter = 0;
        r = new Random();
    }

    public double get_next_value(){
        counter += 1 + Math.abs(r.nextGaussian() * resp_variance);
        double sin = Math.sin(counter / period) * 20;
        if (sin < random_threshold){
            counter -= Math.abs(r.nextGaussian() * resp_variance);
            sin = Math.sin(counter / period) * 20;
        }
        return sin + target_mean_resp_rate;
    }

}