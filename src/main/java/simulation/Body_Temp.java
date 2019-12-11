package simulation;

import java.util.Random;

public class Body_Temp {

    double mean_temp;
    Random_Walk walk;

    public Body_Temp(double mean_temp, double walk_sigma, double adjustment_speed){
        this.mean_temp = mean_temp;
        walk = new Random_Walk(mean_temp, walk_sigma, adjustment_speed);
    }

    public double get_next_value(){
        return walk.get_next_pos();
    }
}
