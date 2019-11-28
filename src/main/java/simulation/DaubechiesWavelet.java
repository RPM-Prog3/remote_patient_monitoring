package simulation;

import java.lang.Math;

public class DaubechiesWavelet{

    private double c;

    void forward(double[] a, int n){
        if (n < 1){
            throw new IllegalArgumentException("p must be at least 1");
        }
        else if (n == 1){
            c = 1 / Math.sqrt(2);

        }
    }

}