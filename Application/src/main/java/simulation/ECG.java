package simulation;
import java.util.Random;


public class ECG {
    double[] concatenated;

    private double[] array; //array of one heartbeat
    /**
     * Constructor Class. Initialise ECG simulation parameters to default values
     */
    public ECG() {
        int order = 10;
        DaubechiesWavelet.SetOrder(order);
        array = DaubechiesWavelet.ReturnDaub();
    }

    public void addNoise(double[] input_array, double mean, double variance)
    {
        int len = input_array.length;

        for (int i = 0; i < len; i++)
        {
            Random r = new Random();
            double noise = r.nextGaussian() * Math.sqrt(variance) + mean;

            input_array[i] += noise;
        }
    }

    public void SetDaubCoefficient(int n)
    {
        DaubechiesWavelet.SetOrder(n);
    }

    public double[] Simulate(int ecg_type) {

        if (ecg_type == 0) {
            PadZeros(146);
            addNoise(concatenated, 0, 0.001);
        }
        else if (ecg_type == 1){
            PadZeros(72);
            addNoise(concatenated, 0, 0.001);
        }
        else if (ecg_type == -1){
            PadZeros(218);
            addNoise(concatenated,0,0.001);
        }
        else {
            throw new IllegalArgumentException("ECG type must be -1, 0 or 1");
        }

        return concatenated;
    }

    private void PadZeros(int target_mean){
        int high = target_mean + 6;

        int step;
        int pos = target_mean;
        int diff;
        float normalized_diff;
        int point_pointer = 0;

        concatenated = new double[300*high];
        Random r = new Random();

        for (int i = 0; i<100; i+=1) {

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

            int ii;
            for (ii = 0; ii < pos+20; ii += 1) {
                if (ii < 20)
                    concatenated[point_pointer + ii] = array[ii];
                if (ii >= 20)
                    concatenated[point_pointer + ii] = 0;
            }
            point_pointer += pos+20;
        }
    }
}
