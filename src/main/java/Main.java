import org.apache.commons.lang3.ArrayUtils;
import simulation.ECG;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        double variance = 0.0001;
        double mean = 0;

        ECG simulation = new ECG();
        double[] array = simulation.Simulate();
        System.out.println(Arrays.toString(array));
        simulation.addNoise(mean, variance);

        double[] zero_padding = new double[10];
        double[] combined_array = ArrayUtils.addAll(zero_padding,array);

        System.out.println(Arrays.toString(array));
        System.out.println(Arrays.toString(combined_array));
    }
}


