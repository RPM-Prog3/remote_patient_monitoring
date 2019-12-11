package simulation;

import java.util.Random;

public class Random_Walk {

    double target_mean;
    double pos;
    double sigma;
    double adjustment_speed;
    Random r;

    public Random_Walk(double target_mean, double sigma, double adjustment_speed){
        this.target_mean = target_mean;
        this.pos = target_mean;
        this.sigma = sigma;
        this.adjustment_speed = adjustment_speed;
        r = new Random();
    }

    private double sample_gaussian(double mean, double sigma){
        return (r.nextGaussian() * sigma) + mean;
    }

    public double sample_next_step(){
        double step = sample_gaussian(0.0, sigma);
        double est_next_pos = pos + step;
        double diff = est_next_pos - target_mean;
        double adjustment = Math.signum(diff) * step;
        if (adjustment > 0){
            step *= 0.5;
        }
        return step;
    }

    public double get_next_pos(){
        pos += sample_next_step();
        return pos;
    }

    public void update_target_mean(double new_mean){
        target_mean = new_mean;
    }

    public void update_sigma(double new_sigma){
        sigma = new_sigma;
    }

    public void update_adjustment_speed(double new_adjust){
        adjustment_speed = new_adjust;
    }
}