package simulation;
import java.util.Random;
import java.util.Arrays;

public class ECG {
    private double[] array; //array of one heartbeat
    private double new_value;
    private int val_position, number_zeros;
    private int order;

    /**
     * Constructor Class. Initialise ECG simulation parameters to default values
     */
    public ECG(int order, String ecg_type) {
        this.order = order;
        if (ecg_type.equals("normal")) {
            DaubechiesWavelet.SetOrder(order);
            array = DaubechiesWavelet.ReturnDaub();
        }
        else if (ecg_type.equals("AIR")){
            array = new double[] {0, 0.04, 0.07, 0.09, 0.1, 0.1, 0.1, 0.3, 0.5, 0.6, 0.5, 0, -0.06, -0.08, -0.1, -0.14, -0.18,-0.14,-0.12,-0.1};
            }
        else if (ecg_type.equals("AF")){
            DaubechiesWavelet.SetOrder(8);
            double[] array1 = DaubechiesWavelet.ReturnDaub();
            double[] array2 = new double[] {-0.1, -0.25, -0.3, -0.15};
            double[] temp_array = new double[array1.length + array2.length];

            System.arraycopy(array1, 0, temp_array, 0, array1.length);
            System.arraycopy(array2, 0, temp_array, array1.length, array2.length);

            array = temp_array;
            System.out.println(Arrays.toString(temp_array));
        }
        val_position = 0;
    }

    public double addNoise(double input_val, double mean, double variance)
    {
        Random r = new Random();
        double noise = r.nextGaussian() * Math.sqrt(variance) + mean;

        input_val += noise;
        return input_val;
    }

    private int PadZeros(int target_mean){
        int high = target_mean + 6;
        int step, diff;
        int pos = target_mean;
        float normalized_diff;
        Random r = new Random();

        if (r.nextInt(2) == 0)
        {
            step = r.nextInt(13) - 6;
            diff = pos - target_mean;
            normalized_diff = (float)diff/(high-target_mean);
            if (Math.abs(normalized_diff) >= 1)
                step *= -(int)(Math.signum(step)*Math.signum(normalized_diff));
            else
                step = Math.round(step*(1-Math.abs(normalized_diff)));
            pos += step;
        }

        return pos;
    }

    public double get_next_value(int hr_speed){
        int n_points = array.length;
        int target_mean;

        if (hr_speed == 0)
            target_mean = 166 - n_points;
        else if (hr_speed == 1)
            target_mean = 92 - n_points;
        else if (hr_speed == -1)
            target_mean = 218 - n_points;
        else if (hr_speed == -2)
            target_mean = 244 - n_points;
        else if (hr_speed == 2)
            target_mean = 66 - n_points;
        else
            throw new IllegalArgumentException("heart rate type must be -2, -1, 0, 1, or 2");

        set_new_value(target_mean, n_points);

        return addNoise(new_value, 0, 0.001);
    }

    private void set_new_value(int target_mean, int n_pts){
        if (val_position == 0)
            number_zeros = PadZeros(target_mean);

        if (val_position < n_pts)
            new_value = array[val_position];
        else if (val_position < number_zeros+n_pts)
            new_value = 0;

        val_position += 1;
        if (val_position ==  number_zeros+n_pts)
            val_position = 0;
    }
}
