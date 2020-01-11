package simulation;

public class Body_Temp{

    double []array;
    double mean_temp;
    double walk_sigma;
    double adjustment_speed;
    Random_Walk walk;

    public Body_Temp(double mean_temp, double walk_sigma, double adjustment_speed){
        this.mean_temp = mean_temp;
        this.walk_sigma = walk_sigma;
        this.adjustment_speed = adjustment_speed;
    }

    /**
    public Body_Temp(double mean_temp, double walk_sigma, double adjustment_speed, int array_size){
        array = new double[array_size];
        this.mean_temp = mean_temp;
        walk = new Random_Walk(mean_temp, walk_sigma, adjustment_speed);
    }
     */

    public double get_next_value(int temp_type){

        if (temp_type == 0)
            walk = new Random_Walk(mean_temp, walk_sigma, adjustment_speed);
        else if (Math.abs(temp_type) == 1)
            walk = new Random_Walk(mean_temp+(temp_type*2), walk_sigma, adjustment_speed);
        else if (Math.abs(temp_type) == 2)
            walk = new Random_Walk(mean_temp+(temp_type*2), walk_sigma, adjustment_speed);
        else
            throw new IllegalArgumentException("temperature type must be -2, -1, 0, 1, or 2");
        return walk.get_next_pos();
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
