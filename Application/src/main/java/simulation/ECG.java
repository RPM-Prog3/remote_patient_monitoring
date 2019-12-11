package simulation;
import java.util.Random;


public class ECG {
    double[] concatenated;

    private double[] array; //array of one heartbeat
    /**
     * Constructor Class. Initialise ECG App.simulation parameters to default values
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
        concatenated = new double[3000];

        for (int i = 0; i<100; i+=1) {
            for (int ii = 0; ii < 30; ii += 1) {
                if (ii < 20)
                    concatenated[i*30 + ii] = array[ii];
//                System.out.println(concatenated[i+ii]);
                if (ii >= 20)
                    concatenated[i*30 + ii] = 0;
            }
        }


//        array = ArrayUtils.addAll
    }
}