package simulation;
import java.util.Random;


public class ECG {
    private double[] array; //array of one heartbeat
    private double new_value;
    private int val_position, number_zeros;

    /**
     * Constructor Class. Initialise ECG simulation parameters to default values
     */
    public ECG() {
        int order = 10;
        DaubechiesWavelet.SetOrder(order);
        array = DaubechiesWavelet.ReturnDaub();
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

    public double get_next_value(int ecg_type){
        if (ecg_type == 0)
            ecg_type = 146;
        else if (ecg_type == 1)
            ecg_type = 72;
        else if (ecg_type == -1)
            ecg_type =218;
        else
            throw new IllegalArgumentException("ECG type must be -1, 0 or 1");

        if (val_position == 0)
            number_zeros = PadZeros(ecg_type);

        if (val_position < 20)
            new_value = array[val_position];
        else if (val_position < number_zeros+20)
            new_value = 0;

        val_position += 1;
        if (val_position ==  number_zeros+20)
            val_position = 0;

        return addNoise(new_value, 0, 0.001);
    }
}
