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
        int low = 140;
        int high = 152;
        int target_mean = (low + high)/2;
        int noisy_idx = 146;

        int step;
        int pos = 146;
        int diff;
        float normalized_diff;

        concatenated = new double[300*high];
        Random r = new Random();

        for (int i = 0; i<100; i+=1) {

            //if (r.nextInt(3) == 2)
            //{

                step = r.nextInt(13) - 6;
//                System.out.println(step);
                diff = pos - target_mean;
                normalized_diff = (float)diff/(high-target_mean);
                if (Math.abs(normalized_diff) >= 1)
                    step *= -(int)(Math.signum(step)*Math.signum(normalized_diff));
                else
                    step = Math.round(step*(1-Math.abs(normalized_diff)));
                pos += step;
                System.out.println(pos);
           // }

            for (int ii = 0; ii < pos+20; ii += 1) {
                if (ii < 20)
                    concatenated[i*(pos+20) + ii] = array[ii];
//                System.out.println(concatenated[i+ii]);
                if (ii >= 20)
                    concatenated[i*(pos+20) + ii] = 0;
            }
        }

        System.out.println("\n\n\n");


//        array = ArrayUtils.addAll
    }
}