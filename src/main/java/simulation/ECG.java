package simulation;

public class ECG {

    private double[] array;
    /**
     * Constructor Class. Initialise ECG simulation parameters to default values
     */

    public ECG() {
        int order = 10;
        DaubechiesWavelet.SetOrder(order);
        array = DaubechiesWavelet.ReturnDaub();
    }

    public void SetDaubCoefficient(int n)
    {
        DaubechiesWavelet.SetOrder(n);
    }

    public double[] Simulate() {
        return array;
    }


}
