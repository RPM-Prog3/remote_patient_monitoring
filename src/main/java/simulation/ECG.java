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

    public double[] Simulate() {
        PadZeros();

//        for (int i = 0; i<100; i+=1)
//            for (int ii=0; ii<30; ii+=1){
//                System.out.println(concatenated[i+ii]);
//            }
        addNoise(concatenated, 0, 0.001);

        return concatenated;
    }

    private void PadZeros(){
        //double[] zero_padding = new double[10];
        int low = 144;
        int high = 148;
        int noisy_idx = 146;
        int target_mean = 146;
        int step = 1;
        int adjustment = 2;

        concatenated = new double[100*high];
        Random r = new Random();

        for (int i = 0; i<100; i+=1) {

            if (r.nextInt(3) == 2)
            {
                if (r.nextBoolean() == true)
                {
                    step = r.nextInt(5) + target_mean;
                }
                else
                {
                    step = -(r.nextInt(5) + target_mean);
                }

                int next_idx = noisy_idx + step;
                int diff = next_idx - target_mean;
                adjustment = (int)(Math.signum(diff)) * step;


                //noisy_idx = r.nextInt(high-low) + low;
            }

            for (int ii = 0; ii < noisy_idx; ii += 1) {
                if (ii < 20)
                    concatenated[i*noisy_idx + ii] = array[ii];
//                System.out.println(concatenated[i+ii]);
                if (ii >= 20)
                    concatenated[i*noisy_idx + ii] = 0;
            }
        }


//        array = ArrayUtils.addAll
    }
}